package org.kainos.ea.team2.db;


import org.kainos.ea.team2.cli.Job;
import org.kainos.ea.team2.cli.JobSpecificationResponse;
import org.kainos.ea.team2.cli.CreateJob;
import org.kainos.ea.team2.cli.BandLevel;
import org.kainos.ea.team2.cli.JobFamily;
import org.kainos.ea.team2.exception.FailedToCreateJobException;
import org.kainos.ea.team2.exception.FailedToGetException;
<<<<<<< HEAD
=======
import org.kainos.ea.team2.exception.JobDoesNotExistException;

>>>>>>> origin/main
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
<<<<<<< HEAD
     * Interface of job dao class, inserts data to db and returns
     * id for new row.
     * @param job (job name, spec, sharepoint, band level ID and job family ID)
     * @return jobiD
     * @throws FailedToCreateJobException
     */
    int createJob(CreateJob job) throws FailedToCreateJobException;

    /**
     * Interface of job dao class, returns list of band levels from db.
     * @return List of type BandLevel
     * @throws FailedToGetException if sql error thrown in dao
     */
    List<BandLevel> getBandLevels() throws FailedToGetException;

    /**
     * Interface of job dao class, returns list of jobFamilies from db.
     * @return List of type JobFamily
     * @throws FailedToGetException if sql error thrown in dao
     */
    List<JobFamily> getJobFamilies() throws FailedToGetException;
=======
     * Interface of job dao class, deletes a job from the database.
     * @param jobID The ID of the job to delete
     * @throws FailedToGetException If SQL error occurs in the DAO
     * @throws JobDoesNotExistException If no job with the specified ID exists
     */
    void deleteJob(int jobID)
            throws FailedToGetException, JobDoesNotExistException;
>>>>>>> origin/main
}
