package com.curse.rest.webservices.restfulwebservices.model;

import javax.validation.constraints.Size;

public class User {

    private Integer id;

    @Size(min = 2, message = "name should have at least 2 characters")
    private String name;

    public User() {
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
