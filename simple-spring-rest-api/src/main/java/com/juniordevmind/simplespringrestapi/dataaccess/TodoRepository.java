package com.juniordevmind.simplespringrestapi.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juniordevmind.simplespringrestapi.models.TodoItem;

@Repository
public interface TodoRepository extends JpaRepository<TodoItem, Integer> {

}
