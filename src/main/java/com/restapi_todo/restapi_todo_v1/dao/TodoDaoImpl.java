package com.restapi_todo.restapi_todo_v1.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.restapi_todo.restapi_todo_v1.model.Todo;
import com.restapi_todo.restapi_todo_v1.repositories.TodoRepository;

import jakarta.persistence.EntityNotFoundException;

//Data Access Object layer for DB Operations
@Service
public class TodoDaoImpl implements TodoDao {

	@Autowired
	TodoRepository todoRepository;
	
	@Override
	public List<Todo> getTodos() {		
		return todoRepository.findAll();		
	}

	
	@Override public void insert(Todo todo) 
	{ 
		todoRepository.save(todo);
	}
	
	@Override public void updateTodo(Todo todo) { 		
		todoRepository.findById(todo.getId()).orElseThrow(
				() -> new EntityNotFoundException("Todo with id " + todo.getId() + " not found"));		
			todoRepository.save(todo);
	}
		
	
	@Override public void deleteTodo(Long todoId) { 
		todoRepository.findById(todoId).orElseThrow(
				() -> new EntityNotFoundException("Todo with id " + todoId + " not found"));
		todoRepository.deleteById(todoId);		  
		  }


	@Override
	public Optional<Todo> getTodoById(Long id) {	
		Optional<Todo> todo=  todoRepository.findById(id);
		return todo;
	}
	
	
}
