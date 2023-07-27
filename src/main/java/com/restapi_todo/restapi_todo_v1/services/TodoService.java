package com.restapi_todo.restapi_todo_v1.services;

import java.util.List;
import com.restapi_todo.restapi_todo_v1.dto.CreateTodoDto;
import com.restapi_todo.restapi_todo_v1.dto.TodoDto;
import com.restapi_todo.restapi_todo_v1.dto.UpdateTodoDto;
import com.restapi_todo.restapi_todo_v1.model.Todo;

public interface TodoService {
   
    List<Todo> getTodos();
    
    TodoDto getTodoById(Long todoId);
    
    void  insert(CreateTodoDto todo);

    void updateTodo(UpdateTodoDto todo);

    void deleteTodo(Long todoId);
}