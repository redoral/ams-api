package com.revature.amsapi.exception;

public class CustomerNotFoundException extends Exception {
    public CustomerNotFoundException(){
        super();
    }

    public CustomerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerNotFoundException(String message) {
        super(message);
    }

    public CustomerNotFoundException(Throwable cause) {
        super(cause);
    }
}
