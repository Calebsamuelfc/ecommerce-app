package com.example.ecommercemart.service;


import com.example.ecommercemart.model.User;
import com.example.ecommercemart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;



    public User authenticate(String email, String password){

        return userRepository.findByEmailAndPassword(email, password).orElse(null);
    }


    public void addUser(User user){

        userRepository.save(user);
    }

}
