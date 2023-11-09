package org.kainos.ea.team2.db;

import org.kainos.ea.team2.cli.Job;
import org.kainos.ea.team2.exception.CouldNotGetJobsException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobDao implements IJobDAO {
    @Override
    public List<Job> getJobs() throws CouldNotGetJobsException {

        // create list to add jobs returned from db
        List<Job> jobList = new ArrayList<>();

        try {
            Connection c = DatabaseConnector.getConnection();
            // sql string
            String sqlString = "SELECT job_id, job_name FROM JobRoles;";

            // prepare sql statement
            PreparedStatement preparedStatement = c.prepareStatement(sqlString);

            // execute statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // add each returned row to jobList
            while(resultSet.next()){
                // create new job
                Job job = new Job(resultSet.getInt("job_id"), resultSet.getString("job_name"));
                // add new job to list
                jobList.add(job);
            }

            // return job list
            return jobList;

        } catch (SQLException e) {
            throw new CouldNotGetJobsException();
        }
    }
}
