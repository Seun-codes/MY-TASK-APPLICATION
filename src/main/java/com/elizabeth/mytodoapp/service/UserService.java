package com.elizabeth.mytodoapp.service;

import com.elizabeth.mytodoapp.DTO.UserDto;
import com.elizabeth.mytodoapp.model.User;

public interface UserService {
    User create(UserDto user);
    User Login(String email, String password);

}
