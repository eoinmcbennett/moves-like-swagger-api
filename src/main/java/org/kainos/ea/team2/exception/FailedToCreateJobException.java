package org.kainos.ea.team2.exception;

/**
 * Failed to create job exception thrown when -1 is returned from db
 * when createJob method called.
 */
public class FailedToCreateJobException extends Throwable {

    /**
     * returns error message passed into constructor when exception thrown.
     * @param message
     */
    public FailedToCreateJobException(final String message) {
        super(message);
    }
}
