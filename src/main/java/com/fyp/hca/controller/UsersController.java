package com.fyp.hca.controller;
import com.fyp.hca.entity.Users;
import com.fyp.hca.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    public Optional<Users> handleLogin(
            @RequestParam String email,
            @RequestParam String password){
        return userService.isValidUser(email,password);
    }
    @GetMapping(value = "user/get-all-users")
    public List<Users> getAllUsers(@RequestParam(defaultValue = "0") Integer pageNo,
                                @RequestParam(defaultValue = "10") Integer pageSize){
        return userService.getallUsers(pageNo, pageSize);
    }


}