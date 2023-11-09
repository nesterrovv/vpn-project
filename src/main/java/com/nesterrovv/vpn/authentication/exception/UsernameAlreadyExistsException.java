package com.nesterrovv.vpn.authentication.exception;

public class UsernameAlreadyExistsException extends RuntimeException {

    public UsernameAlreadyExistsException() {
        super("The user with the entered username already exists!");
    }

}
