package com.revature.amsapi.service;

import com.revature.amsapi.entity.Role;
import com.revature.amsapi.entity.Users;
import com.revature.amsapi.exception.InvalidInputException;
import com.revature.amsapi.exception.InvalidLoginException;
import com.revature.amsapi.exception.MissingFieldException;
import com.revature.amsapi.exception.UserNotFoundException;
import com.revature.amsapi.repository.CustomerRepository;
import com.revature.amsapi.repository.RoleRepository;
import com.revature.amsapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, CustomerRepository customerRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.roleRepository = roleRepository;
    }

    public List<Users> getUsers(){
        return userRepository.findAll();
    }

    public Users getUser(int userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with ID: " + userId + " does not exist."));
    }

    public Users loginUser(Users user) throws InvalidLoginException {
       Users checkUser = userRepository.loginUser(user.getUsername(), user.getPassword());

       if (checkUser == null) {
           throw new InvalidLoginException("Username and password combination is invalid.");
       }

        return checkUser;
    }

    public Users createUser(Users user) throws UserNotFoundException{
        customerRepository.save(user.getCustomer());
        Role role = roleRepository.findById(user.getRole().getRole_id()).orElseThrow(() -> new UserNotFoundException("Role with ID: " + user.getRole().getRole_id() + " does not exist."));
        user.setRole(role);
        return userRepository.save(user);
    }

    public boolean deleteUser(int userId) throws UserNotFoundException {
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with ID: " + userId + " does not exist."));
        userRepository.deleteById(userId);
        return true;
    }

    @Transactional
    public Users updateUser(int userId, String password) throws UserNotFoundException, MissingFieldException, InvalidInputException {
        Users updatedUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with ID: " + userId + " does not exist."));

        if (password == null){
            throw new MissingFieldException("Password must be provided.");
        } else if (password.length() < 8) {
            throw new InvalidInputException("Password needs to be greater at least 8 characters.");
        }

        updatedUser.setPassword(password);
        return userRepository.save(updatedUser);
    }
}
