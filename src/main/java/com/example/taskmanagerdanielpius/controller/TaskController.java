package com.example.taskmanagerdanielpius.controller;

import com.example.taskmanagerdanielpius.service.TaskService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/user/tasks")
    public String userTasks(Model model, Authentication auth) {
        model.addAttribute("tasks", taskService.getTasksForUser(auth.getName()));
        return "user-tasks";
    }

    @PostMapping("/user/add")
    public String addTask(@RequestParam String title,
                          @RequestParam String description,
                          Authentication auth) {
        taskService.addTask(title, description, auth.getName());
        return "redirect:/user/tasks";
    }

    @PostMapping("/user/complete/{id}")
    public String completeTask(@PathVariable Integer id, Authentication auth) {
        taskService.markCompleted(id, auth.getName());
        return "redirect:/user/tasks";
    }

    @GetMapping("/admin/tasks")
    public String adminTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "admin-tasks";
    }

    @PostMapping("/admin/delete/{id}")
    public String deleteTask(@PathVariable Integer id) {
        taskService.deleteTask(id);
        return "redirect:/admin/tasks";
    }
}
