package com.example.kregielniaspring.exceptions;

public class LoginInUseException extends Exception {
    public LoginInUseException(String errorMessage) {
        super(errorMessage);
    }
}
