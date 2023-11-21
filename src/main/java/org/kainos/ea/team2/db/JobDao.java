package org.kainos.ea.team2.db;

import org.kainos.ea.team2.cli.Job;
import org.kainos.ea.team2.cli.JobSpecificationResponse;
import org.kainos.ea.team2.exception.FailedToGetException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class JobDao implements IJobDAO {

    /**
     * Calls to the dao to return a list of jobs
     * from the job table in the database.
     * @return list of jobs
     * @throws FailedToGetException if a sql error is thrown.
     */
    @Override
    public List<Job> getJobs() throws FailedToGetException {

        // create list to add jobs returned from db
        List<Job> jobList = new ArrayList<>();

        try {
            // establish connection with database
            Connection c = DatabaseConnector.getConnection();

            // sql string
            String sqlString = "SELECT job_id, job_name, "
                    + "Capabilities.capability_name FROM JobRoles "
                    + "INNER JOIN JobFamilies ON JobRoles.job_family_id="
                    + "JobFamilies.job_family_id INNER JOIN Capabilities "
                    + "ON JobFamilies.capability_id=Capabilities.capability_id;";

            // prepare sql statement
            PreparedStatement preparedStatement =
                    c.prepareStatement(sqlString);

            // execute statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // add each returned row to jobList
            while (resultSet.next()) {
                // create new job
                Job job = new Job(resultSet.getInt("job_id"),
                        resultSet.getString("job_name"),
                        resultSet.getString("capability_name"));
                // add new job to list
                jobList.add(job);
            }

            // return job list
            return jobList;

        } catch (SQLException e) {
            // throw could not get jobs exception if sql exception is caught
            throw new FailedToGetException("Failed to get jobs.");
        }
    }

    /**
     * Calls to the dao to return job spec, sharepoint link and responsibilities
     * from the job and responsibilities table in the database.
     * @param id job id of returned job spec, sharepoint link and responsibility description
     * @return JobSpecificationResponse
     * @throws FailedToGetException if a sql error is thrown.
     */
    public JobSpecificationResponse getJobSpec(final int id)
            throws FailedToGetException {

        try {
            // Establish connection with the database
            Connection c = DatabaseConnector.getConnection();

            // SQL string to fetch job details and responsibilities
            String sqlString = "SELECT JobRoles.job_name, JobRoles.job_specification, JobRoles.sharepoint_link, " +
                    "GROUP_CONCAT(Responsibilities.responsibility_description SEPARATOR ', ') AS responsibilities_list " +
                    "FROM JobRoles " +
                    "LEFT JOIN JobResponsibilities ON JobRoles.job_id = JobResponsibilities.job_id " +
                    "LEFT JOIN Responsibilities ON JobResponsibilities.responsibility_id = Responsibilities.responsibility_id " +
                    "WHERE JobRoles.job_id = ? " +
                    "GROUP BY JobRoles.job_id;";

            // Prepare statement
            PreparedStatement preparedStatement = c.prepareStatement(sqlString);
            preparedStatement.setInt(1, id);

            // Execute query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Create and return job spec response object with rows returned
            while (resultSet.next()) {
                String responsibilitiesString = resultSet.getString("responsibilities_list");
                List<String> responsibilitiesList = Arrays.asList(responsibilitiesString.split(", "));
                return new JobSpecificationResponse(
                        resultSet.getString("job_name"),
                        resultSet.getString("job_specification"),
                        resultSet.getString("sharepoint_link"),
                        responsibilitiesList
                );
            }

            // If no rows returned, return null
            return null;

        } catch (SQLException e) {
            throw new FailedToGetException("Failed to get job.");
        }
    }
}
