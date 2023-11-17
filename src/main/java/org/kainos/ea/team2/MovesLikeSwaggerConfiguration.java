package org.kainos.ea.team2;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import io.swagger.annotations.ApiKeyAuthDefinition;
import io.swagger.annotations.SecurityDefinition;
import io.swagger.annotations.SwaggerDefinition;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@SwaggerDefinition(
  securityDefinition = @SecurityDefinition(
    apiKeyAuthDefinitions = {
      @ApiKeyAuthDefinition(key = "basicAuth", name = "Authorization", in = ApiKeyAuthDefinition.ApiKeyLocation.HEADER)
    }
  )
)



public final class MovesLikeSwaggerConfiguration extends Configuration {
  /**
   * Swagger configuration bundle.
   * Required by Swagger
   */
  @Valid
  @NotNull
  private final SwaggerBundleConfiguration
          swagger = new SwaggerBundleConfiguration();

  /**
   * Gets the current swagger bundle configuration.
   * @return SwaggerBundleConfiguration
   */
  @JsonProperty("swagger")
  public SwaggerBundleConfiguration getSwagger() {
    swagger.setResourcePackage("org.kainos.ea.team2");
    String[] schemes = {"http", "https"};
    swagger.setSchemes(schemes);
    return swagger;
  }
}
