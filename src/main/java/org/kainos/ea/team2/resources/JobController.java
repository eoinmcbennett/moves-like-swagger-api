package org.kainos.ea.team2.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.team2.cli.Authorise;
import org.kainos.ea.team2.cli.UserRole;
import org.kainos.ea.team2.db.DatabaseConnector;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Path("/api")
@Api("Moves Like Swagger API")
public class JobController {

  /**
   * Provides a list of jobs within the system.
   * @param token the token from the header
   * @return List of jobs in JSON or nothing
   */
  @GET
  @Path("/jobs")
  @Produces(MediaType.APPLICATION_JSON)
  @Authorise(UserRole.Admin)
  public Response getJobs(
          @HeaderParam("Authorization") final String token) {
    try {
      Connection conn = DatabaseConnector.getConnection();
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("SELECT job_name FROM JobRoles");
      List<String> jobs = new ArrayList<>();

      while (rs.next()) {
        jobs.add(rs.getString("job_name"));
      }

      return Response.ok().entity(jobs).build();
    } catch (Exception e) {
      return Response.serverError().entity(e.getMessage()).build();
    }
  }
}
