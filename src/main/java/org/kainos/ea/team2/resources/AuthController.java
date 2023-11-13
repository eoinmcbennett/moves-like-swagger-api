package org.kainos.ea.team2.resources;

import io.fusionauth.jwt.domain.JWT;
import io.swagger.annotations.Api;
import org.kainos.ea.team2.api.AuthenticationService;
import org.kainos.ea.team2.api.IAuthenticationService;
import org.kainos.ea.team2.cli.BasicCredentials;
import org.kainos.ea.team2.client.AuthenticationException;
import org.kainos.ea.team2.client.BasicCredentialValidator;
import org.kainos.ea.team2.client.IValidator;
import org.kainos.ea.team2.db.DBAuthenticationSource;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/api")
@Api("Moves Like Swagger Authentication API")
public class AuthController {
    /**
     * The JWT secret environment variable.
     */
    private static final String JWT_SECRET = System.getenv("JWT_SECRET");

    /**
     * Validator for basic credentials.
     */
    private final IValidator<BasicCredentials> basicCredentialValidator;

    /**
     * The authentication service implementation to use.
     */
    private final IAuthenticationService authService;

    /**
     * Creates a new auth controller instance.
     */
    public AuthController() {
        this.basicCredentialValidator = new BasicCredentialValidator();
        this.authService = new AuthenticationService(
                new DBAuthenticationSource(),
                JWT_SECRET,
                this.basicCredentialValidator
        );
    }

    /**
     * Attempts to get a JWT for the user.
     * @param credential The credential to check
     * @return 200 + JWT / 401 / 500
     */
    @POST
    @Path("/login")
    public Response login(final BasicCredentials credential) {
        try {
            JWT userJwt = authService.authenticate(credential);

            if (userJwt == null) {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }

            String signedToken = authService.sign(userJwt);

            return Response.ok().entity(signedToken).build();
        } catch (AuthenticationException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }
}
