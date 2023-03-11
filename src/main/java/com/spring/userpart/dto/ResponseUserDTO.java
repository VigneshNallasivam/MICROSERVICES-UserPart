package com.spring.userpart.dto;

import com.spring.userpart.model.UserModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
public class ResponseUserDTO
{
    private String message;
    private Object object;
    public ResponseUserDTO(String message,Object object)
    {
        this.message=message;
        this.object=object;
    }
}