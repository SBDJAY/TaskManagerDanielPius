package com.example.taskmanagerdanielpius.model;

public class TaskModel {
    private Integer id;
    private String title;
    private String description;
    private Boolean completed;
    private String ownerUsername;


    public TaskModel(Integer id, String title, String description, Boolean completed, String ownerUsername) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.ownerUsername = ownerUsername;
    }

    public Integer getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public Boolean isCompleted() { return completed; }
    public String getOwnerUsername() { return ownerUsername; }

    public void setCompleted(Boolean completed) { this.completed = completed; }
}
