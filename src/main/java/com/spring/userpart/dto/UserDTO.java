package com.spring.userpart.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UserDTO
{
    private long userId;
    @Pattern(regexp = "^[A-Z]{1}[a-z]{2,}$",message = "First name should start with capital letter")
    private String firstName;
    @Pattern(regexp = "^[A-Z]{1}[a-z]{2,}$",message = "Last name should start with capital letter")
    private String lastName;
    private String email;
    private String address;
    private LocalDate dob;
    private String password;
}