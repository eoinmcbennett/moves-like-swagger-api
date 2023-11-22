package org.kainos.ea.team2.client;

import org.kainos.ea.team2.cli.BasicCredentials;

/**
 * Implements a validator for basic login credentials.
 */
public class BasicCredentialValidator implements IValidator<BasicCredentials> {
    /**
     * Attempts to validate a set of basic credentials.
     * @param credential the credentials to validate
     * @return String describing error. null if ok
     */
    public String validate(final BasicCredentials credential) {
        if (credential == null) {
            return "Credential was null";
        }

        if (credential.getPassword() == null) {
            return "The password is null";
        }

        if (credential.getUsername() == null) {
            return "The username is null";
        }

        if (credential.getUsername().isEmpty()) {
            return "the usernames is an empty string";
        }

        if (credential.getPassword().isEmpty()) {
            return "the password is an empty string";
        }

        return null;
    }
}
