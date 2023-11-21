package org.kainos.ea.team2.api;

import org.kainos.ea.team2.cli.Job;
import org.kainos.ea.team2.cli.JobSpecificationResponse;
import org.kainos.ea.team2.db.IJobDAO;
import org.kainos.ea.team2.exception.FailedToGetException;
import org.kainos.ea.team2.exception.JobDoesNotExistException;

import java.util.List;

public class JobService {

    /**
     * create instance of job dao interface.
     */
    private IJobDAO jobDao;

    /**
     * create instance of jobservice class using jobdao interface.
     * @param jobDao
     */
    public JobService(final IJobDAO jobDao) {
        this.jobDao = jobDao;
    }

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
     * Calls the JobDao to delete a job with a specified JobID
     * @param jobID
     */
    public void deleteJob(int jobID) throws JobDoesNotExistException, FailedToGetException {

        // Check if job exists before attempting to delete it
        if (jobDao.getJobSpec(jobID) == null) {
            throw new JobDoesNotExistException();
        }

        jobDao.deleteJob(jobID);
    }
}
