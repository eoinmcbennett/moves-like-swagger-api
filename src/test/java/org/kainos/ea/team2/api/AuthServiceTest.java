package org.kainos.ea.team2.api;


import io.fusionauth.jwt.domain.JWT;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.team2.cli.BasicCredentials;
import org.kainos.ea.team2.cli.HashedPassword;
import org.kainos.ea.team2.client.AuthenticationException;
import org.kainos.ea.team2.client.BasicCredentialValidator;
import org.kainos.ea.team2.client.IValidator;
import org.kainos.ea.team2.db.IAuthenticationSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    private static final String JWT_SECRET = "SDFGSRT3fghdfhEHEghEghe";
    private static final String HASH_ALG = "PBKDF2WithHmacSHA512";
    private static final int KEY_LENGTH = 512;

    private static final IValidator<BasicCredentials> validator = new BasicCredentialValidator();

    private static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
    private static HashedPassword generateHashForPassword(String password) {
        try {
            char[] passwordChars = password.toCharArray();
            byte[] salt = generateSalt();
            int iterations = 3000;
            KeySpec spec = new PBEKeySpec(passwordChars, salt, iterations, KEY_LENGTH);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(HASH_ALG);

            byte[] hash = factory.generateSecret(spec).getEncoded();

            return new HashedPassword(hash,salt,iterations);
        } catch(Exception e){
            return null;
        }
    }

    @Test
    void authSource_testLoginSuccessful_whenValidCredentialsGiven() throws AuthenticationException {
        IAuthenticationSource authenticationSource = Mockito.mock(IAuthenticationSource.class);
        IAuthenticationService authService = new AuthenticationService(authenticationSource, JWT_SECRET, validator);

        HashedPassword generatedPassword = generateHashForPassword("testing");
        if(generatedPassword == null){
            fail("Couldn't generate hashed password");
        }

        BasicCredentials credentials = new BasicCredentials("test","testing");

        Mockito.when(authenticationSource.getHashedPasswordForUser("test")).thenReturn(generatedPassword);

        JWT userToken = authService.authenticate(credentials);

        Assertions.assertEquals(userToken.issuer,"org.kainos.ea");
        Assertions.assertNotNull(userToken);
    }

    @Test
    void authSource_testLoginUnsuccessful_whenAuthenticationExceptionThrown() throws AuthenticationException {
        IAuthenticationSource authenticationSource = Mockito.mock(IAuthenticationSource.class);
        IAuthenticationService authService = new AuthenticationService(authenticationSource, JWT_SECRET, validator);

        BasicCredentials credentials = new BasicCredentials("test","testing");

        Mockito.when(authenticationSource.getHashedPasswordForUser("test")).thenThrow(AuthenticationException.class);

        Assertions.assertThrows(AuthenticationException.class,() -> {
            authService.authenticate(credentials);
        });
    }

    @Test
    void authService_testLoginUnsuccessful_whenAttemptToCompareNullPassword() {
        IAuthenticationSource authenticationSource = Mockito.mock(IAuthenticationSource.class);
        IAuthenticationService authService = new AuthenticationService(authenticationSource, JWT_SECRET, validator);

        BasicCredentials credentials = new BasicCredentials("test",null);

        Assertions.assertThrows(AuthenticationException.class,() -> {
            authService.authenticate(credentials);
        });
    }

    @Test
    void authService_testLoginUnsuccessful_whenAttemptToGetPasswordForNullUserName() {
        IAuthenticationSource authenticationSource = Mockito.mock(IAuthenticationSource.class);
        IAuthenticationService authService = new AuthenticationService(authenticationSource, JWT_SECRET, validator);

        BasicCredentials credentials = new BasicCredentials(null,"testing");

        Assertions.assertThrows(AuthenticationException.class,() -> {
            authService.authenticate(credentials);
        });
    }

    @Test
    void authService_testLoginUnsuccessful_whenNullCredentialPassed() {
        IAuthenticationSource authenticationSource = Mockito.mock(IAuthenticationSource.class);
        IAuthenticationService authService = new AuthenticationService(authenticationSource, JWT_SECRET, validator);

        BasicCredentials credentials = null;

        Assertions.assertThrows(AuthenticationException.class,() -> {
            authService.authenticate(credentials);
        });
    }

    @Test
    void authService_testLoginUnsuccessful_whenUserNameNotFound() throws AuthenticationException {
        IAuthenticationSource authenticationSource = Mockito.mock(IAuthenticationSource.class);
        IAuthenticationService authService = new AuthenticationService(authenticationSource, JWT_SECRET, validator);

        BasicCredentials credentials = new BasicCredentials("Test","testing");

        Mockito.lenient().when(authenticationSource.getHashedPasswordForUser("test")).thenReturn(null);

        Assertions.assertNull(authService.authenticate(credentials));
    }

    @Test
    void authService_testLoginUnsuccessful_whenHashedPasswordHasInvalidIterations() throws AuthenticationException {
        IAuthenticationSource authenticationSource = Mockito.mock(IAuthenticationSource.class);
        IAuthenticationService authService = new AuthenticationService(authenticationSource, JWT_SECRET, validator);

        HashedPassword generatedPassword = generateHashForPassword("testing");
        HashedPassword invalidHashedPassword = new HashedPassword(generatedPassword.getHashedPassword(),generatedPassword.getSalt(),-1);

        BasicCredentials credentials = new BasicCredentials("test","testing");

        Mockito.when(authenticationSource.getHashedPasswordForUser("test")).thenReturn(invalidHashedPassword);

        Assertions.assertThrows(AuthenticationException.class,() -> {
            authService.authenticate(credentials);
        });
    }

    @Test
    void authService_testLoginUnsuccessful_whenHashedPasswordHasInvalidSalt() throws AuthenticationException {
        IAuthenticationSource authenticationSource = Mockito.mock(IAuthenticationSource.class);
        IAuthenticationService authService = new AuthenticationService(authenticationSource, JWT_SECRET, validator);

        HashedPassword generatedPassword = generateHashForPassword("testing");
        SecureRandom random = new SecureRandom();
        byte[] invalidSalt = new byte[16];
        random.nextBytes(invalidSalt);

        HashedPassword invalidHashedPassword = new HashedPassword(generatedPassword.getHashedPassword(), invalidSalt, 3000);

        BasicCredentials credentials = new BasicCredentials("test","testing");

        Mockito.when(authenticationSource.getHashedPasswordForUser("test")).thenReturn(invalidHashedPassword);

        Assertions.assertNull(authService.authenticate(credentials));
    }

    @Test
    void authSource_testVerificationSuccessful_whenValidTokenGiven() throws AuthenticationException {
        IAuthenticationSource authenticationSource = Mockito.mock(IAuthenticationSource.class);
        IAuthenticationService authService = new AuthenticationService(authenticationSource, JWT_SECRET, validator);

        HashedPassword generatedPassword = generateHashForPassword("testing");
        if(generatedPassword == null){
            fail("Couldn't generate hashed password");
        }

        BasicCredentials credentials = new BasicCredentials("test","testing");

        Mockito.when(authenticationSource.getHashedPasswordForUser("test")).thenReturn(generatedPassword);

        JWT userToken = authService.authenticate(credentials);

        String signedToken = authService.sign(userToken);

        Assertions.assertTrue(authService.validate(signedToken));
    }

    @Test
    void authSource_testTokenSigned_whenValidTokenPassed() throws AuthenticationException {
        IAuthenticationSource authenticationSource = Mockito.mock(IAuthenticationSource.class);
        IAuthenticationService authService = new AuthenticationService(authenticationSource, JWT_SECRET, validator);

        HashedPassword generatedPassword = generateHashForPassword("testing");
        if(generatedPassword == null){
            fail("Couldn't generate hashed password");
        }

        BasicCredentials credentials = new BasicCredentials("test","testing");

        Mockito.when(authenticationSource.getHashedPasswordForUser("test")).thenReturn(generatedPassword);

        JWT userToken = authService.authenticate(credentials);

        String signedToken = authService.sign(userToken);

        Assertions.assertNotNull(signedToken);
    }
}
