package org.kainos.ea.team2.db;

import io.fusionauth.jwt.domain.JWT;
import org.kainos.ea.team2.cli.BasicCredentials;
import org.kainos.ea.team2.client.AuthenticationException;

/**
 * Methods that must be implemented by an authentication source.
 */
public interface IAuthenticationSource {

    /**
     * Gets the stored hash of the users password if one exists.
     * @param username the username to lookup
     * @return byte[] of the users hashed password or null if none exists
     * @throws AuthenticationException thrown for non-user error
     */
    byte[] getHashedPasswordForUser(String username) throws AuthenticationException;

    /**
     * Gets the stored salt for a user if one exists.
     * @param username the username to lookup
     * @return byte[] containing the salt or null if none exists
     * @throws AuthenticationException thrown for non-user error
     */
    byte[] getSaltForUser(String username) throws AuthenticationException;
}
