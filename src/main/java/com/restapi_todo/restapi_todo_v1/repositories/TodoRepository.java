package com.restapi_todo.restapi_todo_v1.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restapi_todo.restapi_todo_v1.model.Todo;




public interface TodoRepository extends JpaRepository<Todo, Long> {
  //Todo findById(int id);
}