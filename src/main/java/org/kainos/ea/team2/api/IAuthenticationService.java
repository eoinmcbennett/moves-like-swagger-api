package org.kainos.ea.team2.api;

import io.fusionauth.jwt.domain.JWT;
import org.kainos.ea.team2.cli.BasicCredentials;
import org.kainos.ea.team2.client.AuthenticationException;

/**
 * Describes the methods needed by an authentication service.
 */
public interface IAuthenticationService {
    /**
     * Attempts to authenticate a user with the given credentials.
     * @param credentials the credentials to authenticate
     * @return JWT if successful, null if credentials are bad
     * @throws AuthenticationException Thrown on non-user error
     */
    JWT authenticate(BasicCredentials credentials)
            throws AuthenticationException;

    /**
     * Attempts to validate a passed in JWT.
     * @param jwt the JWT to validate
     * @return true or false depending if the token was valid
     */
    boolean validate(String jwt);

    /**
     * Attempts to sign a JWT with the secret key.
     * @param jwt the JWT to sign
     * @return String representing the signed JWT
     */
    String sign(JWT jwt);
}
