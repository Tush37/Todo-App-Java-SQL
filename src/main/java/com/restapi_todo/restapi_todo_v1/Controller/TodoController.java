package com.restapi_todo.restapi_todo_v1.Controller; 

  

import java.util.List; 
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.*; 
import com.restapi_todo.restapi_todo_v1.dto.CreateTodoDto; 
import com.restapi_todo.restapi_todo_v1.dto.TodoDto; 
import com.restapi_todo.restapi_todo_v1.dto.UpdateTodoDto; 
import com.restapi_todo.restapi_todo_v1.model.Todo; 
import com.restapi_todo.restapi_todo_v1.services.TodoService; 

  

@RestController 
@RequestMapping("/api") 
public class TodoController { 

  
    @Autowired 
    TodoService todoService; 
    
    Logger logger = LoggerFactory.getLogger(TodoController.class); 

    // Endpoint to test if the API is working 

    @GetMapping("/hello-world") 
    public String helloWorld() { 
        logger.info("TodoController: Calling hello-world"); 
        return "Hello new world"; 
    } 

 
    // Get all todos 
    @GetMapping("/todos") 
    public ResponseEntity<List<Todo>> getAllTodos() { 
        try { 
            // Logging: Recording the entry into the 'getAllTodos' method 
            logger.info("TodoController: Fetching all todos."); 
            List<Todo> todos = todoService.getTodos(); 
            return new ResponseEntity<>(todos, HttpStatus.OK); 

        } catch (Exception e) { 
            logger.error("TodoController: Error occurred while fetching todos", e); 
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        } 
    } 
  

    // Get a todo by ID 
    @GetMapping("/todos/{todoId}") 
    public ResponseEntity<TodoDto> getTodosById(@PathVariable long todoId) { 
        try { 
            // Logging: Recording the entry into the 'getTodosById' method 
            logger.info("TodoController: Fetching a todo by ID."); 
            TodoDto todo = todoService.getTodoById(todoId); 
            return new ResponseEntity<>(todo, HttpStatus.OK); 

        } catch (Exception e) { 
            logger.error("Error occurred while fetching todos", e); 
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        } 
    } 

  

    // Create a new todo 
    @PostMapping(value = "/todos") 
    public ResponseEntity<CreateTodoDto> saveTodo(@RequestBody CreateTodoDto tododto) { 
        try { 
            // Logging: Recording the entry into the 'saveTodo' method 
            logger.info("TodoController: Creating a new todo."); 
            todoService.insert(tododto); 
            return new ResponseEntity<>(tododto, HttpStatus.CREATED); 
        } catch (Exception e) { 
            logger.error("Error occurred while creating todo", e); 
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        } 
    } 

  
    // Update an existing todo 
    @PutMapping(value = "/todos") 
    public ResponseEntity<UpdateTodoDto> updateTodo(@RequestBody UpdateTodoDto tododto) { 
        try { 
            // Logging: Recording the entry into the 'updateTodo' method 
            logger.info("TodoController: Updating a todo."); 
            todoService.updateTodo(tododto); 
            return new ResponseEntity<>(tododto, HttpStatus.OK); 
        } catch (Exception e) { 
            logger.error("Error occurred while updating todo", e); 
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        } 
    } 

  

    // Delete a todo by ID 
    @DeleteMapping("/todos/{id}") 
    public ResponseEntity<Todo> deleteTodoById(@PathVariable Long id) { 
        try { 
            // Logging: Recording the entry into the 'deleteTodoById' method 
            logger.info("TodoController: Deleting a todo by ID.");             
            todoService.deleteTodo(id); 
            return new ResponseEntity<>(HttpStatus.OK); 
        } catch (Exception e) { 
            logger.error("Error occurred while deleting todo", e); 
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 

        } 

    } 

} 