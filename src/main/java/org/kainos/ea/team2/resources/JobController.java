package org.kainos.ea.team2.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.team2.api.JobService;
import org.kainos.ea.team2.db.JobDao;
import org.kainos.ea.team2.exception.CouldNotGetJobsException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api")
@Api("Moves Like Swagger API")
public class JobController {

    /**
     * create instance of jobs service class.
     */
    private JobService jobService = new JobService(new JobDao());

    /**
     * endpoint to get list of jobs from database.
     * @return Response with appropriate status code and body.
     * Status code 200 if request successful and list non-empty.
     * Status code 500 if internal server error.
     */
    @GET
    @Path("/job-roles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobs() {
        try {
            // call to jobs service to return list of jobs
            return Response.status(Response.Status.OK).
                    entity(jobService.getJobs()).build();
        } catch (CouldNotGetJobsException e) {
            // status code 500 if internal server error
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity(e.getMessage()).build();
        } catch (Exception exception) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity(exception.getMessage()).build();
        }
    }
}
