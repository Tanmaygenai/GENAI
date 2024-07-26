package com.exavalu.agentportal.error;

public class UserException extends RuntimeException {

    public UserException() {
        super();
    }

    public UserException(String userName) {
        super("User Not Found: " + userName);
    }

}

