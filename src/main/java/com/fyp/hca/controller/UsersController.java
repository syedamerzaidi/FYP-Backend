package com.fyp.hca.controller;
import com.fyp.hca.entity.Users;
import com.fyp.hca.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
public class UsersController {

    private final UsersService userService;

    @Autowired
    public UsersController(UsersService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "user/add")
    public void addUsers(@RequestBody Users users){
        userService.save(users);
    }

    @PutMapping(value = "/user/addUser", consumes = {"multipart/form-data"})
    public void addUsers(Users user,@RequestParam("file") MultipartFile file) throws SQLException, IOException {
        userService.saveWithImg(user,file);
    }

    @GetMapping(value = "user/get")
    public List<Users> getUsers(){
        return userService.getUsers();
    }

    @GetMapping(value = "user/get/{id}")
    public Optional<Users> getUserById(@PathVariable Integer id){
        return userService.getUserById(id);
    }

    @DeleteMapping(value = "/user/delete/{id}")
    public void deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
    }

    @PutMapping(value = "/user/update")
    public void updateUser(@RequestBody Users users){
        userService.updateUsers(users);
    }

    @GetMapping(value = "/user/login")
    public Users handleLogin(
            @RequestParam("email") String email,
            @RequestParam("password") String password){
        return userService.isValidUser(email,password);
    }
}