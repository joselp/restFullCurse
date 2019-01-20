package com.curse.rest.webservices.restfulwebservices.controller;

import com.curse.rest.webservices.restfulwebservices.model.Name;
import com.curse.rest.webservices.restfulwebservices.model.PersonV1;
import com.curse.rest.webservices.restfulwebservices.model.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @GetMapping("/v1/person")
    public PersonV1 getPerson(){

        return new PersonV1("Jose Luis");
    }

    @GetMapping("/v2/person")
    public PersonV2 getPersonV2(){

        return new PersonV2(new Name("Jose", "Perez"));
    }


    @GetMapping(value = "/person/param", params = "version=v1")
    public PersonV1 paramV1(){

        return new PersonV1("Jose Luis");
    }

    @GetMapping(value = "/person/param", params = "version=v2")
    public PersonV2 paramv2(){

        return new PersonV2(new Name("Jose", "Perez"));
    }
}
