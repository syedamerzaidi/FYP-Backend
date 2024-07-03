/*
package com.fyp.hca.controller;

import com.fyp.hca.entity.Users;
import com.fyp.hca.model.PaginatedResponse;
import com.fyp.hca.services.UsersService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UsersController {

    private final UsersService userService;

    @Autowired
    public UsersController(UsersService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/add")
    public void addUsers(@RequestBody Users users) {
        userService.save(users);
    }

    @GetMapping(value = "/get")
    @RolesAllowed("admin")
    public List<Users> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(value = "/get/{id}")
    public Optional<Users> getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @PutMapping(value = "/update")
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
    public ResponseEntity<?> getTableData(
            @RequestParam("start") int start,
            @RequestParam("size") int size,
            @RequestParam("filters") String filters,
            @RequestParam("sorting") String sorting,
            @RequestParam("globalFilter") String globalFilter) {
        PaginatedResponse<Users> result = userService.getTableData(start, size, filters, sorting, globalFilter);
        return ResponseEntity.ok().body(result);
    }
}
*/


package com.fyp.hca.controller;

import com.fyp.hca.config.CustomAuthenticationProvider;
import com.fyp.hca.entity.Users;
import com.fyp.hca.model.PaginatedResponse;
import com.fyp.hca.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UsersController {

    private final UsersService userService;
    private final CustomAuthenticationProvider authenticationProvider;

    @Autowired
    public UsersController(UsersService userService, CustomAuthenticationProvider authenticationProvider) {
        this.userService = userService;
        this.authenticationProvider = authenticationProvider;
    }

    @PostMapping(value = "/add")
    public void addUsers(@RequestBody Users users) {
        userService.save(users);
    }

    @GetMapping(value = "/get")
    public List<Users> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(value = "/get/{id}")
    public Optional<Users> getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @PutMapping(value = "/update")
    public void updateUser(@RequestBody Users users) {
        userService.updateUsers(users);
    }

    @GetMapping(value = "/login")
    public ResponseEntity<?> handleLogin(
            @RequestParam("email") String email,
            @RequestParam("password") String password) {
        try {
            // Create authentication token
            Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);

            // Authenticate user using custom authentication provider
            authentication = authenticationProvider.authenticate(authentication);

            // Set authenticated user in Security Context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Retrieve authenticated user details
            Users authenticatedUser = (Users) authentication.getPrincipal();

            // Return authenticated user details
            return ResponseEntity.ok(authenticatedUser);
        } catch (AuthenticationException e) {
            // Authentication failed
            return ResponseEntity.status(401).body("Authentication failed: " + e.getMessage());
        }
    }

    @GetMapping(value = "/getTableData")
    public ResponseEntity<?> getTableData(
            @RequestParam("start") int start,
            @RequestParam("size") int size,
            @RequestParam("filters") String filters,
            @RequestParam("sorting") String sorting,
            @RequestParam("globalFilter") String globalFilter) {
        PaginatedResponse<Users> result = userService.getTableData(start, size, filters, sorting, globalFilter);
        return ResponseEntity.ok().body(result);
    }
}
