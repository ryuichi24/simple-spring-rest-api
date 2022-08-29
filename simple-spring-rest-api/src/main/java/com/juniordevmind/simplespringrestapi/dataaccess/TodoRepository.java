package com.juniordevmind.simplespringrestapi.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juniordevmind.simplespringrestapi.models.TodoItem;

public interface TodoRepository extends JpaRepository<TodoItem, Integer> {

}
