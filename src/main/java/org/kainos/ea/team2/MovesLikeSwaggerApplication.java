package org.kainos.ea.team2;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.kainos.ea.team2.resources.JobController;

public final class MovesLikeSwaggerApplication extends Application<MovesLikeSwaggerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new MovesLikeSwaggerApplication().run(args);
    }

    @Override
    public String getName() {
        return "true";
    }

    @Override
    public void initialize(final Bootstrap<MovesLikeSwaggerConfiguration> bootstrap) {

    }

    @Override
    public void run(final MovesLikeSwaggerConfiguration configuration,
                    final Environment environment) {
        environment.jersey().register(new JobController());
    }

}
