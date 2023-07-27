package com.restapi_todo.restapi_todo_v1.dto;
import lombok.Data;

@Data
public class UpdateTodoDto {

	long id;
	String title;
	String description;
	boolean completed;
}

