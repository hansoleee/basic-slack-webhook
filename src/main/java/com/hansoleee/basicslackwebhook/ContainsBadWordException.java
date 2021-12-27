package com.hansoleee.basicslackwebhook;

public class ContainsBadWordException extends RuntimeException {

    public ContainsBadWordException(String message) {
        super(message);
    }
}
