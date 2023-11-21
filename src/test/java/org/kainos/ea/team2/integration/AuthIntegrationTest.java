package org.kainos.ea.team2.integration;

import org.kainos.ea.team2.MovesLikeSwaggerApplication;
import org.kainos.ea.team2.MovesLikeSwaggerConfiguration;
import org.kainos.ea.team2.cli.BasicCredentials;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;


@ExtendWith(DropwizardExtensionsSupport.class)
public class AuthIntegrationTest {
    static final DropwizardAppExtension<MovesLikeSwaggerConfiguration> APP = new DropwizardAppExtension<>(
            MovesLikeSwaggerApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    private static final String VALID_USER_NAME = System.getenv("TEST_VALID_USERNAME");
    private static final String VALID_USER_PASSWORD = System.getenv("TEST_VALID_PASSWORD");


    /**
     * Verify that logging in valid credentials gives back a valid JWT
     */
    @Test
    void login_shouldReturn200_whenValidCredentialsArePassed() {
        // if environment vars not set, throw illegal arg exception
        if(VALID_USER_NAME == null || VALID_USER_PASSWORD == null){
            System.out.println("env vars not set");
            throw new IllegalArgumentException("Test credential environment variables not set!");
        }
        // create basic credentials object with env vars
        BasicCredentials credentials = new BasicCredentials(VALID_USER_NAME,VALID_USER_PASSWORD);
        // post basic credentials
        Response response = APP.client().target("http://localhost:8080/api/login").request().post(Entity.json(credentials));
        // check that response status is 200
        Assertions.assertEquals(200, response.getStatus());
    }

    /**
     * checks that 401 unauthorized is returned when a user attempts to login with wrong credentials.
     */
    @Test
    void login_shouldReturn401Unauthorized_whenWrongCredentialsArePassed() {
        BasicCredentials credentials = new BasicCredentials("ertetDFGHdfghfdgh","sdfgsdfg");
        Response response = APP.client().target("http://localhost:8080/api/login").request().post(Entity.json(credentials));

        Assertions.assertEquals(401, response.getStatus());
    }

    /**
     * Checks that 400 bad request is returned when a user leaves username and password blank.
     */
    @Test
    void login_shouldReturn400_whenInvalidCredentialsArePassed() {
        BasicCredentials credentials = new BasicCredentials("",null);
        Response response = APP.client().target("http://localhost:8080/api/login").request().post(Entity.json(credentials));

        Assertions.assertEquals(400, response.getStatus());
    }

    /**
     * Checks that 400 bad request is returned when a user enters neither a username nor password.
     */
    @Test
    void login_shouldReturn400_whenNullCredentialsArePassed() {
        Response response = APP.client().target("http://localhost:8080/api/login").request().post(Entity.json(null));
        Assertions.assertEquals(400, response.getStatus());
    }
}
