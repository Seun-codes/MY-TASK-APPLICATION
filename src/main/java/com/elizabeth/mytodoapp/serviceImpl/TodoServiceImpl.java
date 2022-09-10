package com.elizabeth.mytodoapp.serviceImpl;

import com.elizabeth.mytodoapp.DTO.TodoDto;
import com.elizabeth.mytodoapp.Repository.TodoRepository;
import com.elizabeth.mytodoapp.exceptions.CustomException;
import com.elizabeth.mytodoapp.model.TodoItem;
import com.elizabeth.mytodoapp.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TodoServiceImpl implements ToDoService {

    TodoRepository todoRepository;
@Autowired
    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public TodoItem findById(int id) {
        TodoItem todoItem = todoRepository.findById(id).
                orElseThrow(()-> new CustomException("Todo id " + id + "not found"));
        return todoItem;
    }

    @Override
    public Boolean delete(int id) {
        if(this.findById(id) == null){
            return false;
        }
        todoRepository.deleteById(id);
        return true;
    }

    @Override
    public TodoItem updateTitleAndDescription(TodoDto todoDto, int id) {
        TodoItem todoItem = findById(id);
        todoItem.setTitle(todoDto.getTitle());
        todoItem.setDescription(todoDto.getDescription());
        return todoRepository.save(todoItem);

    }

    @Override
    public List<TodoItem> viewAllTodo() {
        return todoRepository.findAll();
    }

    @Override
    public List<TodoItem> allTaskByUserId(int id) {
        return todoRepository.findAllUserById(id);
    }

    @Override
    public List<TodoItem> viewAllTaskByStatus(String status) {
        return todoRepository.listOfTodoByStatus(status);
    }

    @Override
    public int updateTaskStatus(String status, int id) {
        return todoRepository.updateTodoByIdAndStatus(status,id);
    }

    @Override
    public List<TodoItem> findAllByUser_idAndStatus( int id, String status) {
        return todoRepository.findUserByIdAndStatus(status,id);
    }

    @Override
    public TodoItem markCompleted(int id) {
    TodoItem todoItem = findById(id);
    if(todoItem.getStatus().equals("inprogress")){
        todoItem.setCompletedAt(LocalDateTime.now());
        todoItem.setStatus("completed");
    }
        return todoRepository.save(todoItem);
    }


}
