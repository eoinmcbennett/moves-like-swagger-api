package org.kainos.ea.team2.exception;

/**
 * thrown if an sql error is caught in the dao class.
 */
public class CouldNotGetJobsException extends Throwable {
    @Override
    public String getMessage(){
        return "Could not get jobs";
    }
}
