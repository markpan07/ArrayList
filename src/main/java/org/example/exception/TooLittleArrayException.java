package org.example.exception;

public class TooLittleArrayException extends RuntimeException{
    public TooLittleArrayException(String message){
        super(message);
    }
}
