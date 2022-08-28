package com.juniordevmind.simplespringrestapi.models;

import javax.validation.constraints.NotBlank;

public class TodoItem {
    private int id;
    @NotBlank(message = "Title must not be blank.")
    private String title;

    public TodoItem() {
    }

    public TodoItem(int id, String title) {
        this.id = id;
        this.title = title;
    }

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
}
