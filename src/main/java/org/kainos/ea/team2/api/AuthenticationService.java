package org.kainos.ea.team2.api;

import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACSigner;
import io.fusionauth.jwt.hmac.HMACVerifier;
import org.kainos.ea.team2.cli.BasicCredentials;
import org.kainos.ea.team2.cli.HashedPassword;
import org.kainos.ea.team2.client.AuthenticationException;
import org.kainos.ea.team2.db.IAuthenticationSource;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;

/**
 * Implements the authentication service defined by the interface
 */
public class AuthenticationService implements IAuthenticationService {
    /**
     * The hashing algorithm to use.
     */
    private static final String PASSWORD_HASHING_ALGORITHM = "PBKDF2WithHmacSHA512";

    /**
     * Implementation of an authentication source.
     */
    private final IAuthenticationSource authSource;

    /**
     * A signer for JWTs
     */
    private final Signer jwtSigner;

    /**
     * A verifier for JWTs
     */
    private final Verifier jwtVerifier;


    /**
     * Creates a new instance of this service.
     * @param authSource The source to pull credentials from.
     * @param jwtSecret The secret used to sign and verify JWTs
     */
    public AuthenticationService(IAuthenticationSource authSource, String jwtSecret) {
        this.authSource = authSource;
        this.jwtSigner = HMACSigner.newSHA256Signer(jwtSecret);
        this.jwtVerifier = HMACVerifier.newVerifier(jwtSecret);
    }

    /**
     * Re-generates a password hash given the passed parameters
     * @param password the password to hash
     * @param salt the salt used in the hash
     * @param iterations the number of iterations used in the hash
     * @return byte[] representing the hash
     * @throws NoSuchAlgorithmException Thrown if hashing algorithm doesn't exist
     * @throws InvalidKeySpecException Thrown if hash generation fails
     */
    private byte[] getPasswordHash(String password, byte[] salt, int iterations) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(password.toCharArray(),salt,iterations,512);
        SecretKeyFactory factory = SecretKeyFactory.getInstance(PASSWORD_HASHING_ALGORITHM);

        return factory.generateSecret(spec).getEncoded();
    }

    /**
     * Attempts to generate a JWT with the credential provided.
     * @param credentials the credentials to authenticate
     * @return JWT if successful, null if failed
     * @throws AuthenticationException Thrown if an error occurred during validation
     */
    @Override
    public JWT authenticate(BasicCredentials credentials) throws AuthenticationException {
        HashedPassword hashedPassword = authSource.getHashedPasswordForUser(credentials.getUsername());

        if (hashedPassword == null) {
            return null;
        }

        //re hash the provided password and add the existing salt
        byte[] credentialHash = null;
        try {
            credentialHash = getPasswordHash(credentials.getPassword(),hashedPassword.getSalt(),hashedPassword.getIterations());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AuthenticationException("An error occurred validating the passed in credentials");
        }

        if (!Arrays.equals(credentialHash, hashedPassword.getHashedPassword())){
            return null;
        }

        return new JWT()
            .setIssuer("org.kainos.ea")
            .setIssuedAt(ZonedDateTime.now(ZoneOffset.UTC))
            .setSubject(credentials.getUsername())
            .setExpiration(ZonedDateTime.now(ZoneOffset.UTC).plusDays(1));
    }

    /**
     * Checks if the encoded JWT passed was issued by this server.
     * @param jwt the JWT to validate
     * @return true if valid, false if not
     */
    @Override
    public boolean validate(String jwt) {
        JWT userJWT = JWT.getDecoder().decode(jwt,jwtVerifier);
        return userJWT != null;
    }

    /**
     * Attempts to sign and encode a JWT.
     * @param jwt the JWT to sign
     * @return the signed, encoded JWT
     */
    @Override
    public String sign(JWT jwt) {
        return JWT.getEncoder().encode(jwt,jwtSigner);
    }
}
