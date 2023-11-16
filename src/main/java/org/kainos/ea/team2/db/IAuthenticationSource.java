package org.kainos.ea.team2.db;

import org.kainos.ea.team2.cli.HashedPassword;
import org.kainos.ea.team2.cli.UserRole;
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
    HashedPassword getHashedPasswordForUser(String username)
            throws AuthenticationException;

    UserRole getRoleForUser(String username)
        throws AuthenticationException;
}
