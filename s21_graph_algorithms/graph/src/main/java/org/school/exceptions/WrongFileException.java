package org.school.exceptions;

public class WrongFileException extends RuntimeException {
    public WrongFileException() {
        super("Failed to load from file");
    }
}
