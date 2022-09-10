package com.elizabeth.mytodoapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoDto {
    private String title;
    private String description;
    private String status;
    private int user_id;

}