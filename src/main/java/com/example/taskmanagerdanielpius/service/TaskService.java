package com.example.taskmanagerdanielpius.service;

import com.example.taskmanagerdanielpius.model.TaskModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private List<TaskModel> tasks = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger();

    public List<TaskModel> getTasksForUser(String username) {
        return tasks.stream()
                .filter(task -> task.getOwnerUsername().equals(username))
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<TaskModel> getAllTasks() {
        return tasks;
    }

    public void addTask(String title, String description, String username) {
        tasks.add(new TaskModel(idCounter.incrementAndGet(), title, description, false, username));
    }

    public void markCompleted(Integer id, String username) {
        tasks.stream()
                .filter(task -> task.getId().equals(id) && task.getOwnerUsername().equals(username))
                .findFirst()
                .ifPresent(task -> task.setCompleted(true));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteTask(Integer id) {
        tasks.removeIf(task -> task.getId().equals(id));
    }

}
