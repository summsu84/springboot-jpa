package com.teamjw.tripapp.app.user.security;

import com.teamjw.tripapp.app.user.domain.User;

import java.util.Optional;


/**
 * 
 * @author vladimir.stankovic
 *
 * Aug 17, 2016
 */
public interface UserService {
    public Optional<User> getByUsername(String username);
}
