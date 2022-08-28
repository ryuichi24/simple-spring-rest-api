package com.juniordevmind.simplespringrestapi.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.juniordevmind.simplespringrestapi.models.TodoItem;
import com.juniordevmind.simplespringrestapi.services.TodoService;

@RequestMapping(path = TodoController.BASE_URL)
@RestController
public class TodoController {
    public static final String BASE_URL = "/api/v1/todos";

    @Autowired
    private TodoService _todoService;

    @GetMapping(path = "")
    public ResponseEntity<List<TodoItem>> getTodoItems() {
        List<TodoItem> todoItems = _todoService.getTodoItems();
        return ResponseEntity.ok(todoItems);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TodoItem> getTodoItem(@PathVariable int id) {
        TodoItem found = _todoService.getTodoItemById(id);
        return ResponseEntity.ok(found);
    }

    @PostMapping(path = "")
    public ResponseEntity<TodoItem> createTodoItem(@RequestBody TodoItem newTodoItem) {
        TodoItem savedTodoItem = _todoService.saveTodoItem(newTodoItem);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedTodoItem.getId()).toUri();
        return ResponseEntity.created(location).body(savedTodoItem);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateTodoItem(@PathVariable int id, @RequestBody TodoItem newTodoItem) {
        _todoService.updateTodoItem(id, newTodoItem);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> removeTodoItem(@PathVariable int id) {
        _todoService.removeTodoItemById(id);
        return ResponseEntity.noContent().build();
    }
}
