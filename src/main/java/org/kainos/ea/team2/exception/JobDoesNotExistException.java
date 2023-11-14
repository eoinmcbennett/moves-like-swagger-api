package org.kainos.ea.team2.exception;

/**
 * Job does not exist exception thrown when nothing is returned from db
 * when getJobSpec method called.
 */
public class JobDoesNotExistException extends Throwable {

    /**
     * Returns appropriate error message.
     * @return String Job does not exist message
     */
    @Override
    public String getMessage() {
        return "Job does not exist.";
    }
}
