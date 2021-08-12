package com.example.backendapi.services;

import com.example.backendapi.models.Customer;
import com.example.backendapi.models.UserModel;
import com.example.backendapi.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserModel addUser(UserModel user)
    {
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        UserModel addedUser = userRepository.insert(user);

        return addedUser;
    }

    public List<UserModel> getUsers()
    {

        return userRepository.findAll();
    }

    public Optional<UserModel> getAUser(String id) throws Exception
    {

        Optional <UserModel> user = userRepository.findById(id);

        if(!user.isPresent())
        {
            throw new Exception("User with id " + id + " does not exist");
        }

        return user;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserModel validUser = userRepository.findByUsername(username);

        String userName = validUser.getUsername();
        String passWord = validUser.getPassword();

        return new User(userName, passWord, new ArrayList<>());
    }
}
