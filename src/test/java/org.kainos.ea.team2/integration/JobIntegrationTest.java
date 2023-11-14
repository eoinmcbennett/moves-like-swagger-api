package org.kainos.ea.team2.integration;

import org.kainos.ea.team2.MovesLikeSwaggerApplication;
import org.kainos.ea.team2.MovesLikeSwaggerConfiguration;
import org.kainos.ea.team2.cli.Job;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;


/**
 * integration testing, verifies that the job controller, job service and job dao communicate properly.
 */
@ExtendWith(DropwizardExtensionsSupport.class)
public class JobIntegrationTest {

    static final DropwizardAppExtension<MovesLikeSwaggerConfiguration> APP = new DropwizardAppExtension<>(
            MovesLikeSwaggerApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );


    /**
     * Verify that the getJobs method returns a list of jobs from the database.
     */
    @Test
    void getJobs_shouldReturnListOfJobs() {

        // list of employees, add each employee returned from the db
        List<Job> response = APP.client().target("http://localhost:8080/api/job-roles")
                .request().get(List.class);

        // check that the list of jobs is non-empty
        Assertions.assertTrue(response.size() > 0);

     }
    }
