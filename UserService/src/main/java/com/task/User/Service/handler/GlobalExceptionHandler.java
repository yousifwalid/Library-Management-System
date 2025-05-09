package com.task.User.Service.handler;


import com.task.User.Service.CustomError.ErrorMessage;
import com.task.User.Service.exception.UserAlreadyExistsException;
import com.task.User.Service.exception.UserNotFoundException;
import com.task.User.Service.model.entity.User;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@Builder
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleBookNotFound(UserNotFoundException ex){
        ErrorMessage error = new ErrorMessage();
        error.setStatusCode(HttpStatus.NOT_FOUND.value());
        error.setMessage("Book with this id is not exist");
        error.setTimestamp(new Date());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> handleBookAlreadyExists(UserAlreadyExistsException ex){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatusCode(HttpStatus.FOUND.value());
        errorMessage.setMessage("Book is already existing");
        errorMessage.setTimestamp(new Date());
        return new ResponseEntity<>(errorMessage,HttpStatus.FOUND);
    }
}

