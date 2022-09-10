package com.elizabeth.mytodoapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotEmpty;

@Data @AllArgsConstructor @NoArgsConstructor
public class UserDto {
    @NotEmpty
    private String name;

    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}
