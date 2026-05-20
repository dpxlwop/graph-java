package org.school.exceptions;

public class WrongInputData extends RuntimeException {
    public WrongInputData(String message) {
        super(message);
    }
}
