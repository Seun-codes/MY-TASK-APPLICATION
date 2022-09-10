package com.elizabeth.mytodoapp.controller;


import com.elizabeth.mytodoapp.DTO.TodoDto;
import com.elizabeth.mytodoapp.DTO.UserDto;
import com.elizabeth.mytodoapp.model.TodoItem;
import com.elizabeth.mytodoapp.model.User;
import com.elizabeth.mytodoapp.service.ToDoService;
import com.elizabeth.mytodoapp.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/user")
public class TodoFormController {


   private ToDoService toDoService;
   private UserServiceImpl userService;
@Autowired
    public TodoFormController(ToDoService toDoService, UserServiceImpl userService) {
        this.toDoService = toDoService;
        this.userService = userService;
    }





    @GetMapping("/dashboard")
    public String index(Model model, HttpSession session){
        List<TodoItem> allTasks = toDoService.allTaskByUserId((Integer) session.getAttribute("id"));
        model.addAttribute("newTaskDetails" , new TodoDto());
        model.addAttribute("tasks" , allTasks);
        return "dashboard";
    }

    @GetMapping(value = "/task/{status}")
    public String taskByStatus(@PathVariable(name = "status") String status , Model model , HttpSession session){
        int user_id = (int) session.getAttribute("id");
        List<TodoItem> listOfTaskByStatus = toDoService.findAllByUser_idAndStatus(user_id , status);
        model.addAttribute("tasksByStatus" , listOfTaskByStatus);
        return "task_by-status";
    }
    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable(name = "id") int id){
        toDoService.delete(id);
        return "redirect:/user/dashboard";
    }

    @GetMapping(value = "/editPage/{id}")
    public String showEditPage(@PathVariable(name = "id") int id , Model model){
        TodoItem todoItem = toDoService.findById(id);
        model.addAttribute("singleTask" , todoItem);
        model.addAttribute("taskBody", new TodoDto());
        return  "edit";
    }

    @PostMapping(value = "/edit/{id}")
    public String editTask(@PathVariable( name = "id") String id , @ModelAttribute TodoDto todoDto){
        int  todoId = Integer.parseInt(id);
        toDoService.updateTitleAndDescription(todoDto , todoId);
        return "redirect:/user/dashboard";
    }
    @GetMapping(value = "/addNewTask")
    public String addTask(Model model){
        model.addAttribute("newTask" , new TodoDto());
        return "addTask";
    }
    @PostMapping(value = "/addTask")
    public String CreateTask(@ModelAttribute TodoDto todoDto){
        userService.crateTask(todoDto);
        return "redirect:/user/dashboard";
    }

    @PostMapping(value = "/changeStatus/{id}")
    public String moveToInProgress(@PathVariable(name = "id")   String id, @RequestParam String status ){
        int taskId = Integer.parseInt(id);
        toDoService.updateTaskStatus(status, taskId);
        return "redirect:/user/dashboard";
    }

    @PostMapping(value = "/complete/{id}")
    public String complete(@PathVariable(name = "id")   String id){
        int taskId = Integer.parseInt(id);
        toDoService.markCompleted(taskId);
        return "redirect:/user/dashboard";
    }

    @GetMapping(value = "/singleTask/{id}")
    public String getSingleTask(@PathVariable(name = "id") String id , Model model){
       TodoItem todoItem = toDoService.findById(Integer.parseInt(id));
        model.addAttribute("task" , todoItem);
        return "singleTask";
    }
@GetMapping("/logout")
    public String logout(HttpSession httpSession){
    httpSession.invalidate();
    return "redirect:/user/login";
    }



}
