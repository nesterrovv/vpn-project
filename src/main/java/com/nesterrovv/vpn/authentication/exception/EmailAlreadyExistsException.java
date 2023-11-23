package com.nesterrovv.vpn.authentication.exception;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException() {
        super("The user with the entered email already exists!");
    }

}
