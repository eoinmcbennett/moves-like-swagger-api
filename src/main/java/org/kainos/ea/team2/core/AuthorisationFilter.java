package org.kainos.ea.team2.core;

import org.kainos.ea.team2.api.AuthenticationService;
import org.kainos.ea.team2.api.IAuthenticationService;
import org.kainos.ea.team2.cli.Authorise;
import org.kainos.ea.team2.cli.UserRole;
import org.kainos.ea.team2.client.AuthenticationException;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Response;
import java.lang.reflect.Method;

/**
 * A filter to allow for checking authorised roles on endpoints.
 */
@Authorise
public class AuthorisationFilter implements ContainerRequestFilter {

    /**
     * The auth service to use for tokens.
     */
    private IAuthenticationService authService;

    /**
     * Creates a new authorisation filter.
     * @param authService the auth service to use for tokens
     */
    public AuthorisationFilter(final IAuthenticationService authService) {
        this.authService = authService;
    }

    /**
     * Used to get the role annotation for the endpoint.
     */
    private @Inject ResourceInfo resourceInfo;

    /**
     * Filters an authorised endpoints access by role.
     * @param containerRequestContext the request context
     */
    @Override
    public void filter(final ContainerRequestContext containerRequestContext) {
        // gets the resource method from the resourceInfo object
        Method method = resourceInfo.getResourceMethod();
        // find out from authorise annotation in
        // controller if this route is for user and or admin
        Authorise annotation = method.getAnnotation(Authorise.class);

        // if authorise annotation null, route is accessible to all
        if (annotation == null) {
            return;
        }

        // required role is min role needed by person logged in to access route
        UserRole requiredRole;

        // if annotation required admin is false, required role is user.
        // Otherwise, required role is admin
        if (!annotation.requireAdmin()) {
            requiredRole = UserRole.User; // user = 1 (enum)
        } else {
            requiredRole = UserRole.Admin; // admin = 2 (enum)
        }

        String authorisationHeader =
                containerRequestContext.getHeaderString("Authorization");
        if (authorisationHeader == null) {
          containerRequestContext.abortWith(
                  Response.status(Response.Status.UNAUTHORIZED)
                  .build());
          return;
        }

        String encodedJWT = authorisationHeader.split(" ")[1];
        if (encodedJWT == null) {
            containerRequestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                    .build());
            return;
        }

       try {
           // check if current jwt is valid
           if (!this.authService.validate(encodedJWT)) {
               containerRequestContext.abortWith(
                        Response.status(Response.Status.UNAUTHORIZED)
                        .build());
               return;
           }

           // get role of user passed into token
           UserRole tokenRole = AuthenticationService.getTokenRole(encodedJWT);
           if (tokenRole == null) {
               containerRequestContext.abortWith(
                   Response.status(Response.Status.UNAUTHORIZED)
                   .build());
               return;
           }

           // if role passed in token is less than required role,
           // route is forbidden
           // i.e. if current user is role user (enum 1)
           // and required role is admin (enum 2)
           if (tokenRole.getUserRole() < requiredRole.getUserRole()) {
               containerRequestContext.abortWith(
                       Response.status(
                       Response.Status.FORBIDDEN)
                       .build());
           }
       } catch (AuthenticationException e) {
           containerRequestContext.abortWith(
                   Response.status(
                   Response.Status.UNAUTHORIZED).build());
       } catch (Exception e) {
           containerRequestContext.abortWith(
               Response.status(
               Response.Status.INTERNAL_SERVER_ERROR).build());
       }
    }
}
