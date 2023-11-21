package org.kainos.ea.team2.integration;

import org.kainos.ea.team2.MovesLikeSwaggerApplication;
import org.kainos.ea.team2.MovesLikeSwaggerConfiguration;
import org.kainos.ea.team2.cli.CreateJob;
import org.kainos.ea.team2.cli.Job;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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

    /**
     * Verify that the getJobSpec method returns a JobSpecificationResponse.
     */
    @Test
    void getJobSpec_shouldReturnJobSpec() {

        // call url to get job spec where job id = 1
        Response response = APP.client().target("http://localhost:8080/api/job-specification/1")
                .request()
                .get();

        // check status code 200 returned
        Assertions.assertEquals(200, response.getStatus());

        // check response entity is not null
        Assertions.assertNotNull(response.getEntity());

    }

    /**
     * Verify that the getJobSpec method returns status code
     * 404 not found and correct error message in entity
     * when dao returns null.
     */
    @Test
    void getJobSpec_shouldReturnStatusCode404WhenDaoReturnsNull() {

        // call url to get job spec with job id that doesn't exist
        Response response = APP.client().target("http://localhost:8080/api/job-specification/-1")
                .request()
                .get();

        // check status code 404 returned
        Assertions.assertEquals(404, response.getStatus());

        // check response entity is not null
        Assertions.assertEquals("Job does not exist.", response.readEntity(String.class));

    }

    /**
     * Verify that the createJob method returns the ID of the job created.
     */
    @Test
    void createJob_shouldReturnIdOfJob() {

        //Dummy job data to test
        CreateJob job = new CreateJob(
                "Driver",
                "Drives bus",
                "www.bus.com",
                1,
                6

        );

        int id = APP.client().target("http://localhost:8080/api/create-job")
                .request()
                .post(Entity.entity(job, MediaType.APPLICATION_JSON_TYPE))
                .readEntity(Integer.class);

        Assertions.assertNotNull(id);
        Assertions.assertNotEquals(-1, id);

        Response response = APP.client().target("http://localhost:8080/api/create-job")
                .request()
                .post(Entity.entity(job, MediaType.APPLICATION_JSON_TYPE));

        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void createJob_shouldReturn400Error_whenJobNameIsAnEmptyString() {

        //Dummy job data to test
        CreateJob job = new CreateJob(
                "",
                "Drives bus",
                "www.bus.com",
                1,
                6

        );

        Response response = APP.client().target("http://localhost:8080/api/create-job")
                .request()
                .post(Entity.entity(job, MediaType.APPLICATION_JSON_TYPE));

        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void createJob_shouldReturn400Error_whenJobSpecificationIsAnEmptyString() {

        //Dummy job data to test
        CreateJob job = new CreateJob(
                "Driver",
                "",
                "www.bus.com",
                1,
                6

        );

        Response response = APP.client().target("http://localhost:8080/api/create-job")
                .request()
                .post(Entity.entity(job, MediaType.APPLICATION_JSON_TYPE));

        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void createJob_shouldReturn400Error_whenSharepointLinkIsAnEmptyString() {

        //Dummy job data to test
        CreateJob job = new CreateJob(
                "Driver",
                "Drives Bus",
                "",
                1,
                6

        );

        Response response = APP.client().target("http://localhost:8080/api/create-job")
                .request()
                .post(Entity.entity(job, MediaType.APPLICATION_JSON_TYPE));

        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void createJob_shouldReturn400Error_whenJobNameHasTooManyChars() {

        //Dummy job data to test
        CreateJob job = new CreateJob(
                "abpwtupudmdhxsilqzppyvqdnmnmlkzdclvvoafueluqimgttkjhbqzvntlishfysivlxefzjgbnmoxvlq",
                "Drives Bus",
                "www.bus.com",
                1,
                6

        );

        Response response = APP.client().target("http://localhost:8080/api/create-job")
                .request()
                .post(Entity.entity(job, MediaType.APPLICATION_JSON_TYPE));

        Assertions.assertEquals(400, response.getStatus());
    }

}
