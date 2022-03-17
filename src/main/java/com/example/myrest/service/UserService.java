package com.example.myrest.service;

import com.example.myrest.model.Role;
import com.example.myrest.model.TB_User;
import com.example.myrest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public TB_User save(TB_User user)  {

        System.out.println("############     user.getPassword              ####################");
        System.out.println(user.getPassword());
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(true);
        Role role= new Role();
        role.setId(1l);
        user.getRoles().add(role);
        return userRepository.save(user);
    }


    //    public HashMap<String, Object> usernameOverlap(String username) {
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("result", userRepository.existsByUsername(username));
//        return map;
//    }
    public HashMap<String, Object> usernameOverlap(String username) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("1", userRepository.existsByUsername(username));
        return map;
    }


}

