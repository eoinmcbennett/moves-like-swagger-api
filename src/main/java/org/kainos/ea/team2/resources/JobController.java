package org.kainos.ea.team2.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.Authorization;
import org.kainos.ea.team2.cli.Authorise;
import org.kainos.ea.team2.cli.UserRole;
import org.kainos.ea.team2.api.JobService;
import org.kainos.ea.team2.db.JobDao;
import org.kainos.ea.team2.exception.FailedToGetException;
import org.kainos.ea.team2.exception.JobDoesNotExistException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/api")
@Api(
    value = "Moves Like Swagger API",
    authorizations = {@Authorization(value = "basicAuth")}
)
public class JobController {
    /**
     * Job service instance for the controller to use.
     */
    private final JobService jobService = new JobService(new JobDao());


    /**
     * endpoint to get list of jobs from database.
     * @return Response with appropriate status code and body.
     * Status code 200 if request successful and list non-empty.
     * Status code 500 if internal server error.
     */
    @GET
    @Path("/job-roles")
    @Produces(MediaType.APPLICATION_JSON)
    @Authorise // user and admin route
    public Response getJobs() {
        
        try {
            // call to jobs service to return list of jobs
            return Response.status(Response.Status.OK).
                    entity(jobService.getJobs()).build();
        } catch (FailedToGetException e) {
            // status code 500 if internal server error
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity(e.getMessage()).build();
        }
    }

    /**
     * endpoint to get list of jobs from database.
     * @param id job id of job with returned spec and sharepoint link
     * @return Response with appropriate status code and body.
     * Status code 200 if request successful and list non-empty.
     * Status code 500 if internal server error.
     * Status code 404 if job with given id doesn't exist.
     */
    @GET
    @Path("/job-specification/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Authorise(requireAdmin = true) // admin only route
    public Response getJobSpec(
            @PathParam("id") final int id) {
        try {
            // call to jobs service to return job spec response object
            return Response.status(Response.Status.OK).
                    entity(jobService.getJobSpec(id)).build();
        } catch (FailedToGetException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity(e.getMessage()).build();
        } catch (JobDoesNotExistException e) {
            return Response.status(Response.Status.NOT_FOUND).
                    entity(e.getMessage()).build();
        }
    }
}
