package org.kainos.ea.team2.exception;

public class FailedToGetException extends Throwable {

    /**
     * returns error message passed into constructor when exception thrown.
     * @param message
     */
    public FailedToGetException(final String message) {
        super(message);
    }
}
