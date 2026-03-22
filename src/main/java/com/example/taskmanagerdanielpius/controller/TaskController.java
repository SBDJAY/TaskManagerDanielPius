package com.example.taskmanagerdanielpius.controller;

import com.example.taskmanagerdanielpius.model.TaskModel;
import com.example.taskmanagerdanielpius.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;


@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/user/tasks")
    public String userTasks(Model model, Principal principal) {
        model.addAttribute("tasks",
                taskService.getTasksForUser(principal.getName()));
        model.addAttribute("task", new TaskModel());
        return "user";
    }

    @PostMapping("/user/add")
    public String addTask(@ModelAttribute("task") TaskModel task,
                          Principal principal,
                          RedirectAttributes redirectAttributes) {

        taskService.addTask(task, principal.getName());
        redirectAttributes.addFlashAttribute("success", "Task added successfully!");
        return "redirect:/user/tasks";
    }

    @PostMapping("/user/complete/{id}")
    public String completeTask(@PathVariable int id,
                               Principal principal,
                               RedirectAttributes redirectAttributes) {

        taskService.markComplete(id, principal.getName());
        redirectAttributes.addFlashAttribute("success", "Task marked as completed!");
        return "redirect:/user/tasks";
    }

    @GetMapping("/admin/tasks")
    public String adminTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "admin";
    }

    @PostMapping("/admin/delete/{id}")
    public String deleteTask(@PathVariable int id,
                             RedirectAttributes redirectAttributes) {

        taskService.deleteTask(id);
        redirectAttributes.addFlashAttribute("success", "Task deleted!");
        return "redirect:/admin/tasks";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }
}
