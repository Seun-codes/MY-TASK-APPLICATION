package com.elizabeth.mytodoapp.serviceImpl;

import com.elizabeth.mytodoapp.DTO.TodoDto;
import com.elizabeth.mytodoapp.DTO.UserDto;
import com.elizabeth.mytodoapp.Repository.TodoRepository;
import com.elizabeth.mytodoapp.Repository.UserRepository;
import com.elizabeth.mytodoapp.model.TodoItem;
import com.elizabeth.mytodoapp.model.User;
import com.elizabeth.mytodoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

   private UserRepository userRepository;

    private TodoRepository todoRepository;
@Autowired
    public UserServiceImpl(UserRepository userRepository, TodoRepository todoRepository) {
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
    }

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(UserDto user) {
        User users = new User();
        users.setEmail(user.getEmail());
        users.setPassword(user.getPassword());
       users.setName(user.getName());
        return userRepository.save(users);
    }

    @Override
    public String Login(String email, String password) {

        String message = "";
        User user = getUserByEmail(email);
        if (user.getPassword().equals(password)){
            message = "Success";
        }else {
            message = "incorrect password";
        }
        return message;
    }

    @Override
    public TodoItem crateTask(TodoDto todoDto) {
        TodoItem todoItem1 = new TodoItem();
        todoItem1.setDescription(todoDto.getDescription());
        todoItem1.setStatus(todoDto.getStatus());
        todoItem1.setTitle(todoDto.getTitle());
        todoItem1.setUser(userRepository.findById(todoDto.getUser_id()).get());
        return  todoRepository.save(todoItem1);
    }






    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
