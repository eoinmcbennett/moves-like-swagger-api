package org.kainos.ea.team2;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.kainos.ea.team2.resources.JobController;

public class MovesLikeSwaggerApplication extends Application<MovesLikeSwaggerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new MovesLikeSwaggerApplication().run(args);
    }

    @Override
    public String getName() {
        return "true";
    }

    @Override
    public void initialize(final Bootstrap<MovesLikeSwaggerConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final MovesLikeSwaggerConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
        System.out.println();
        environment.jersey().register(new JobController());
    }

}
