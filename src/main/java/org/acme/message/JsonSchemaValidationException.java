package org.acme.message;

public class JsonSchemaValidationException extends RuntimeException {
    public JsonSchemaValidationException(String message) {
        super(message);
    }

    public JsonSchemaValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}