package com.elizabeth.mytodoapp.DTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserDto {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}
