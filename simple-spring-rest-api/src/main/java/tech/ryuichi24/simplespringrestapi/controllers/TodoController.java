package tech.ryuichi24.simplespringrestapi.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tech.ryuichi24.simplespringrestapi.models.TodoItem;
import tech.ryuichi24.simplespringrestapi.services.TodoService;

@RequestMapping(path = TodoController.BASE_URL)
@RestController
public class TodoController {
    public static final String BASE_URL = "/api/v1/todos";

    @Autowired
    private TodoService _todoService;

    @RequestMapping(method = RequestMethod.GET, path = "")
    public ResponseEntity<List<TodoItem>> getTodoItems() {
        List<TodoItem> todoItems = _todoService.getTodoItems();
        return ResponseEntity.ok(todoItems);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<TodoItem> getTodoItem(@PathVariable int id) {
        TodoItem found = _todoService.getTodoItemById(id);
        return ResponseEntity.ok(found);
    }

    @RequestMapping(method = RequestMethod.POST, path = "")
    public ResponseEntity<TodoItem> createTodoItem(@RequestBody TodoItem newTodoItem) {
        TodoItem savedTodoItem = _todoService.saveTodoItem(newTodoItem);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedTodoItem.getId()).toUri();

        return ResponseEntity.created(location).body(savedTodoItem);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<?> updateTodoItem(@PathVariable int id, @RequestBody TodoItem newTodoItem) {
        _todoService.updateTodoItem(id, newTodoItem);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<?> removeTodoItem(@PathVariable int id) {
        _todoService.removeTodoItemById(id);
        return ResponseEntity.noContent().build();
    }
}
