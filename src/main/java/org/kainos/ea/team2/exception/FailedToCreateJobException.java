package org.kainos.ea.team2.exception;

/**
 * Failed to create job exception thrown when -1 is returned from db
 * when createJob method called.
 */
public class FailedToCreateJobException extends Throwable {

    /**
     * Returns appropriate error message.
     * @return String failed to create job message
     */
    @Override
    public String getMessage() {
        return "Failed to create job";
    }
}
