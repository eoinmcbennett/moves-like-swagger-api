package org.kainos.ea.team2.db;

import org.kainos.ea.team2.cli.BandLevel;
import org.kainos.ea.team2.cli.Job;
import org.kainos.ea.team2.exception.CouldNotGetJobsException;

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
     * @throws CouldNotGetJobsException if a sql error is thrown.
     */
    @Override
    public List<Job> getJobs() throws CouldNotGetJobsException {

        // create list to add jobs returned from db
        List<Job> jobList = new ArrayList<>();

        try {
            // establish connection with database
            Connection c = DatabaseConnector.getConnection();

            // sql string
            String sqlString = "SELECT job_id, job_name, bandlevel_id, band_name FROM JobRoles JOIN BandLevel USING(bandlevel_id);";

            // prepare sql statement
            PreparedStatement preparedStatement = c.prepareStatement(sqlString);

            // execute statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // add each returned row to jobList
            while (resultSet.next()) {
                BandLevel level = new BandLevel(
                    resultSet.getInt("bandlevel_id"),
                    resultSet.getString("band_name")
                );

                Job job = new Job(
                    resultSet.getInt("job_id"),
                    resultSet.getString("job_name"),
                    level
                );

                jobList.add(job);
            }

            // return job list
            return jobList;

        } catch (SQLException e) {
            // throw could not get jobs exception if sql exception is caught
            throw new CouldNotGetJobsException();
        }
    }
}
