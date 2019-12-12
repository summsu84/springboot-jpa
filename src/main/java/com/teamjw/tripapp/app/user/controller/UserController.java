package com.teamjw.tripapp.app.user.controller;


import com.teamjw.tripapp.app.user.domain.User;
import com.teamjw.tripapp.app.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
	@Value("#{environment['SERVICE_ENDPOINT'] ?: 'localhost:8080'}")
	private String serviceEndpoint;

	@Autowired
    private UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/api/user", method = RequestMethod.GET)
    public List<User> getUsers() {
        return userService.getUsers();
    }

    //
/*    @RequestMapping(value = "/api/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }*/

    @RequestMapping(value = "/api/user/{userId}", method = RequestMethod.GET)
    public Optional<User> getUserById(@PathVariable(value = "userId") Long userId) {
        return userService.getUserById(userId);
    }

/*    @RequestMapping(value = "/user", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@PathVariable(value = "authorId") Long authorId, @RequestBody Author author) {
        return authorService.updateAuthorById(authorId, author);
    }*/

    @RequestMapping(value = "/api/user/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUserById(@PathVariable(value = "userId") long userId) {
        return userService.deleteUserById(userId);
    }

}