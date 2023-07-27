package com.restapi_todo.restapi_todo_v1.model;

import java.sql.Timestamp;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Todo {
    @Id
    @GeneratedValue
    long id;
    @Column(name="title")
    String title;
    
    @Column
    String description;

    @CreationTimestamp
    @Column(updatable = false)
    Timestamp dateCreated;
    
    @Column
    boolean isCompleted;
    
    @UpdateTimestamp
    @Column
    Timestamp lastModified;

}

