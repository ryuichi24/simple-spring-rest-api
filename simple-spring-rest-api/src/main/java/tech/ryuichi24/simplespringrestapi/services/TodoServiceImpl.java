package tech.ryuichi24.simplespringrestapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tech.ryuichi24.simplespringrestapi.models.TodoItem;

@Service
public class TodoServiceImpl implements TodoService {
    private final AtomicInteger _counter = new AtomicInteger();

    private final List<TodoItem> _todoItems = new ArrayList<>() {
        {
            add(new TodoItem(_counter.incrementAndGet(), "todo 1"));
            add(new TodoItem(_counter.incrementAndGet(), "todo 2"));
            add(new TodoItem(_counter.incrementAndGet(), "todo 3"));
        }
    };

    @Override
    public TodoItem saveTodoItem(TodoItem todoItem) {
        if (Objects.isNull(todoItem)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Todo item must not be null.");
        }

        todoItem.setId(_counter.incrementAndGet());
        _todoItems.add(todoItem);

        return todoItem;
    }

    @Override
    public List<TodoItem> getTodoItems() {
        return this._todoItems;
    }

    @Override
    public TodoItem getTodoItemById(int id) {
        TodoItem found = _findTodoItemById(id);

        return found;
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
        _todoItems.add(todoItem);

        return todoItem;
    }

    private TodoItem _findTodoItemById(int id) {
        TodoItem found = _todoItems.stream().filter(item -> item.getId() == id).findAny().orElse(null);
        if (Objects.isNull(found)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
        }

        return found;
    }

}
