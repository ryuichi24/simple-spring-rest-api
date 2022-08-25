package tech.ryuichi24.simplespringrestapi.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    public List<String> getTodoItems() {
        return this._todoItems;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String getTodoItem(@PathVariable int id) {
        int targetIndex = id - 1;
        return this._todoItems.get(targetIndex);
    }

    @RequestMapping(method = RequestMethod.POST, path = "")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String createTodoItem(@RequestBody Map<String, Object> newTodoItem) {
        String newTodoItemTitle = (String) newTodoItem.get("title");
        return String.format("created: %s", newTodoItemTitle);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public String updateTodoItem(@PathVariable int id) {
        return String.format("updated: %s", id);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public String removeTodoItem(@PathVariable int id) {
        return String.format("deleted: %s", id);
    }
}
