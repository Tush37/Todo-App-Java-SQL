package com.restapi_todo.restapi_todo_v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapi_todo.restapi_todo_v1.Controller.TodoController;
import com.restapi_todo.restapi_todo_v1.dto.CreateTodoDto;
import com.restapi_todo.restapi_todo_v1.dto.TodoDto;
import com.restapi_todo.restapi_todo_v1.dto.UpdateTodoDto;
import com.restapi_todo.restapi_todo_v1.model.Todo;
import com.restapi_todo.restapi_todo_v1.services.TodoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(TodoController.class)
public class TodoControllerTest {

    @MockBean
    private TodoService todoService;

    @InjectMocks
    private TodoController todoController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // Test for the getAllTodos() method
    @Test
    public void testGetAllTodos() throws Exception {
        // Mocking the todoService.getTodos() method to return a single Todo object
        when(todoService.getTodos()).thenReturn(Collections.singletonList(new Todo()));

        mockMvc.perform(get("/api/todos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0]").exists());
    }


    // Test for the getTodoById() method
    @Test
    public void testGetTodoById() throws Exception {
        TodoDto todoDto = new TodoDto();
        todoDto.setId(1L);
        todoDto.setTitle("Test Todo");
        when(todoService.getTodoById(1L)).thenReturn(todoDto);

        mockMvc.perform(get("/api/todos/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Test Todo"));
    }

    // Test for the saveTodo() method
    @Test
    public void testSaveTodo() throws Exception {
        CreateTodoDto todoDto = new CreateTodoDto();
        todoDto.setTitle("New Todo");
        todoDto.setDescription("Description");
 
        mockMvc.perform(post("/api/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(todoDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("New Todo"));
    } 

    // Test for the updateTodo() method
    @Test
    public void testUpdateTodo() throws Exception {
        UpdateTodoDto todoDto = new UpdateTodoDto();
        todoDto.setId(1L);
        todoDto.setTitle("Updated Todo");
 
        mockMvc.perform(put("/api/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(todoDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Updated Todo"));
    }
 
    // Test for the deleteTodoById() method
    @Test
    public void testDeleteTodoById() throws Exception {
        mockMvc.perform(delete("/api/todos/1"))
                .andExpect(status().isOk());
    }
}