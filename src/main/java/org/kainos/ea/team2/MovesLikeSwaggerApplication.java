package org.kainos.ea.team2;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.kainos.ea.team2.api.AuthenticationService;
import org.kainos.ea.team2.api.IAuthenticationService;
import org.kainos.ea.team2.client.BasicCredentialValidator;
import org.kainos.ea.team2.core.AuthorisationFilter;
import org.kainos.ea.team2.db.DBAuthenticationSource;
import org.kainos.ea.team2.resources.AuthController;
import org.kainos.ea.team2.resources.JobController;

/**
 * Moves like swagger API.
 */
public final class MovesLikeSwaggerApplication
        extends Application<MovesLikeSwaggerConfiguration> {

  /**
   * JWT Secret.
   */
  private static final String JWT_SECRET = System.getenv("JWT_SECRET");

  /**
   * The auth service to use.
   */
  private final IAuthenticationService authService = new AuthenticationService(
          new DBAuthenticationSource(),
          JWT_SECRET,
          new BasicCredentialValidator());


/**
 * Entrypoint.
 * @param args arguments passed to the application
 * @throws Exception thrown on error within the application
 */
  public static void main(final String[] args) throws Exception {
    new MovesLikeSwaggerApplication().run(args);
  }

  @Override
  public String getName() {
    return "true";
  }

  @Override
  public void initialize(
          final Bootstrap<MovesLikeSwaggerConfiguration> bootstrap) {
    bootstrap.addBundle(
        new SwaggerBundle<MovesLikeSwaggerConfiguration>() {
          @Override
          protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(
              final MovesLikeSwaggerConfiguration configuration) {
            return configuration.getSwagger();
          }
        });
  }

  @Override
  public void run(
      final MovesLikeSwaggerConfiguration configuration,
      final Environment environment) {


    environment.jersey().register(new AuthController(authService));
    environment.jersey().register(new AuthorisationFilter(authService));
    environment.jersey().register(new JobController());
  }
}
