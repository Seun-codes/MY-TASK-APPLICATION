package com.elizabeth.mytodoapp.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;

    @OneToMany(mappedBy ="user")
    private Set<TodoItem> todoItem;





}
