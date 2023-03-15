package com.example.selenidetemplate.exception;

public class TestEnvInitFailedException extends Exception {
    public TestEnvInitFailedException() {
        super("Failed to initialize test env");
    }

    public TestEnvInitFailedException(String message) {
        super(message);
    }

    public TestEnvInitFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
