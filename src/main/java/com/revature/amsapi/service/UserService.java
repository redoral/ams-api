package com.revature.amsapi.service;

import com.revature.amsapi.entity.Customer;
import com.revature.amsapi.entity.Role;
import com.revature.amsapi.entity.Users;
import com.revature.amsapi.repository.CustomerRepository;
import com.revature.amsapi.repository.RoleRepository;
import com.revature.amsapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;

    // Init repository to call queries
    @Autowired
    public UserService(UserRepository userRepository, CustomerRepository customerRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.roleRepository = roleRepository;
    }

    // Returns all users
    public List<Users> getUsers(){
        return userRepository.findAll();
    }

    // Get a user by id
    public Users getUser(int userId) {
        return userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("User with ID: " + userId + " does not exist."));
    }

    // Creates a new user
    public Users createUser(Users user){
        customerRepository.save(user.getCustomer());
        Role role = roleRepository.findById(user.getRole().getRole_id()).orElseThrow(() -> new IllegalStateException("Role with ID: " + user.getRole().getRole_id() + " does not exist."));
        user.setRole(role);
        return userRepository.save(user);
    }

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
    public Users updateUser(int userId, String password) {
        Users updatedUser = userRepository.findById(userId).orElseThrow(() ->
                new IllegalStateException("User with ID: " + userId + " does not exist."));

        if (password !=  null){
            updatedUser.setPassword(password);
            return userRepository.save(updatedUser);
        } else {
            throw new IllegalStateException("Password must be provided.");
        }
    }
}
