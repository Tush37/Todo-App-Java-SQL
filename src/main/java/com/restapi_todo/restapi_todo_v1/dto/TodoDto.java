package com.restapi_todo.restapi_todo_v1.dto;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class TodoDto {

	 long id;
	 String title;
	 String description;
	 Timestamp dateCreated;
	 boolean completed;
	 Timestamp lastModified;
}


