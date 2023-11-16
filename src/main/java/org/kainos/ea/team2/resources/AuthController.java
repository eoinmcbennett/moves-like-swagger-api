package org.kainos.ea.team2.resources;

import io.fusionauth.jwt.domain.JWT;
import io.swagger.annotations.Api;
import org.kainos.ea.team2.api.IAuthenticationService;
import org.kainos.ea.team2.cli.BasicCredentials;
import org.kainos.ea.team2.client.AuthenticationException;
import org.kainos.ea.team2.client.ValidationException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/api")
@Api("Moves Like Swagger Authentication API")
public class AuthController {
    /**
     * The authentication service implementation to use.
     */
    private final IAuthenticationService authService;

    /**
     * Creates a new auth controller instance.
     * @param authService the auth service to use
     */
    public AuthController(
            final IAuthenticationService authService) {
        this.authService = authService;
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
        } catch (ValidationException e) {
            System.err.println(e);

            return Response.status(Response.Status.BAD_REQUEST)
                .entity(e.getMessage())
                .build();
        }
    }
}
