package org.kainos.ea.team2.api;

import io.fusionauth.jwt.domain.JWT;
import org.kainos.ea.team2.cli.BasicCredentials;
import org.kainos.ea.team2.client.AuthenticationException;

public interface IAuthenticationService {
    /**
     * Attempts to authenticate a user with the given credentials
     * @param credentials the credentials to authenticate
     * @return JWT if successful, null if credentials are bad
     * @throws AuthenticationException Thrown on non-user error
     */
    JWT authenticate(BasicCredentials credentials) throws AuthenticationException;
}
