package com.assignment.client.dto;


import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRegisterDto {
    @Email(message = "Invalid email")
    private String email;
    @NotBlank(message = "Invalid Name: Empty name")
    @NotNull(message = "Invalid Name: Name is NULL")
    @Size(min = 3, max = 30, message = "Invalid Name: Must be of 3 - 30 characters")
    private String firstName;
    @NotBlank(message = "Invalid Name: Empty name")
    @NotNull(message = "Invalid Name: Name is NULL")
    @Size(min = 3, max = 30, message = "Invalid Name: Must be of 3 - 30 characters")
    private String lastName;
    @NotNull
    private String password;
    @NotNull(message = "Invalid Phone number: Number is NULL")
    private Long phoneNumber;
}
