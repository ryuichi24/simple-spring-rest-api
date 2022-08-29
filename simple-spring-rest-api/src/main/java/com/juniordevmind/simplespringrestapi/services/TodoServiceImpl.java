package com.juniordevmind.simplespringrestapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.juniordevmind.simplespringrestapi.dataaccess.TodoRepository;
import com.juniordevmind.simplespringrestapi.errors.NotFoundException;
import com.juniordevmind.simplespringrestapi.models.TodoItem;

@Service
@Primary
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository _todoRepository;

    @Override
    public TodoItem saveTodoItem(TodoItem todoItem) {
        return _todoRepository.save(todoItem);
    }

    @Override
    public List<TodoItem> getTodoItems() {
        return _todoRepository.findAll();
    }

    @Override
    public TodoItem getTodoItemById(int id) {
        return _findTodoItemById(id);
    }

    @Override
    public void removeTodoItemById(int id) {
        _todoRepository.deleteById(id);
    }

    @Override
    public TodoItem updateTodoItem(int id, TodoItem todoItem) {
        return _todoRepository.save(todoItem);
    }

    private TodoItem _findTodoItemById(int id) throws NotFoundException {
        Optional<TodoItem> found = _todoRepository.findById(id);
        if (!found.isPresent()) {
            throw new NotFoundException("The todo item is not available.");
        }
        return found.get();
    }

}
