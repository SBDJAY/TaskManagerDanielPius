package com.example.taskmanagerdanielpius.service;

import com.example.taskmanagerdanielpius.model.TaskModel;

import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class TaskService {

    private List<TaskModel> tasks = new ArrayList<>();
    private int idCounter = 1;

    public List<TaskModel> getTasksForUser(String username) {
        return tasks.stream()
                .filter(task -> task.getOwnerUsername().equals(username))
                .toList();
    }

    public List<TaskModel> getAllTasks() {
        return tasks;
    }

    public void addTask(TaskModel task, String username) {
        task.setId(idCounter++);
        task.setOwnerUsername(username);
        task.setCompleted(false);
        tasks.add(task);
    }

    public void markComplete(int id, String username) {
        for (TaskModel task : tasks) {
            if (task.getId() == id &&
                    task.getOwnerUsername().equals(username)) {
                task.setCompleted(true);
            }
        }
    }

    public void deleteTask(int id) {
        tasks.removeIf(task -> task.getId() == id);
    }
}
