package com.sunbase.models;

import java.sql.Timestamp;

public class Blog {
    private int id;
    private String title;
    private String content;
    private String imageVideoPath;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private int authorId;

    // Empty constructor
    public Blog() {}

    // Constructor for creating a new blog post
    public Blog(String title, String content, String imageVideoPath, int authorId) {
        this.title = title;
        this.content = content;
        this.imageVideoPath = imageVideoPath;
        this.authorId = authorId;
    }

    // Constructor for retrieving a blog from the database
    public Blog(int id, String title, String content, String imageVideoPath, Timestamp createdAt, Timestamp updatedAt, int authorId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.imageVideoPath = imageVideoPath;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.authorId = authorId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageVideoPath() {
        return imageVideoPath;
    }

    public void setImageVideoPath(String imageVideoPath) {
        this.imageVideoPath = imageVideoPath;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
}
