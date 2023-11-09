package org.kainos.ea.team2.api;

import org.kainos.ea.team2.cli.Job;
import org.kainos.ea.team2.db.IJobDAO;
import org.kainos.ea.team2.exception.CouldNotGetJobsException;

import java.util.List;

public class JobService {
    private IJobDAO jobDao;

    public JobService(IJobDAO jobDao){
        this.jobDao = jobDao;
    }

    /**
     * calls to dao to return a list of jobs from database.
     * @return List<Job>
     * @throws CouldNotGetJobsException if sql error thrown in dao class
     */
    public List<Job> getJobs() throws CouldNotGetJobsException {

        // call to job dao
        return jobDao.getJobs();
    }
}
