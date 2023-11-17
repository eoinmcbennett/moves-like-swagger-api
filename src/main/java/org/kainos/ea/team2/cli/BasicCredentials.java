package org.kainos.ea.team2.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A username and password credential.
 */
public class BasicCredentials {
    /**
     * The users username.
     */
    private String username;

    /**
     * The users password.
     */
    private String password;

    /**
     * Creates a new basic credential.
     * @param username the username
     * @param password the password
     */
    @JsonCreator
    public BasicCredentials(
            @JsonProperty("username") final String username,
            @JsonProperty("password") final String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the username for this credential.
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username for this credential.
     * @param username the username to set
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Gets the password for this credential.
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for this credential.
     * @param password the password to set
     */
    public void setPassword(final String password) {
        this.password = password;
    }
}
