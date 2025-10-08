package com.example.tienda17.exceptions;

public class ServiceExceptions extends RuntimeException{
	public ServiceExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceExceptions(String message) {
        super(message);
    }

}
