package com.teamjw.tripapp.app.user.service;

import java.util.Optional;

import com.teamjw.tripapp.app.user.domain.User;
import com.teamjw.tripapp.app.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.teamjw.tripapp.app.user.security.UserService;



/**
 * Mock implementation.
 * 
 * @author vladimir.stankovic
 *
 * Aug 4, 2016
 */
@Service
public class DatabaseUserService implements UserService {
    private final UserRepository userRepository;
    
    @Autowired
    public DatabaseUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }
}
