package org.kainos.ea.team2.api;

import io.fusionauth.jwt.domain.JWT;
import org.kainos.ea.team2.cli.BasicCredentials;
import org.kainos.ea.team2.client.AuthenticationException;
import org.kainos.ea.team2.db.IAuthenticationSource;

public class AuthenticationService implements IAuthenticationService {
    private final IAuthenticationSource authSource;

    public AuthenticationService(IAuthenticationSource authSource) {
        this.authSource = authSource;
    }

    @Override
    public JWT authenticate(BasicCredentials credentials) throws AuthenticationException {
        //Grab any existing data about the user
        String username = credentials.getUsername();
        byte[] storeHash = authSource.getHashedPasswordForUser(username);
        byte[] storeSalt = authSource.getSaltForUser(username);

        if (storeSalt == null || storeHash == null) {
            return null;
        }

        //re hash the provided password and add the existing salt

        //compare the hashes

        //create a JWT and return it if the comparison was successful

        //return null if the comparison failed
    }
}
