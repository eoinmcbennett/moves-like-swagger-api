package org.kainos.ea.team2.integration;

import org.kainos.ea.team2.MovesLikeSwaggerApplication;
import org.kainos.ea.team2.MovesLikeSwaggerConfiguration;
import org.kainos.ea.team2.cli.BandLevel;
import org.kainos.ea.team2.cli.CreateJob;
import org.kainos.ea.team2.cli.Job;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.team2.cli.JobFamily;
import org.kainos.ea.team2.db.DatabaseConnector;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

        // list of jobs, add each job returned from the db
        List<Job> response = APP.client().target("http://localhost:8080/api/job-roles")
                .request().get(List.class);

        // check that the list of jobs is non-empty
        Assertions.assertTrue(response.size() > 0);

    }

    /**
     * Verify that the getBandLevels method returns a list of band levels from the database.
     */
    @Test
    void getBandLevels_shouldReturnListOfBandLevels() {

        // list of band levels, add each band level returned from the db
        List<BandLevel> response = APP.client().target("http://localhost:8080/api/job-band-levels")
                .request().get(List.class);

        // check that the list of band levels is non-empty
        Assertions.assertTrue(response.size() > 0);

    }

    @Test
    void getJobFamilies_shouldReturnListOfJobFamilies() {

        // list of job families, add each job family returned from the db
        List<JobFamily> response = APP.client().target("http://localhost:8080/api/job-families")
                .request().get(List.class);

        // check that the list of job families is non-empty
        Assertions.assertTrue(response.size() > 0);

    }

    /**
     * Verify that the getJobSpec method returns a JobSpecificationResponse.
     */
    @Test
    void getJobSpec_shouldReturnJobSpec() {

        // call url to get job spec where job id = 1
        Response response = APP.client().target("http://localhost:8080/api/job-specification/2")
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
     * Verify that the createJob method returns the ID of the job created
     * and correct response.
     */
    @Test
    void createJob_shouldReturnIdOfJob_whenJobCreatedSuccessfully() {

        //Dummy job data to test
        CreateJob job = new CreateJob(
                "Driver",
                "Drives bus",
                "www.bus.com",
                1,
                6

        );

        //call url to create job
        Response response = APP.client().target("http://localhost:8080/api/create-job")
                .request()
                .post(Entity.entity(job, MediaType.APPLICATION_JSON_TYPE));

        int id = response.readEntity(Integer.class);
        int status = response.getStatus();

        //check generated ID isn't null
        Assertions.assertNotNull(id);
        //check generated ID is valid
        Assertions.assertNotEquals(-1, id);
        //check correct response
        Assertions.assertEquals(200, status);

    }

    /**
     * Verify that the createJob method returns status code
     * 400 and correct error message in entity
     * when job name is an empty string.
     */
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

    /**
     * Verify that the createJob method returns status code
     * 400 and correct error message in entity
     * when job name is null.
     */
    @Test
    void createJob_shouldReturn400Error_whenJobNameNull() {

        //Dummy job data to test
        CreateJob job = new CreateJob(
                null,
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

    /**
     * Verify that the createJob method returns status code
     * 400 and correct error message in entity
     * when job Specification is an empty string.
     */
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

    /**
     * Verify that the createJob method returns status code
     * 400 and correct error message in entity
     * when job Specification is null.
     */
    @Test
    void createJob_shouldReturn400Error_whenJobSpecificationIsNull() {

        //Dummy job data to test
        CreateJob job = new CreateJob(
                "Driver",
                null,
                "www.bus.com",
                1,
                6

        );

        Response response = APP.client().target("http://localhost:8080/api/create-job")
                .request()
                .post(Entity.entity(job, MediaType.APPLICATION_JSON_TYPE));

        Assertions.assertEquals(400, response.getStatus());
    }

    /**
     * Verify that the createJob method returns status code
     * 400 and correct error message in entity
     * when sharepoint link is an empty string.
     */
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

    /**
     * Verify that the createJob method returns status code
     * 400 and correct error message in entity
     * when sharepoint link is null.
     */
    @Test
    void createJob_shouldReturn400Error_whenSharepointLinkIsNull() {

        //Dummy job data to test
        CreateJob job = new CreateJob(
                "Driver",
                "Drives Bus",
                null,
                1,
                6

        );

        Response response = APP.client().target("http://localhost:8080/api/create-job")
                .request()
                .post(Entity.entity(job, MediaType.APPLICATION_JSON_TYPE));

        Assertions.assertEquals(400, response.getStatus());
    }

    /**
     * Verify that the createJob method returns status code
     * 400 and correct error message in entity
     * when job name is too long.
     */
    @Test
    void createJob_shouldReturn400Error_whenJobNameHasTooManyChars() {

        //Dummy job data to test
        CreateJob job = new CreateJob(
                "abpwtupudmdhxsilqzppyvqdnmnmlkzdclvvoafueluqimgttkjhbqzvntlishfysivlxefzjgbnmoxvlqb",
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

    /**
     * Verify that the createJob method returns status code
     * 400 and correct error message in entity
     * when band level ID is invalid.
     */
    @Test
    void createJob_shouldReturn400Error_whenJobBandLevelIdInvalid() {

        //Dummy job data to test
        CreateJob job = new CreateJob(
                "Driver",
                "Drives Bus",
                "www.bus.com",
                0,
                6

        );

        Response response = APP.client().target("http://localhost:8080/api/create-job")
                .request()
                .post(Entity.entity(job, MediaType.APPLICATION_JSON_TYPE));

        Assertions.assertEquals(400, response.getStatus());
    }

    /**
     * Verify that the createJob method returns status code
     * 400 and correct error message in entity
     * when job family ID is invalid.
     */
    @Test
    void createJob_shouldReturn500Error_whenJobFamilyIdInvalid() {

        //Dummy job data to test
        CreateJob job = new CreateJob(
                "Driver",
                "Drives Bus",
                "www.bus.com",
                1,
                0

        );

        Response response = APP.client().target("http://localhost:8080/api/create-job")
                .request()
                .post(Entity.entity(job, MediaType.APPLICATION_JSON_TYPE));

        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void deleteJob_shouldReturnStatusCode204WhenJobIsSuccessfullyDeleted() throws SQLException {

        // Get DB connection
        Connection c = DatabaseConnector.getConnection();

        // Insert a new job into the DB so we can delete it for this test case
        String sqlString =
                "INSERT INTO JobRoles (job_name, job_specification, sharepoint_link, bandlevel_id, job_family_id)" +
                " VALUES ('test', 'test', 'test', 1, 1)";

        Statement statement = c.createStatement();
        statement.execute(sqlString, Statement.RETURN_GENERATED_KEYS);

        // Get the ID of the newly inserted job
        int returnedID;
        ResultSet result = statement.getGeneratedKeys();

        if (result.next()) {
            returnedID = result.getInt(1);
        } else {
            throw new SQLException("Failed to insert the test job");
        }

        // call API to delete the newly added job
        Response response = APP.client().target("http://localhost:8080/api/job-roles/" + returnedID)
                .request()
                .delete();

        // check status code 204 returned
        Assertions.assertEquals(204,response.getStatus());
    }

    @Test
    void deleteJob_ShouldReturnStatusCode404_whenJobDoesNotExist() {

        int invalidID = -1;

        // Attempt to delete the non-existent job
        Response response = APP.client().target("http://localhost:8080/api/job-roles/" + invalidID)
                .request()
                .delete();

        // check status code 404 returned
        Assertions.assertEquals(404,response.getStatus());
    }
}
