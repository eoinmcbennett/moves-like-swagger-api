package org.kainos.ea.team2.integration;

import org.kainos.ea.team2.MovesLikeSwaggerApplication;
import org.kainos.ea.team2.MovesLikeSwaggerConfiguration;
import org.kainos.ea.team2.cli.BasicCredentials;
import org.kainos.ea.team2.cli.Job;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.team2.db.DatabaseConnector;

import javax.ws.rs.client.Entity;
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
     * valid name of user to log in to system
     */
    private static final String VALID_USER_NAME = System.getenv("TEST_VALID_USERNAME");

    /**
     * valid password of user to log in to system
     */
    private static final String VALID_USER_PASSWORD = System.getenv("TEST_VALID_PASSWORD");

    /**
     * valid name of admin
     */
    private static final String VALID_ADMIN_NAME = System.getenv("VALID_ADMIN_NAME");

    /**
     * valid password of admin
     */
    private static final String VALID_ADMIN_PASSWORD = System.getenv("VALID_ADMIN_PASSWORD");

    /**
     * method to create a jwt for a user to access routes.
     * @return
     */
    private String getUserJWT() {
        if(VALID_USER_NAME == null || VALID_USER_PASSWORD == null){
            throw new IllegalArgumentException("user environment variables not set!");
        }
        BasicCredentials credentials = new BasicCredentials(VALID_USER_NAME,VALID_USER_PASSWORD);
        Response response = APP.client().target("http://localhost:8080/api/login").request().post(Entity.json(credentials));

        return response.readEntity(String.class);
    }

    /**
     * method to create a jwt for admin to access routes
     * @return
     */
    private String getAdminJWT() {
        if(VALID_ADMIN_NAME == null || VALID_ADMIN_PASSWORD == null){
            throw new IllegalArgumentException("admin environment variables not set!");
        }
        BasicCredentials credentials = new BasicCredentials(VALID_ADMIN_NAME,VALID_ADMIN_PASSWORD);
        Response response = APP.client().target("http://localhost:8080/api/login").request().post(Entity.json(credentials));

        return response.readEntity(String.class);
    }


    /**
     * Verify that the getJobs method returns a list of jobs from the database when logged in as a user.
     */
    @Test
    void getJobs_shouldReturnListOfJobsForUser() {
        String jwt = getUserJWT();

        // list of employees, add each employee returned from the db
        List<Job> response = APP.client().target("http://localhost:8080/api/job-roles")
                .request().header("Authorization", "Bearer " + jwt).get(List.class);

        // check that the list of jobs is non-empty
        Assertions.assertTrue(response.size() > 0);
     }

    /**
     * Verify that the getJobs method returns a list of jobs from the database when logged in as an admin..
     */
    @Test
    void getJobs_shouldReturnListOfJobsForAdmin() {
        String jwt = getAdminJWT();

        // list of employees, add each employee returned from the db
        List<Job> response = APP.client().target("http://localhost:8080/api/job-roles")
                .request().header("Authorization", "Bearer " + jwt).get(List.class);

        // check that the list of jobs is non-empty
        Assertions.assertTrue(response.size() > 0);
    }

    /**
     * Checks that status 400 bad request returned when user visits url when not logged in.
     */
    @Test
     void getJobs_shouldReturn400BadRequestWhenNotLoggedIn(){
         // call url to get jobs, no passed in jwt
         Response response = APP.client().target("http://localhost:8080/api/job-roles")
                 .request()
                 .get();

         // check status code 400 bad request returned
         Assertions.assertEquals(400,response.getStatus());
     }

    /**
     * Verify that the getJobSpec method returns a JobSpecificationResponse for a user.
     */
    @Test
    void getJobSpec_shouldReturnJobSpecForUser() {

        // call url to get job spec where job id = 1
        Response response = APP.client().target("http://localhost:8080/api/job-specification/2")
                .request()
                .header("Authorization", "Bearer " + getUserJWT())
                .get();

        // check status code 200 returned
        Assertions.assertEquals(200,response.getStatus());

        // check response entity is not null
        Assertions.assertNotNull(response.getEntity());

    }

    /**
     * Verify that the getJobSpec method returns a JobSpecificationResponse for an admin.
     */
    @Test
    void getJobSpec_shouldReturnJobSpecForAdmin() {

        // call url to get job spec where job id = 1
        Response response = APP.client().target("http://localhost:8080/api/job-specification/2")
                .request()
                .header("Authorization", "Bearer " + getAdminJWT())
                .get();

        // check status code 200 returned
        Assertions.assertEquals(200,response.getStatus());

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
                .header("Authorization", "Bearer " + getUserJWT())
                .get();

        // check status code 404 returned
        Assertions.assertEquals(404,response.getStatus());

        // check response entity is not null
        Assertions.assertEquals("Job does not exist.", response.readEntity(String.class));

    }

    /**
     * Checks that status 400 bad request returned when user visits url when not logged in.
     */
    @Test
    void getJobSpec_shouldReturn400BadRequestWhenNotLoggedIn(){
        // call url to get jobs, no passed in jwt
        Response response = APP.client().target("http://localhost:8080/api/job-specification/1")
                .request()
                .get();

        // check status code 400 bad request returned
        Assertions.assertEquals(400,response.getStatus());
    }

    /**
     * Checks that status 204 deleted returned when job deleted
     */
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
                .request().header("Authorization", "Bearer " + getAdminJWT())
                .delete();

        // check status code 204 returned
        Assertions.assertEquals(204,response.getStatus());
    }

    @Test
    void deleteJob_ShouldReturnStatusCode404_whenJobDoesNotExist() {

        int invalidID = -1;

        // Attempt to delete the non-existent job
        Response response = APP.client().target("http://localhost:8080/api/job-roles/" + invalidID)
                .request().header("Authorization", "Bearer " + getAdminJWT())
                .delete();

        // check status code 404 returned
        Assertions.assertEquals(404,response.getStatus());
    }

    /**
     * Checks that status 400 bad request returned when user visits url when not logged in.
     */
    @Test
    void deleteJob_shouldReturn400BadRequestWhenNotLoggedIn(){
        // call url to get jobs, no passed in jwt
        Response response = APP.client().target("http://localhost:8080/api/job-roles/99")
                .request()
                .delete();

        // check status code 400 bad request returned
        Assertions.assertEquals(400,response.getStatus());
    }

    /**
     * Checks that status 403 forbidden returned when user who is not admin attempts to delete.
     */
    @Test
    void deleteJob_shouldReturn403ForbiddenWhenLoggedInIsNotAdmin(){
        // call url to get jobs, no passed in jwt
        Response response = APP.client().target("http://localhost:8080/api/job-roles/99")
                .request().header("Authorization", "Bearer " + getUserJWT())
                .delete();

        // check status code 400 bad request returned
        Assertions.assertEquals(403,response.getStatus());
    }
}
