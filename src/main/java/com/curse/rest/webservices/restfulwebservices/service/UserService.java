package com.curse.rest.webservices.restfulwebservices.service;

import com.curse.rest.webservices.restfulwebservices.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private List<User> users;

    {
        users = new ArrayList<>();

        users.add(new User(1, "Jose L"));
        users.add(new User(2, "Luis A"));
        users.add(new User(3, "Mireya C"));
        users.add(new User(4, "Andres D"));
    }

    public List<User> getAll(){

        return users;
    }

    public User getUser(Integer id){

        return users.stream().filter(user -> user.getId().equals(id)).findAny().orElse(null);

    }

    public User createUser(User user){

        int nextId = users.stream().mapToInt(User::getId).max().orElse(0) + 1;
        user.setId(nextId);
        users.add(user);

        return user;
    }

    public User deleteById(Integer id){

        Optional<User> optionalUser = users.stream().filter(user -> user.getId().equals(id)).findFirst();

        if(optionalUser.isPresent()){
            users.remove(optionalUser.get());
            return optionalUser.get();
        } else {
            return null;
        }

    }

}
