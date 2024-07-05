package com.fyp.hca.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KeycloakAdminService {

    @Value("${keycloak.admin.url}")
    private String keycloakAdminUrl;

    @Value("${keycloak.admin.realm}")
    private String keycloakAdminRealm;

    @Value("${keycloak.admin.client-id}")
    private String clientId;

    @Value("${keycloak.admin.username}")
    private String adminUsername;

    @Value("${keycloak.admin.password}")
    private String adminPassword;

    @Value("${keycloak.admin.token-url}")
    private String tokenUrl;

    private final RestTemplate restTemplate;

    public KeycloakAdminService() {
        this.restTemplate = new RestTemplate(getClientHttpRequestFactory());
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(3000);
        factory.setReadTimeout(3000);
        return factory;
    }

    private String getAdminAccessToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "password");
        requestBody.add("client_id", clientId);
        requestBody.add("username", adminUsername);
        requestBody.add("password", adminPassword);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<Map> response = restTemplate.exchange(tokenUrl, HttpMethod.POST, entity, Map.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                Object accessToken = response.getBody().get("access_token");
                if (accessToken != null) {
                    return accessToken.toString();
                } else {
                    throw new IllegalStateException("Access token not found in response body");
                }
            } else {
                throw new IllegalStateException("Failed to obtain access token. Status code: " + response.getStatusCodeValue());
            }
        } catch (HttpClientErrorException e) {
            throw new IllegalStateException("Error while obtaining access token: " + e.getMessage(), e);
        }
    }


    public void createUser(String username, String email, String firstName, String lastName, String password) {
        String createUserUrl = keycloakAdminUrl + "/admin/realms/" + keycloakAdminRealm + "/users";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + getAdminAccessToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        String jsonPayload = "{"
                + "\"username\": \"" + username + "\","
                + "\"email\": \"" + email + "\","
                + "\"firstName\": \"" + firstName + "\","
                + "\"lastName\": \"" + lastName + "\","
                + "\"enabled\": true,"
                + "\"credentials\": [{\"type\": \"password\", \"value\": \"" + password + "\", \"temporary\": false}]"
                + "}";

        HttpEntity<String> entity = new HttpEntity<>(jsonPayload, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(createUserUrl, HttpMethod.POST, entity, String.class);
            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new IllegalStateException("Failed to create user. Status code: " + response.getStatusCodeValue());
            }
        } catch (Exception e) {
            throw new IllegalStateException("Error while creating user: " + e.getMessage(), e);
        }
    }


    public void assignRealmRole(String userId, String roleId) {
        String assignRoleUrl = keycloakAdminUrl + "/admin/realms/" + keycloakAdminRealm + "/users/" + userId + "/role-mappings/realm";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + getAdminAccessToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        String jsonPayload = "[{\"id\": \"" + roleId + "\", \"name\": \"Super Admin\", \"composite\": false}]";

        HttpEntity<String> entity = new HttpEntity<>(jsonPayload, headers);

        restTemplate.exchange(assignRoleUrl, HttpMethod.POST, entity, String.class);
    }

    public void assignClientRole(String userId, String clientId, String roleId) {
        String assignRoleUrl = keycloakAdminUrl + "/admin/realms/" + keycloakAdminRealm + "/users/" + userId + "/role-mappings/clients/" + clientId;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + getAdminAccessToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        String jsonPayload = "[{\"id\": \"" + roleId + "\", \"name\": \"Hospital Admin\", \"composite\": false}]";

        HttpEntity<String> entity = new HttpEntity<>(jsonPayload, headers);

        restTemplate.exchange(assignRoleUrl, HttpMethod.POST, entity, String.class);
    }

    private String getRealmRoleId(String roleName) {
        String roleUrl = keycloakAdminUrl + "/admin/realms/" + keycloakAdminRealm + "/roles/" + roleName;
        String accessToken = getAdminAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(roleUrl, HttpMethod.GET, request, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return (String) response.getBody().get("id");
        } else {
            throw new RuntimeException("Failed to get role ID for role: " + roleName);
        }
    }

    public String getUserIdByUsername(String username) {
        String searchUrl = keycloakAdminUrl + "/admin/realms/" + keycloakAdminRealm + "/users?username=" + username;
        String accessToken = getAdminAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<List> response = restTemplate.exchange(searchUrl, HttpMethod.GET, request, List.class);

        if (response.getStatusCode() == HttpStatus.OK && !response.getBody().isEmpty()) {
            Map user = (Map) response.getBody().get(0);
            return (String) user.get("id");
        } else {
            throw new RuntimeException("Failed to get user ID for username: " + username);
        }
    }
}
