package com.spring.userpart.service;

import com.spring.userpart.dto.LoginDTO;
import com.spring.userpart.dto.UserDTO;
import com.spring.userpart.model.UserModel;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

public interface IUserService
{
    UserModel register(UserDTO userDTO) throws Exception;

    List<UserModel> getAll();

    Optional<UserModel> getById(long id);

    UserModel getByEmailID(String email);

    UserModel updateUserByEmail(UserDTO userDTO, String email);

    UserModel forgotPassword(UserDTO userDTO, String email);

    String login(LoginDTO loginDTO);

    String deleteById(long id);

    UserModel changePassword(LoginDTO loginDTO, String email);

    UserModel updateById(UserDTO userDTO, long id);
}
