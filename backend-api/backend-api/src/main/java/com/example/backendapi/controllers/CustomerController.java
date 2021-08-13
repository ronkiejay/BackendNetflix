package com.example.backendapi.controllers;


import com.example.backendapi.CustomizedResponse;
import com.example.backendapi.models.Customer;
import com.example.backendapi.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
public class CustomerController
{
    @Autowired
    private CustomerService customerService;


    //No 1: Adding customer to the database
    @PostMapping(value="/customers", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity addScene(@RequestBody Customer customer)
    {

        CustomizedResponse customizedResponse;
        try
        {
            customizedResponse = new CustomizedResponse("Your information is successfully added as displayed below: ", Collections.singletonList(customerService.insertIntoCustomer(customer))
            );


        }
        catch (Exception e)
        {
            customizedResponse = new CustomizedResponse(e.getMessage(), null);

            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }


    //No 2: Getting Customer by ID
    @GetMapping("/customers/{customerId}")
    public ResponseEntity getAMovie(@PathVariable("customerId") String id)
    {
        CustomizedResponse customizedResponse = null;
        try
        {
            customizedResponse = new CustomizedResponse("Customer with id " + id, Collections.singletonList(customerService.getACustomer(id)));
        }
        catch (Exception e)
        {
            customizedResponse = new CustomizedResponse(e.getMessage(), null);

            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

}
