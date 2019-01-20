package com.curse.rest.webservices.restfulwebservices.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.curse.rest.webservices.restfulwebservices.exceptions.UserNotFoundException;
import com.curse.rest.webservices.restfulwebservices.model.User;
import com.curse.rest.webservices.restfulwebservices.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> getAll() {

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
    public ResponseEntity<String> createUser(@Valid @RequestBody User user){

        User userCreated = userService.createUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userCreated.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

}
