package com.restapi_todo.restapi_todo_v1.dto;
import lombok.Data;

@Data
public class CreateTodoDto {

	String title;
	String description;
}