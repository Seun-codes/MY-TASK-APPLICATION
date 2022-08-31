package com.elizabeth.mytodoapp.model;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
@Data
@Entity
@Table(name = "ToDoList")
public class TodoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String status;
    private Instant createdTime;
    private Instant modifiedTime;

    @ManyToOne(fetch =FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;


}
