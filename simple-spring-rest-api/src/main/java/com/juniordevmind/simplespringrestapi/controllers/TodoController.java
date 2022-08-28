package com.juniordevmind.simplespringrestapi.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.juniordevmind.simplespringrestapi.models.TodoItem;

@RequestMapping(path = TodoController.BASE_URL)
@RestController
public class TodoController {
    public static final String BASE_URL = "/api/v1/todos";
    private final AtomicInteger _counter = new AtomicInteger();
    private final List<TodoItem> _todoItems = new ArrayList<>() {
        {
            add(new TodoItem(_counter.incrementAndGet(), "todo 1"));
            add(new TodoItem(_counter.incrementAndGet(), "todo 2"));
            add(new TodoItem(_counter.incrementAndGet(), "todo 3"));
        }
    };

    @RequestMapping(method = RequestMethod.GET, path = "")
    public ResponseEntity<List<TodoItem>> getTodoItems() {
        return ResponseEntity.ok(_todoItems);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<TodoItem> getTodoItem(@PathVariable int id) {
        TodoItem found = _findTodoItemById(id);
        return ResponseEntity.ok(found);
    }

    @RequestMapping(method = RequestMethod.POST, path = "")
    public ResponseEntity<TodoItem> createTodoItem(@RequestBody TodoItem newTodoItem) {
        if (Objects.isNull(newTodoItem.getTitle())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title must not be null.");
        }
        newTodoItem.setId(_counter.incrementAndGet());
        _todoItems.add(newTodoItem);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newTodoItem.getId()).toUri();
        return ResponseEntity.created(location).body(newTodoItem);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<?> updateTodoItem(@PathVariable int id, @RequestBody TodoItem newTodoItem) {
        TodoItem found = _findTodoItemById(id);
        _todoItems.remove(found);
        _todoItems.add(newTodoItem);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<?> removeTodoItem(@PathVariable int id) {
        TodoItem found = _findTodoItemById(id);
        _todoItems.remove(found);
        return ResponseEntity.noContent().build();
    }

    private TodoItem _findTodoItemById(int id) {
        Optional<TodoItem> found = _todoItems.stream().filter(item -> item.getId() == id).findAny();
        if (!found.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
        }
        return found.get();
    }
}
