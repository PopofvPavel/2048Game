package com.example.Board;

public class NotEnoughSpaceException extends Exception{
    public static final String ERROR_MESSAGE = "Not enough space in the board";

    NotEnoughSpaceException(String message) {
        super(message);

    }

    public NotEnoughSpaceException() {
        super(ERROR_MESSAGE);
    }
}
