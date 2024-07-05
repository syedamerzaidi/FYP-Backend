package com.fyp.hca.controller;

import com.fyp.hca.entity.Users;
import com.fyp.hca.model.PaginatedResponse;
import com.fyp.hca.services.KeycloakAdminService;
import com.fyp.hca.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UsersController {

    private final UsersService userService;
    private final KeycloakAdminService keycloakAdminService;

    @Autowired
    public UsersController(UsersService userService, KeycloakAdminService keycloakAdminService) {
        this.userService = userService;
        this.keycloakAdminService = keycloakAdminService;
    }

    @PostMapping(value = "/add")
    @PreAuthorize("hasRole('Super Administrator')")
    public void addUsers(@RequestBody Users users) {
        /*keycloakAdminService.createUser(users.getFirstName(),users.getEmail(),users.getFirstName(),users.getLastName(),users.getPassword());
        String roleId = switch (users.getRole()) {
            case "Super Administrator" -> "Super Administrator";
            case "PROVINCE Administrator" -> "Province Administrator";
            case "Division Administrator" -> "Division Administrator";
            case "District Administrator" -> "District Administrator";
            case "Tehsil Administrator" -> "Tehsil Administrator";
            case "Hospital Administrator" -> "Hospital Administrator";
            default -> null;
        };
        keycloakAdminService.assignRealmRole(keycloakAdminService.getUserIdByUsername(users.getEmail()), roleId);
        keycloakAdminService.assignClientRole(keycloakAdminService.getUserIdByUsername(users.getEmail()), "client", roleId);*/
        userService.save(users);
    }

    @GetMapping(value = "/get")
    //@PreAuthorize("hasAnyRole('SUPER_ADMIN', 'PROVINCE_ADMIN', 'DIVISION_ADMIN', 'DISTRICT_ADMIN', 'TEHSIL_ADMIN', 'HOSPITAL_ADMIN')")
    public List<Users> getUsers() {
        return userService.getUsers();
    }


    //@PreAuthorize("hasAnyRole('SUPER_ADMIN', 'PROVINCE_ADMIN', 'DIVISION_ADMIN', 'DISTRICT_ADMIN', 'TEHSIL_ADMIN', 'HOSPITAL_ADMIN')")
    @GetMapping(value = "/get/{id}")
    public Optional<Users> getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @DeleteMapping(value = "/delete/{id}")
    //@PreAuthorize("hasRole('client Super Administrator')")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @PutMapping(value = "/update")
    //@PreAuthorize("hasRole('SUPER_ADMIN')")
    public void updateUser(@RequestBody Users users) {
        userService.updateUsers(users);
    }

    @GetMapping(value = "/login")
    public Users handleLogin(
            @RequestParam("email") String email,
            @RequestParam("password") String password) {
        return userService.isValidUser(email, password);
    }

    @GetMapping(value = "/getTableData")
    //@PreAuthorize("hasAnyRole('SUPER_ADMIN', 'PROVINCE_ADMIN', 'DIVISION_ADMIN', 'DISTRICT_ADMIN', 'TEHSIL_ADMIN', 'HOSPITAL_ADMIN')")
    public ResponseEntity<?> getTableData(
            @RequestParam("start") int start,
            @RequestParam("size") int size,
            @RequestParam("filters") String filters,
            @RequestParam("sorting") String sorting,
            @RequestParam("globalFilter") String globalFilter) {
        PaginatedResponse<Users> result = userService.getTableData(start, size, filters, sorting, globalFilter);
        return ResponseEntity.ok().body(result);
    }

    // Example method to check if a user has a specific role
    @GetMapping(value = "/checkRole")
    public boolean checkUserRole(@RequestParam String role) {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_" + role));
    }
}
