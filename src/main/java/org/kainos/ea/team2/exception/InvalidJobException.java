package org.kainos.ea.team2.exception;

public class InvalidJobException extends Exception {

    /**
     * returns error message passed into constructor when exception thrown.
     * @param error
     */
    public InvalidJobException(final String error) {
        super(error);


    }
}
