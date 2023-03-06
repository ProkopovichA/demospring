package com.prokopovich.demospring.exception;

public class UserAlredyExistException extends Exception{
    public UserAlredyExistException(String message) {
        super(message);
    }
}
