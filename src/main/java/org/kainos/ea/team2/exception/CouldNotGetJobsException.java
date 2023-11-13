package org.kainos.ea.team2.exception;

/**
 * thrown if an sql error is caught in the dao class.
 */
public class CouldNotGetJobsException extends Throwable {

    /**
     * returns an appropriate error message explaining the exception.
     * @return error message.
     */
    @Override
    public String getMessage() {
        return "Could not get jobs";
    }
}
