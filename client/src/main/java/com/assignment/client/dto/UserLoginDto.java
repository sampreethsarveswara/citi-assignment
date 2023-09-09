package com.assignment.client.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserLoginDto {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
