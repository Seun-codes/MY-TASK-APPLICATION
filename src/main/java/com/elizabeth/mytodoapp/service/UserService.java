package com.elizabeth.mytodoapp.service;

import com.elizabeth.mytodoapp.DTO.TodoDto;
import com.elizabeth.mytodoapp.DTO.UserDto;
import com.elizabeth.mytodoapp.model.TodoItem;
import com.elizabeth.mytodoapp.model.User;

import java.util.List;

public interface UserService {
    User create(UserDto user);
    public String Login(String email, String password);

    TodoItem crateTask(TodoDto todoDto);



    User getUserByEmail(String email);

}
