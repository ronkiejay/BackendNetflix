package com.example.backendapi.controllers;

import com.example.backendapi.CustomizedResponse;
import com.example.backendapi.models.UserModel;
import com.example.backendapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
public class UserController
{
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity getUsers()
    {

        var response = new CustomizedResponse("A list of all users ", userService.getUsers());

        return new ResponseEntity(response, HttpStatus.OK);

    }

    @GetMapping("/users/{id}")
    public ResponseEntity getAUser(@PathVariable("id") String id)
    {

        CustomizedResponse customizedResponse = null;
        try
        {
            customizedResponse = new CustomizedResponse("User with id " + id, Collections.singletonList(userService.getAUser(id)));
        }
        catch (Exception e)
        {
            customizedResponse = new CustomizedResponse(e.getMessage(), null);

            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/users", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity createUser(@RequestBody UserModel user)
    {

        var response = new CustomizedResponse("User created successfully", Collections.singletonList(userService.addUser(user)));

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
