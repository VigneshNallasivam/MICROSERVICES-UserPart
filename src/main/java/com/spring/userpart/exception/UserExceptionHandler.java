package com.spring.userpart.exception;

import com.spring.userpart.dto.ResponseUserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class UserExceptionHandler
{
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseUserDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception)
    {
        List<ObjectError> errorList=exception.getBindingResult().getAllErrors();
        List<String> errMsg=errorList.stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.toList());
        ResponseUserDTO responseUserDTO =new ResponseUserDTO("Exception Occured", errMsg.toString());
        return new ResponseEntity<>(responseUserDTO, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ResponseUserDTO> handleException(UserException exception)
    {
        ResponseUserDTO responseUserDTO =new ResponseUserDTO("Exception Occured",exception.getMessage());
        return new ResponseEntity<>(responseUserDTO,HttpStatus.BAD_GATEWAY);
    }
}
