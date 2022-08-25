package tech.ryuichi24.simplespringrestapi.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = TodoController.BASE_URL)
@RestController
public class TodoController {
    public static final String BASE_URL = "/api/v1/todos";

    private final List<String> _todoItems = new ArrayList<>() {
        {
            add("todo 1");
            add("todo 2");
            add("todo 3");
        }
    };

    @RequestMapping(method = RequestMethod.GET, path = "")
    public ResponseEntity<List<String>> getTodoItems() {
        return ResponseEntity.ok(this._todoItems);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<String> getTodoItem(@PathVariable int id) {
        int targetIndex = id - 1;
        return ResponseEntity.ok(this._todoItems.get(targetIndex));
    }

    @RequestMapping(method = RequestMethod.POST, path = "")
    public ResponseEntity<String> createTodoItem(@RequestBody Map<String, Object> newTodoItem) {
        String newTodoItemTitle = (String) newTodoItem.get("title");
        return ResponseEntity.ok(newTodoItemTitle);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<?> updateTodoItem(@PathVariable int id) {
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<String> removeTodoItem(@PathVariable int id) {
        return ResponseEntity.noContent().build();
    }
}
