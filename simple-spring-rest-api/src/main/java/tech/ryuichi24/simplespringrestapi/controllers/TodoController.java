package tech.ryuichi24.simplespringrestapi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tech.ryuichi24.simplespringrestapi.models.TodoItem;

@RestController
public class TodoController {

    private final List<TodoItem> _todoItems = new ArrayList<>() {
        {
            add(new TodoItem(1, "todo 1"));
            add(new TodoItem(2, "todo 2"));
            add(new TodoItem(3, "todo 3"));
        }
    };

    // get todos
    @RequestMapping(method = RequestMethod.GET, path = "/todos")
    public List<TodoItem> getTodoItems() {
        return _todoItems;
    }

    // get todo
    @RequestMapping(method = RequestMethod.GET, path = "/todos/{id}")
    public TodoItem getTodoItem(@PathVariable int id) {
        TodoItem found = _getTodoItemById(id);
        if (found == null) {
            // return 404
        }

        return found;
    }

    // create todo
    @RequestMapping(method = RequestMethod.POST, path = "/todos")
    public TodoItem createTodoItem(@RequestBody TodoItem todoItem) {
        // TODO: generate id
        todoItem.setId(4);
        _todoItems.add(todoItem);
        return todoItem;
    }

    // update todo
    @RequestMapping(method = RequestMethod.PUT, path = "/todos/{id}")
    public TodoItem updateTodoItem(@RequestBody TodoItem todoItem, @PathVariable int id) {
        TodoItem found = _getTodoItemById(id);
        if (found == null) {
            // return 404
        }

        _todoItems.remove(found);
        _todoItems.add(todoItem);

        return todoItem;
    }

    // delete todo
    @RequestMapping(method = RequestMethod.DELETE, path = "/todos/{id}")
    public void removeTodoItem(@PathVariable int id) {
        TodoItem found = _getTodoItemById(id);
        if (found == null) {
            // return 404
        }
        _todoItems.remove(found);
    }

    private TodoItem _getTodoItemById(int id) {
        return _todoItems.stream().filter(item -> item.getId() == id).findAny().orElse(null);
    }

}
