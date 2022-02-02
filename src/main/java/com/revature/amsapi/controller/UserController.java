package com.revature.amsapi.controller;

import com.revature.amsapi.entity.Users;
import com.revature.amsapi.exception.InvalidInputException;
import com.revature.amsapi.exception.InvalidLoginException;
import com.revature.amsapi.exception.MissingFieldException;
import com.revature.amsapi.exception.UserNotFoundException;
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
    public Users getUsersById(@PathVariable("userId") Integer userId) throws UserNotFoundException { return userService.getUser(userId); }

    @PostMapping(path = "login")
    public Users loginUser(@RequestBody Users user) throws InvalidLoginException { return userService.loginUser(user); }

    @PostMapping
    public Users createUser(@RequestBody Users user) throws UserNotFoundException { return userService.createUser(user); }

    @DeleteMapping(path = "{userId}")
    public boolean deleteUser(@PathVariable("userId") Integer userId) throws UserNotFoundException { return userService.deleteUser(userId); }

    @PutMapping(path = "{userId}/{newPass}")
    public Users updateRole(@PathVariable("userId") Integer userId, @PathVariable("newPass") String newPass) throws UserNotFoundException, MissingFieldException, InvalidInputException { return userService.updateUser(userId, newPass); }
}
