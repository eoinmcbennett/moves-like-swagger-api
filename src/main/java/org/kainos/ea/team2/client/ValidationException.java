package org.kainos.ea.team2.client;

/**
 * Can be thrown by methods to indicate a validation failure.
 */
public class ValidationException extends Throwable {

    /**
     * Creates a new validation exception.
     * @param message the message to specify
     */
    public ValidationException(final String message) {
        super(message);
    }
}
