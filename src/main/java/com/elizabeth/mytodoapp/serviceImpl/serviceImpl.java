package com.elizabeth.mytodoapp;

import com.elizabeth.mytodoapp.DTO.UserDto;
import com.elizabeth.mytodoapp.Repository.UserRepository;
import com.elizabeth.mytodoapp.model.User;
import com.elizabeth.mytodoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class serviceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User create(UserDto user) {
        User users = new User();
        users.setFirstName(user.getFirstName());
        users.setLastName(user.getLastName());
        users.setEmail(user.getEmail());
        users.setPassword(user.getPassword());
        return userRepository.save(users);
    }

    @Override
    public User Login(String email, String password) {
        User user = userRepository.findByEmailAndPasswordAnd(email,password);
        return user;
    }
}
