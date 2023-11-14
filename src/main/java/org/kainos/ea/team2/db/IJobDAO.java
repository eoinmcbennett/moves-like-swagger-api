package org.kainos.ea.team2.db;

import org.kainos.ea.team2.cli.Job;
import org.kainos.ea.team2.exception.CouldNotGetJobsException;

import java.util.List;

public interface IJobDAO {

    /**
     * Interface of job dao class, returns list of jobs from db.
     * @return List of type Job
     * @throws CouldNotGetJobsException
     */
    List<Job> getJobs() throws CouldNotGetJobsException;
}
