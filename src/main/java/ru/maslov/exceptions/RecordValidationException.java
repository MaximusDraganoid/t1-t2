package ru.maslov.exceptions;

public class RecordValidationException extends RuntimeException{
    public RecordValidationException(String message) {
        super(message);
    }

    public RecordValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
