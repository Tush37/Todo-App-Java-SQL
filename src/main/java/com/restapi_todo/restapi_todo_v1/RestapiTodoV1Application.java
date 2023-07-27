package com.restapi_todo.restapi_todo_v1;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class RestapiTodoV1Application {

	public static void main(String[] args) {
		SpringApplication.run(RestapiTodoV1Application.class, args);
	}
	
	@Configuration
	public class ModelMapperConfig {
	  @Bean
	  public ModelMapper modelMapper() {
	    return new ModelMapper();
	  }
	}
	
}
