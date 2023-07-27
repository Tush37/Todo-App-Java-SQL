package com.restapi_todo.restapi_todo_v1;

import com.restapi_todo.restapi_todo_v1.dao.TodoDao;
import com.restapi_todo.restapi_todo_v1.dto.CreateTodoDto;
import com.restapi_todo.restapi_todo_v1.dto.TodoDto;
import com.restapi_todo.restapi_todo_v1.dto.UpdateTodoDto;
import com.restapi_todo.restapi_todo_v1.model.Todo;
import com.restapi_todo.restapi_todo_v1.services.TodoServiceImpl;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Collections;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

 

@SpringBootTest
public class TodoServiceImplTest {

    @Mock
    private TodoDao todoDao;
    
    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private TodoServiceImpl todoService;

    // Test for the getTodos() method
    @Test
    public void testGetTodos() {
        Todo todo = new Todo();
        todo.setId(1L);
        todo.setTitle("Test Todo");

        // Mocking the todoDao.getTodos() method to return a single Todo object
        when(todoDao.getTodos()).thenReturn(Collections.singletonList(todo));

        // Calling the method under test
        assertEquals(1, todoService.getTodos().size());
        assertEquals("Test Todo", todoService.getTodos().get(0).getTitle());
    }

 

    // Test for the insert() method
    @Test
    public void testInsert() {
        CreateTodoDto todoDto = new CreateTodoDto();
        todoDto.setTitle("New Todo");

        // Calling the method under test
        todoService.insert(todoDto);

        // Verify that todoDao.insert() was called once with any Todo object
        verify(todoDao, times(1)).insert(any(Todo.class));
    }

 

    // Test for the updateTodo() method when the todo with the provided ID exists
    @Test
    public void testUpdateTodo() {
        UpdateTodoDto todoDto = new UpdateTodoDto();
        todoDto.setId(1L);
        todoDto.setTitle("Updated Todo");

        // Creating an existing Todo object
        Todo existingTodo = new Todo();
        existingTodo.setId(1L);
        existingTodo.setTitle("Original Todo");

        // Mocking the todoDao.getTodoById() method to return the existingTodo
        when(todoDao.getTodoById(1L)).thenReturn(Optional.of(existingTodo));

        // Calling the method under test
        todoService.updateTodo(todoDto);

        // Verify that todoDao.updateTodo() was called once with any Todo object
        verify(todoDao, times(1)).updateTodo(any(Todo.class));
    }

 

    // Test for the updateTodo() method when the todo with the provided ID does not exist
    @Test
    public void testUpdateTodoNotFound() {
        UpdateTodoDto todoDto = new UpdateTodoDto();
        todoDto.setId(1L);
        todoDto.setTitle("Updated Todo");

        // Mocking the todoDao.getTodoById() method to return an empty Optional
        when(todoDao.getTodoById(1L)).thenReturn(Optional.empty());

        // Calling the method under test should throw EntityNotFoundException
        assertThrows(EntityNotFoundException.class, () -> todoService.updateTodo(todoDto));
    }

 

    // Test for the deleteTodo() method when the todo with the provided ID exists
    @Test
    public void testDeleteTodo() {
        // Creating an existing Todo object
        Todo existingTodo = new Todo();
        existingTodo.setId(1L);
        existingTodo.setTitle("Test Todo");

        // Mocking the todoDao.getTodoById() method to return the existingTodo
        when(todoDao.getTodoById(1L)).thenReturn(Optional.of(existingTodo));
        
        // Calling the method under test
        todoService.deleteTodo(1L);

        // Verify that todoDao.deleteTodo() was called once with the provided ID (1L)
        verify(todoDao, times(1)).deleteTodo(1L);
    }

 

    // Test for the deleteTodo() method when the todo with the provided ID does not exist
    @Test
    public void testDeleteTodoNotFound() {
        // Mocking the todoDao.getTodoById() method to return an empty Optional
        when(todoDao.getTodoById(1L)).thenReturn(Optional.empty());

        // Calling the method under test should throw EntityNotFoundException
        assertThrows(EntityNotFoundException.class, () -> todoService.deleteTodo(1L));
    }

 

    // Test for the getTodoById() method when the todo with the provided ID exists
    @Test
    public void testGetTodoById() {
        // Creating an existing Todo object
        Todo existingTodo = new Todo();
        existingTodo.setId(1L);
        existingTodo.setTitle("Test Todo");

        // Mocking the todoDao.getTodoById() method to return the existingTodo
        when(todoDao.getTodoById(1L)).thenReturn(Optional.of(existingTodo));

        // Calling the method under test
        TodoDto todoDto = todoService.getTodoById(1L);

        // Verify that the returned TodoDto object is not null and has the expected title
        assertNotNull(todoDto);
        assertEquals("Test Todo", todoDto.getTitle());
    }

 

    // Test for the getTodoById() method when the todo with the provided ID does not exist
    @Test
    public void testGetTodoByIdNotFound() {
        // Mocking the todoDao.getTodoById() method to return an empty Optional
        when(todoDao.getTodoById(1L)).thenReturn(Optional.empty());

        // Calling the method under test should throw EntityNotFoundException
        assertThrows(EntityNotFoundException.class, () -> todoService.getTodoById(1L));
    }
}