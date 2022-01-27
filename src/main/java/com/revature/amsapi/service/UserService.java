package com.revature.amsapi.service;

import com.revature.amsapi.entity.User;
import com.revature.amsapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Init repository to call queries
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // Returns all users
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    // Get a user by id
    public User getUser(int userId) {
        return userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("User with ID: " + userId + " does not exist."));
    }

    // Creates a new user
    public User createUser(User user){ return userRepository.save(user); }

    // Deletes a user
    public boolean deleteUser(int userId) {
        userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("User with ID: " + userId + " does not exist."));

        try {
            userRepository.deleteById(userId);
        } catch (Exception e) {
            System.out.println("Error:" + e);
            return false;
        }

        return true;
    }

    // Updates a user
    @Transactional
    public User updateUser(int userId, String password) {
        User updatedUser = userRepository.findById(userId).orElseThrow(() ->
                new IllegalStateException("User with ID: " + userId + " does not exist."));

        if (password !=  null){
            updatedUser.setPassword(password);
            return userRepository.save(updatedUser);
        } else {
            throw new IllegalStateException("Password cannot be provided.");
        }
    }
}
