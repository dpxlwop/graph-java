package org.school.exceptions;

public class SaveFailException extends RuntimeException {
    public SaveFailException() {
        super("Failed to save file");
    }
}
