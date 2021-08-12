package com.example.backendapi.services;


import com.example.backendapi.models.Customer;
import com.example.backendapi.models.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements UserDetailsService
{
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //No 1. Endpoint to allow a user to Register
    public void insertIntoCustomer(Customer customer)
    {
        String encodedPassword = bCryptPasswordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);

        customerRepository.insert(customer);
    }

    //No 2. Endpoint for a specific Customer
    public Optional<Customer> getACustomer(String id) throws Exception
    {
        Optional <Customer> customer = customerRepository.findById(id);

        if(!customer.isPresent())
        {
            throw new Exception("Customer with id " + id + " does not exist");
        }

        return customer;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }


}
