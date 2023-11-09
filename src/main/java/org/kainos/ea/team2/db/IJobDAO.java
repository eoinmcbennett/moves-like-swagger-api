package org.kainos.ea.team2.db;

import org.kainos.ea.team2.cli.Job;
import org.kainos.ea.team2.exception.CouldNotGetJobsException;

import java.util.List;

public interface IJobDAO {
    List<Job> getJobs() throws CouldNotGetJobsException;
}
