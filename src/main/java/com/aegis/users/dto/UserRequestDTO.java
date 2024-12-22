package com.aegis.users.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UserRequestDTO {
    private String name;
    private String password;
    @Email(message = "email invalid")
    private String email;

}
