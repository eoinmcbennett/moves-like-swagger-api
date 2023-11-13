package org.kainos.ea.team2.client;

/**
 * General exception used for authentication flows.
 */
public class AuthenticationException extends Throwable {
    /**
     * Creates a new auth exception.
     * @param message the message to set
     */
    public AuthenticationException(final String message) {
        super(message);
    }
}
