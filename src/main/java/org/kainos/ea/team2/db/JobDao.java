package org.kainos.ea.team2.db;

import org.kainos.ea.team2.cli.BandLevel;
import org.kainos.ea.team2.cli.Job;
import org.kainos.ea.team2.cli.JobSpecificationResponse;
import org.kainos.ea.team2.cli.CreateJob;
import org.kainos.ea.team2.cli.BandLevel;
import org.kainos.ea.team2.cli.JobFamily;
import org.kainos.ea.team2.exception.FailedToCreateJobException;
import org.kainos.ea.team2.exception.FailedToGetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JobDao implements IJobDAO {

    /**
     * Constant to assign value 1 for use in
     * prepared statement to avoid use of magic numbers.
     */
    public static final int VAL_1 = 1;

    /**
     * Constant to assign value 2 for use in
     * prepared statement to avoid use of magic numbers.
     */
    public static final int VAL_2 = 2;

    /**
     * Constant to assign value 3 for use in
     * prepared statement to avoid use of magic numbers.
     */
    public static final int VAL_3 = 3;

    /**
     * Constant to assign value 4 for use in
     * prepared statement to avoid use of magic numbers.
     */
    public static final int VAL_4 = 4;

    /**
     * Constant to assign value 5 for use in
     * prepared statement to avoid use of magic numbers.
     */
    public static final int VAL_5 = 5;

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
                    + "capability_name, band_name, BandLevel.bandlevel_id "
                    + "FROM JobRoles "
                    + "INNER JOIN JobFamilies ON "
                    + "JobRoles.job_family_id = "
                    + "JobFamilies.job_family_id INNER JOIN Capabilities "
                    + "ON JobFamilies.capability_id = "
                    + "Capabilities.capability_id "
                    + "INNER JOIN BandLevel ON JobRoles.bandlevel_id = "
                    + "BandLevel.bandlevel_id;";

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
                        resultSet.getString("capability_name"),
                        new BandLevel(resultSet.getInt("bandlevel_id"),
                                resultSet.getString("band_name")));
                // add new job to list
                jobList.add(job);
            }

            // return job list
            return jobList;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
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
            String sqlString = "SELECT job_id, job_name, job_specification, "
                    + "sharepoint_link "
                    + "FROM JobRoles WHERE job_id = ?;";

            // prepare statement
            PreparedStatement preparedStatement = c.prepareStatement(sqlString);

            // set placeholder to be job id passed into method
            preparedStatement.setInt(1, id);

            // execute query
            ResultSet resultSet = preparedStatement.executeQuery();

            // create and return job spec response object with row returned
            if (resultSet.next()) {
                return new JobSpecificationResponse(
                        resultSet.getInt("job_id"),
                        resultSet.getString("job_name"),
                        resultSet.getString("job_specification"),
                        resultSet.getString("sharepoint_link"));
            }

            // if no rows returned, return null
            return null;

        } catch (SQLException e) {
            throw new FailedToGetException("Failed to get jobs.");
        }

    }

    /**
     * Calls to the dao to inserts data to the job role table in the
     * db and returns id for new row.
     * @param job (job name, spec, sharepoint, band level ID and job family ID)
     * @return jobId
     * @throws FailedToCreateJobException
     */
    public int createJob(final CreateJob job) throws
            FailedToCreateJobException {

        try {
            // establish connection with db
            Connection c = DatabaseConnector.getConnection();

            // sql string
            String insertStatement = "INSERT INTO JobRoles(job_name, "
                    + "job_specification, sharepoint_link, bandlevel_id, "
                    + "job_family_id) VALUES (?, ?, ?, ?, ?);";

            // prepare statement
            PreparedStatement st = c.prepareStatement(insertStatement,
                    Statement.RETURN_GENERATED_KEYS);


            //assign values to prepared statement
            st.setString(VAL_1, job.getJobName());
            st.setString(VAL_2, job.getJobSpecification());
            st.setString(VAL_3, job.getSharepointLink());
            st.setInt(VAL_4, job.getJobBandLevelId());
            st.setInt(VAL_5, job.getJobFamilyId());

            //execute query
            st.executeUpdate();

            //retrieve result
            ResultSet rs = st.getGeneratedKeys();

            //if row created return the ID
            if (rs.next()) {
                return rs.getInt(1);
            }

            //otherwise return -1
            return -1;
        } catch (SQLException e) {
            throw new FailedToCreateJobException("Failed to get jobs.");
        }
    }

    /**
     * Calls to the dao to return a list of band levels
     * from the band level table in the database.
     * @return list of band levels
     * @throws FailedToGetException if a sql error is thrown.
     */
    public List<BandLevel> getBandLevels() throws FailedToGetException {

        // create list to add jobs returned from db
        List<BandLevel> bandList = new ArrayList<>();

        try {
            // establish connection with database
            Connection c = DatabaseConnector.getConnection();

            // sql string
            String sqlString = "SELECT bandlevel_id, band_name "
                    + "FROM BandLevel;";

            // prepare sql statement
            PreparedStatement preparedStatement =
                    c.prepareStatement(sqlString);

            // execute statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // add each returned row to jobList
            while (resultSet.next()) {
                // create new job
                BandLevel bandLevel =
                        new BandLevel(resultSet.getInt("bandlevel_id"),
                        resultSet.getString("band_name"));
                // add new job to list
                bandList.add(bandLevel);
            }

            // return job list
            return bandList;

        } catch (SQLException e) {
            // throw could not get jobs exception if sql exception is caught
            throw new FailedToGetException("Failed to get band levels.");
        }
    }

    /**
     * Calls to the dao to return a list of job families
     * from the job families table in the database.
     * @return list of job families
     * @throws FailedToGetException if a sql error is thrown.
     */
    public List<JobFamily> getJobFamilies() throws FailedToGetException {

        // create list to add jobs returned from db
        List<JobFamily> jobFamilies = new ArrayList<>();

        try {
            // establish connection with database
            Connection c = DatabaseConnector.getConnection();

            // sql string
            String sqlString = "SELECT job_family_id, job_family_name "
                    + "FROM JobFamilies;";

            // prepare sql statement
            PreparedStatement preparedStatement =
                    c.prepareStatement(sqlString);

            // execute statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // add each returned row to jobList
            while (resultSet.next()) {
                // create new job
                JobFamily jobFamily =
                        new JobFamily(resultSet.getInt("job_family_id"),
                        resultSet.getString("job_family_name"));
                // add new job to list
                jobFamilies.add(jobFamily);
            }

            // return job list
            return jobFamilies;

        } catch (SQLException e) {
            // throw could not get jobs exception if sql exception is caught
            throw new FailedToGetException("Failed to get job families.");
        }
    }

    /**
     * Deletes a job with a specified JobID from the database.
     * @param jobID
     */
    public void deleteJob(final int jobID) throws FailedToGetException {

        try {
            // establish connection with db
            Connection c = DatabaseConnector.getConnection();

            // Create prepared statement for deleting the job
            String sqlString = "DELETE FROM JobRoles WHERE job_id = ?;";
            PreparedStatement preparedStatement = c.prepareStatement(sqlString);
            preparedStatement.setInt(1, jobID);

            // Delete the job
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new FailedToGetException("Failed to get job");
        }

    }
}
