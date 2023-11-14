package org.kainos.ea.team2.api;

import org.kainos.ea.team2.cli.Job;
import org.kainos.ea.team2.db.IJobDAO;
import org.kainos.ea.team2.exception.CouldNotGetJobsException;

import java.util.List;

public class JobService {

    /**
     * create instance of job dao interface.
     */
    private IJobDAO instanceJobDao;

    /**
     * create instance of jobservice class using jobdao interface.
     * @param jobDao
     */
    public JobService(final IJobDAO jobDao) {
        this.instanceJobDao = jobDao;
    }

    /**
     * calls to dao to return a list of jobs from database.
     * @return List<Job>
     * @throws CouldNotGetJobsException if sql error thrown in dao class
     */
    public List<Job> getJobs() throws CouldNotGetJobsException {

        // call to job dao
        return instanceJobDao.getJobs();
    }
}
