package org.kainos.ea.team2.exception;

/**
 * thrown if jobs list returned from database is empty.
 */
public class NoJobsAvailableException extends Throwable {

    @Override
    public String getMessage(){
        return "No job roles available";
    }
}
