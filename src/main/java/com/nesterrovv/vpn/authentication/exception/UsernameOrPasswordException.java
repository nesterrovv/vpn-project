package com.nesterrovv.vpn.authentication.exception;

public class UsernameOrPasswordException extends RuntimeException {

    public UsernameOrPasswordException() {
        super("Not valid login or password!");
    }

}
