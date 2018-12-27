package com.curse.rest.webservices.restfulwebservices.controller;

import com.curse.rest.webservices.restfulwebservices.exceptions.UserNotFoundException;
import com.curse.rest.webservices.restfulwebservices.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.curse.rest.webservices.restfulwebservices.service.UserService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class UserController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> getAll(){

        return userService.getAll();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable(name = "id") Integer id){

        User user = userService.getUser(id);

        if(user == null) {
            throw new UserNotFoundException("id-" + id);
        }
        return user;
    }

    @DeleteMapping("/users/{id}")
    public User deleteUser(@PathVariable(name = "id") Integer id){

        User user = userService.deleteById(id);

        if(user == null) {
            throw new UserNotFoundException("id-" + id);
        }
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity createUser(@Valid @RequestBody User user){

        User userCreated = userService.createUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userCreated.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

}
