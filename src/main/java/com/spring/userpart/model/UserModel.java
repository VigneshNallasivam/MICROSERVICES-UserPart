package com.spring.userpart.model;

import com.spring.userpart.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@Table(name="user")
@Data
@NoArgsConstructor
public class UserModel
{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private LocalDate dob;
    private String password;
    public UserModel(UserDTO userDTO)
    {
        this.userId=userDTO.getUserId();
        this.firstName=userDTO.getFirstName();
        this.lastName=userDTO.getLastName();
        this.email=userDTO.getEmail();
        this.address=userDTO.getAddress();
        this.dob=userDTO.getDob();
        this.password=userDTO.getPassword();
    }
}