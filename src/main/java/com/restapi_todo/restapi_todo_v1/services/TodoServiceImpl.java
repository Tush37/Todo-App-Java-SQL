package com.restapi_todo.restapi_todo_v1.services;

import java.util.List;
import java.util.Optional;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.restapi_todo.restapi_todo_v1.dao.TodoDao;
import com.restapi_todo.restapi_todo_v1.dto.CreateTodoDto;
import com.restapi_todo.restapi_todo_v1.dto.TodoDto;
import com.restapi_todo.restapi_todo_v1.dto.UpdateTodoDto;
import com.restapi_todo.restapi_todo_v1.model.Todo;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    TodoDao tododao;
    
    @Autowired
    private ModelMapper modelMapper;
    
   
    Logger logger = LoggerFactory.getLogger(TodoServiceImpl.class);

    // Method to retrieve all todos
    @Override
    public List<Todo> getTodos() {
        // Logging: Recording the entry into the 'getTodos' method
        logger.info("TodoServiceImpl : Getting all todos.");

        // Calling DAO for getting all records
        return tododao.getTodos();
    }


    // Method to insert a new todo
    @Override
    public void insert(CreateTodoDto todotdo) {
        // Logging: Recording the entry into the 'insert' method
        logger.info("TodoServiceImpl: Inserting a new todo.");

        // Mapping Entity to DTO
        Todo todo = this.modelMapper.map(todotdo, Todo.class);
        
        //Todo todo = new Todo();
        //todo.setTitle(todotdo.getTitle());
        //todo.setDescription(todotdo.getDescription());

        // Calling DAO for creating a new record
        tododao.insert(todo);
    }


    // Method to update an existing todo
    @Override
    public void updateTodo(UpdateTodoDto todo) {
        // Logging: Recording the entry into the 'updateTodo' method
        logger.info("TodoServiceImpl: Updating a todo.");

        // Mapping Entity to DTO
        Todo todo1 = this.modelMapper.map(todo, Todo.class);
        
        //Todo todo1 = new Todo();
        //todo1.setId(todo.getId());
        //todo1.setTitle(todo.getTitle());
        //todo1.setDescription(todo.getDescription());
        //todo1.setCompleted(todo.isCompleted());

        // Calling DAO to retrieve the existing todo by ID
        Optional<Todo> todoRecord = tododao.getTodoById(todo.getId());

        // Throwing an exception if the record is not present
        if (todoRecord.isEmpty()) {
            throw new EntityNotFoundException("Todo Record with id " + todo.getId() + " not found");
        }
        
        // Calling DAO to update the todo
        tododao.updateTodo(todo1);
    }

 

    // Method to delete a todo by ID
    @Override
    public void deleteTodo(Long todoId) {
        // Logging: Recording the entry into the 'deleteTodo' method
        logger.info("TodoServiceImpl: Deleting a todo.");

        // Calling DAO to retrieve the todo by ID
        Optional<Todo> todoRecord = tododao.getTodoById(todoId);

        // Throwing an exception if the record is not present
        if (todoRecord.isEmpty()) {
            throw new EntityNotFoundException("Todo Record with id " + todoId + " not found");
        }
        
        // Calling DAO to delete the todo
        tododao.deleteTodo(todoId);
    }

 

    // Method to retrieve a todo by ID
    @Override
    public TodoDto getTodoById(Long todoId) {
        // Logging: Recording the entry into the 'getTodoById' method
        logger.info("TodoServiceImpl: Getting a todo by ID.");

        // Calling DAO to retrieve the todo by ID
        Optional<Todo> todoRecord = tododao.getTodoById(todoId);

 

        // Throwing an exception if the record is not present
        if (todoRecord.isEmpty()) {
            throw new EntityNotFoundException("Todo Record with id " + todoId + " not found");
        } else {
        	TodoDto todoDto = this.modelMapper.map(todoRecord, TodoDto.class);
			/*
			 * TodoDto todoDto = new TodoDto(); todoDto.setId(todoRecord.get().getId());
			 * todoDto.setTitle(todoRecord.get().getTitle());
			 * todoDto.setDescription(todoRecord.get().getDescription());
			 * todoDto.setCompleted(todoRecord.get().isCompleted());
			 * todoDto.setLastModified(todoRecord.get().getLastModified());
			 * todoDto.setDateCreated(todoRecord.get().getDateCreated());
			 */
            return todoDto;
        }
    }
}