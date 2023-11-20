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
            PreparedStatement preparedStatement = c.prepareStatement(sqlString);

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
     * Calls to the dao to return job spec and sharepoint link
     * from the job table in the database.
     * @param id job id of returned job spec and sharepoint link
     * @return JobSpecificationResponse
     * @throws FailedToGetException if a sql error is thrown.
     */
    public JobSpecificationResponse getJobSpec(final int id)
            throws FailedToGetException {

        try {
            // establish connection with db
            Connection c = DatabaseConnector.getConnection();

            // sql string
            String sqlString = "SELECT job_name, job_specification, "
                    + "sharepoint_link "
                    + "FROM JobRoles WHERE job_id = ?;";

            // prepare statement
            PreparedStatement preparedStatement = c.prepareStatement(sqlString);

            // set placeholder to be job id passed into method
            preparedStatement.setInt(1, id);

            // execute query
            ResultSet resultSet = preparedStatement.executeQuery();

            // create and return job spec response object with row returned
            while (resultSet.next()) {
                return new JobSpecificationResponse(
                        resultSet.getString("job_name"),
                        resultSet.getString("job_specification"),
                        resultSet.getString("sharepoint_link"));
            }

            // if no rows returned, return null
            return null;

        } catch (SQLException e) {
            throw new FailedToGetException("Failed to get job.");
        }
    }
}
