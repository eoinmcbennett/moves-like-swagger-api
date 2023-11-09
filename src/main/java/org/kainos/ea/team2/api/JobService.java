package org.kainos.ea.team2.api;

import org.kainos.ea.team2.cli.Job;
import org.kainos.ea.team2.db.JobDao;
import org.kainos.ea.team2.exception.CouldNotGetJobsException;
import org.kainos.ea.team2.exception.NoJobsAvailableException;

import java.util.List;

public class JobService {

    // create instance of job dao class
    JobDao jobDao = new JobDao();

    /**
     * calls to dao to return a list of jobs from database.
     * @return List<Job>
     * @throws CouldNotGetJobsException if sql error thrown in dao class
     */
    public List<Job> getJobs() throws CouldNotGetJobsException, NoJobsAvailableException {

        // check if jobs list returned from dao is empty
        List<Job> jobList = jobDao.getJobs();

        // if empty, throw a NoJobsAvailableException
        if(jobList == null) {
            throw new NoJobsAvailableException();
        }

        // if jobs list not null, return jobs list
        return jobList;
    }
}
