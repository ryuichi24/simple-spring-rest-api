package com.juniordevmind.simplespringrestapi.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.juniordevmind.simplespringrestapi.errors.BadRequestException;
import com.juniordevmind.simplespringrestapi.errors.NotFoundException;
import com.juniordevmind.simplespringrestapi.models.TodoItem;

@Service
public class TodoServiceImplWithoutRepo implements TodoService {
    private final AtomicInteger _counter = new AtomicInteger();
    private final List<TodoItem> _todoItems = new ArrayList<>() {
        {
            add(new TodoItem(_counter.incrementAndGet(), "todo 1", LocalDateTime.now(), LocalDateTime.now()));
            add(new TodoItem(_counter.incrementAndGet(), "todo 2", LocalDateTime.now(), LocalDateTime.now()));
            add(new TodoItem(_counter.incrementAndGet(), "todo 3", LocalDateTime.now(), LocalDateTime.now()));
        }
    };

    @Override
    public TodoItem saveTodoItem(TodoItem todoItem) throws BadRequestException {
        todoItem.setId(_counter.incrementAndGet());
        todoItem.setCreatedAt(LocalDateTime.now());
        todoItem.setUpdatedAt(LocalDateTime.now());
        _todoItems.add(todoItem);
        return todoItem;
    }

    @Override
    public List<TodoItem> getTodoItems() {
        return this._todoItems;
    }

    @Override
    public TodoItem getTodoItemById(int id) {
        return _findTodoItemById(id);
    }

    @Override
    public void removeTodoItemById(int id) {
        TodoItem found = _findTodoItemById(id);
        _todoItems.remove(found);
    }

    @Override
    public TodoItem updateTodoItem(int id, TodoItem todoItem) {
        TodoItem found = _findTodoItemById(id);
        _todoItems.remove(found);
        todoItem.setUpdatedAt(LocalDateTime.now());
        _todoItems.add(todoItem);
        return todoItem;
    }

    private TodoItem _findTodoItemById(int id) throws NotFoundException {
        Optional<TodoItem> found = _todoItems.stream().filter(item -> item.getId() == id).findAny();
        if (!found.isPresent()) {
            throw new NotFoundException("The todo item is not available.");
        }
        return found.get();
    }
}
