package com.elizabeth.mytodoapp.service;

import com.elizabeth.mytodoapp.DTO.TodoDto;
import com.elizabeth.mytodoapp.model.TodoItem;

import java.util.List;

public interface ToDoService {

    TodoItem findById(int id);

    public Boolean delete(int id);

    TodoItem updateTitleAndDescription(TodoDto todoDto, int id);
    List<TodoItem> viewAllTodo();

    List<TodoItem> allTaskByUserId(int id);

    List<TodoItem> viewAllTaskByStatus(String status);

    int updateTaskStatus(String status, int id);
    List<TodoItem> findAllByUser_idAndStatus(int id , String status);

    TodoItem markCompleted(int id);



}
