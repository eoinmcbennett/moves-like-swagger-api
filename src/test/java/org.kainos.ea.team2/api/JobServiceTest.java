package org.kainos.ea.team2.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.team2.cli.Job;
import org.kainos.ea.team2.db.DatabaseConnector;
import org.kainos.ea.team2.db.IJobDAO;
import org.kainos.ea.team2.db.JobDao;
import org.kainos.ea.team2.exception.CouldNotGetJobsException;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * unit testing job service class
 */
@ExtendWith(MockitoExtension.class)
public class JobServiceTest {

    // create instance of jobdao class
    private static IJobDAO jobDao;

    // create instance of job service class (being tested)
    private static JobService jobService;

    /**
     * Initialise variables before each test is run.
     */
    @BeforeEach
    void setupVars(){
        // create mock instance of jobdao class
        jobDao = Mockito.mock(JobDao.class);

        // initialise job service class (being tested)
        jobService = new JobService(jobDao);
    }

    /**
     * Verify that the correct list of jobs is returned
     * when getJobs method is called in service class.
     * @throws CouldNotGetJobsException if sql error caught
     */
    @Test
    void whenGetJobsCalled_jobServicesReturnsListOfJobs() throws CouldNotGetJobsException, SQLException {

        // create jobs
        Job job1 = new Job(1, "Software Engineer");
        Job job2 = new Job(2, "QA Tester");

        // create array list of jobs
        List<Job> testJobs = new ArrayList<>();
        // add jobs to array list
        testJobs.add(job1);
        testJobs.add(job2);

        // specify return of jobs when dao is called
        Mockito.when(jobDao.getJobs()).thenReturn(testJobs);

        // job service class calls to job dao class to get jobs
        List<Job> jobs = jobService.getJobs();

        // check jobs returned from job service class equal test jobs list
        Assertions.assertEquals(jobs,testJobs);
    }

    /**
     * Verify that could not get jobs exception is thrown by job service class
     * when the exception is thrown by dao class.
     * @throws CouldNotGetJobsException if sql error caught by dao class
     */
    @Test
    void whenGetJobsCalled_shouldThrowCouldNotGetJobsException_whenDaoThrowsCouldNotGetJobsException() throws CouldNotGetJobsException {

        // specify could not get jobs exception is thrown when get jobs method is called in dao class
        Mockito.when(jobDao.getJobs()).thenThrow(CouldNotGetJobsException.class);

        // check could not get jobs exception is thrown when get jobs method is called in job service class
        Assertions.assertThrows(CouldNotGetJobsException.class, jobService::getJobs);
    }

    /**
     * Verify that an empty list of jobs is returned
     * when the job dao class returns no data from the database.
     * @throws CouldNotGetJobsException
     */
    @Test
    void whenGetJobsCalled_shouldReturnEmptyList_whenDAOProvidesNoData() throws CouldNotGetJobsException {

        // specify that an array list of jobs is returned when get jobs method called in job dao class
        Mockito.when(jobDao.getJobs()).thenReturn(new ArrayList<Job>());
        // job service calls get jobs method
        List<Job> jobs = jobService.getJobs();

        // check array list is not null
        Assertions.assertNotNull(jobs);
        // check array list is empty
        Assertions.assertEquals(jobs.size(),0);
    }
}
