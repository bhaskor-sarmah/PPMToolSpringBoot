package io.bhaskor.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import io.bhaskor.ppmtool.domain.User;
import io.bhaskor.ppmtool.exceptions.UsernameAlreadyExistException;
import io.bhaskor.ppmtool.repository.UserRepository;

/**
 * UserService
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User saveUser(User newUser) {

        try {

            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            newUser.setUsername(newUser.getUsername());
            newUser.setConfirmPassword("");
            // Username has to be unique with custom exception
            // password and confirm password match
            // not persist confirm password
            return userRepository.save(newUser);
        } catch (Exception e) {
            throw new UsernameAlreadyExistException("Username '" + newUser.getUsername() + "' already Exist");
        }
    }

}