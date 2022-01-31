package com.revature.amsapi.controller;

import com.revature.amsapi.entity.Users;
import com.revature.amsapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
@CrossOrigin
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<Users> getUsers(){
        return userService.getUsers();
    }

    @GetMapping(path = "{userId}")
    public Users getUsersById(@PathVariable("userId") Integer userId) {
        try {
            return userService.getUser(userId);
        } catch (IllegalStateException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @PostMapping(path = "login")
    public Users loginUser(@RequestBody Users user){
        return userService.loginUser(user);
    }

    @PostMapping
    public Users createUser(@RequestBody Users user){
        return userService.createUser(user); }

    @DeleteMapping(path = "{userId}")
    public boolean deleteUser(@PathVariable("userId") Integer userId){
        try {
            userService.deleteUser(userId);
            return true;
        } catch(IllegalStateException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @PutMapping(path = "{userId}/{newPass}")
    public Users updateRole(@PathVariable("userId") Integer userId, @PathVariable("newPass") String newPass){
        try {
            Users updatedUser = userService.updateUser(userId, newPass);
            return updatedUser;
        } catch (IllegalStateException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
