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
        Method method = resourceInfo.getResourceMethod();
        Authorise annotation = method.getAnnotation(Authorise.class);

        if (annotation == null) {
            return;
        }

        UserRole role = annotation.value();

        String authorisationHeader =
                containerRequestContext.getHeaderString("Authorization");
        if (authorisationHeader == null) {
          containerRequestContext.abortWith(
                  Response.status(Response.Status.BAD_REQUEST)
                  .build());
          return;
        }

        String encodedJWT = authorisationHeader.split(" ")[1];
        if (encodedJWT == null) {
            containerRequestContext.abortWith(
                    Response.status(Response.Status.BAD_REQUEST)
                    .build());
            return;
        }

       try {
           if (!this.authService.validate(encodedJWT)) {
               containerRequestContext.abortWith(
                        Response.status(Response.Status.UNAUTHORIZED)
                        .build());
               return;
           }

           UserRole tokenRole = AuthenticationService.getTokenRole(encodedJWT);
           if (tokenRole == null) {
               containerRequestContext.abortWith(
                   Response.status(Response.Status.UNAUTHORIZED)
                   .build());
               return;
           }

           if (tokenRole.getUserRole() < role.getUserRole()) {
               containerRequestContext.abortWith(
                       Response.status(
                       Response.Status.FORBIDDEN)
                       .build());
           }
       } catch (AuthenticationException e) {
           containerRequestContext.abortWith(
                   Response.status(
                   Response.Status.BAD_REQUEST).build());
       } catch (Exception e) {
           containerRequestContext.abortWith(
               Response.status(
               Response.Status.INTERNAL_SERVER_ERROR).build());
       }
    }
}
