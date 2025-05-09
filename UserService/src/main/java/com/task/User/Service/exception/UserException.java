package com.task.User.Service.exception;

public class UserException extends RuntimeException{
    String message;
    public UserException(String message) {
        this.message = message;
    }
}
