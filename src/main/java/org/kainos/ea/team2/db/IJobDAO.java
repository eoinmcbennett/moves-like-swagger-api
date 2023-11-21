package org.kainos.ea.team2.db;

import org.kainos.ea.team2.cli.Job;
import org.kainos.ea.team2.cli.JobSpecificationResponse;
import org.kainos.ea.team2.exception.FailedToGetException;
import org.kainos.ea.team2.exception.JobDoesNotExistException;

import java.util.List;

public interface IJobDAO {

    /**
     * Interface of job dao class, returns list of jobs from db.
     * @return List of type Job
     * @throws FailedToGetException if sql error thrown in dao
     */
    List<Job> getJobs() throws FailedToGetException;

    /**
     * Interface of job dao class, returns job spec and sharepoint link from db.
     * @param id job id of returned job spec and sharepoint link
     * @return JobSpecificationResponse
     * @throws FailedToGetException if sql error thrown in dao
     */
    JobSpecificationResponse getJobSpec(int id) throws FailedToGetException;

    /**
     * Interface of job dao class, deletes a job from the database.
     * @param jobID The ID of the job to delete
     * @throws FailedToGetException If SQL error occurs in the DAO
     * @throws JobDoesNotExistException If no job with the specified ID exists in the database
     */
    void deleteJob(int jobID) throws FailedToGetException, JobDoesNotExistException;
}
