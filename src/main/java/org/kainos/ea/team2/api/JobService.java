package org.kainos.ea.team2.api;

import org.kainos.ea.team2.cli.CreateJob;
import org.kainos.ea.team2.cli.Job;
import org.kainos.ea.team2.cli.JobSpecificationResponse;
import org.kainos.ea.team2.client.CreateJobValidator;
import org.kainos.ea.team2.db.IJobDAO;
import org.kainos.ea.team2.exception.FailedToCreateJobException;
import org.kainos.ea.team2.exception.FailedToGetException;
import org.kainos.ea.team2.exception.InvalidJobException;
import org.kainos.ea.team2.exception.JobDoesNotExistException;

import java.sql.SQLException;
import java.util.List;

public class JobService {

    /**
     * create instance of job dao interface.
     */
    private IJobDAO jobDao;

    /**
     * create instance of job service class using job dao interface.
     * @param jobDao
     */
    public JobService(final IJobDAO jobDao) {
        this.jobDao = jobDao;
    }

    public CreateJobValidator jobValidator= new CreateJobValidator();

    /**
     * calls to dao to return a list of jobs from database.
     * @return List<Job>
     * @throws FailedToGetException if sql error thrown in dao class
     */
    public List<Job> getJobs() throws FailedToGetException {

        // call to job dao
        return jobDao.getJobs();
    }

    /**
     * calls to job dao to get job spec summary and
     * sharepoint link for job with given id from db.
     * @param id of job role
     * @return JobSpecificationResponse job spec and sharepoint link of job
     * @throws FailedToGetException if sql exception thrown in dao
     * @throws JobDoesNotExistException if no data returned from dao
     * i.e. job with given id doesn't exist
     */
    public JobSpecificationResponse getJobSpec(final int id)
            throws FailedToGetException, JobDoesNotExistException {

        // call to dao to get job spec with given job id
        JobSpecificationResponse jobSpecificationResponse =
                jobDao.getJobSpec(id);

        // if dao returns null, throw job does not exist exception
        if (jobSpecificationResponse == null) {
            throw new JobDoesNotExistException();
        }

        // if non-null, return response from dao
        return jobSpecificationResponse;
    }

    /**
     * Calls to the dao to inserts data to the job role table in the
     * db and returns id for new row.
     * @param job (job name, spec, sharepoint, band level ID and job family ID)
     * @return jobId
     * @throws FailedToCreateJobException if sql exception thrown in dao
     * @throws InvalidJobException if job input is invalid
     */
    public int createJob(final CreateJob job) throws
            FailedToCreateJobException, InvalidJobException {
        try {

            //assign DAO response to id variable
            int id = jobDao.createJob(job);

            String validation= jobValidator.validate(job);

            //if input does not meet validation rules, throw exception
            if(validation!=null) {
                throw new InvalidJobException(validation);
            }

            //if -1, the job has not been added to db, throw exception
            if (id == -1) {
                throw new FailedToCreateJobException();
            }

            //otherwise return the generated id
            return id;

            //catch SQL exception and throw the appropriate message
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToCreateJobException();
        }
    }
}
