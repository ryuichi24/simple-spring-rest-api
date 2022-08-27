package tech.ryuichi24.simplespringrestapi.services;

import java.util.List;

import tech.ryuichi24.simplespringrestapi.models.TodoItem;

public interface TodoService {
    public TodoItem saveTodoItem(TodoItem todoItem);

    public List<TodoItem> getTodoItems();

    public TodoItem getTodoItemById(int id);

    public void removeTodoItemById(int id);

    public TodoItem updateTodoItem(int id, TodoItem todoItem);
}
