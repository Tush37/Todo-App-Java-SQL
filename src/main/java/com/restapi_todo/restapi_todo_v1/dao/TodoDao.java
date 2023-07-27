package com.restapi_todo.restapi_todo_v1.dao;

import java.util.List;
import java.util.Optional;
import com.restapi_todo.restapi_todo_v1.model.Todo;

//Data Access Object layer for DB Operations
public interface TodoDao {
   
    List<Todo> getTodos();
    Optional<Todo> getTodoById(Long id);
    
    void  insert(Todo todo);

   void updateTodo(Todo todo);

    void deleteTodo(Long todoId);
    
    
}