package org.kainos.ea.team2.client;

/**
 * Generic validator interface.
 * @param <T> the model type that the validator works for
 */
public interface IValidator<T> {

    /**
     * Validates the given object.
     * @param object the object to validate
     * @return a string describing an error, if no errors then null
     */
    String validate(T object);
}
