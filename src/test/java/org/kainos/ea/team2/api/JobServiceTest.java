package org.kainos.ea.team2.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.team2.cli.BandLevel;
import org.kainos.ea.team2.cli.Job;
import org.kainos.ea.team2.cli.JobSpecificationResponse;
import org.kainos.ea.team2.db.IJobDAO;
import org.kainos.ea.team2.db.JobDao;
import org.kainos.ea.team2.exception.FailedToGetException;
import org.kainos.ea.team2.exception.JobDoesNotExistException;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
     * @throws FailedToGetException if sql error caught
     */
    @Test
    void whenGetJobsCalled_jobServicesReturnsListOfJobs() throws FailedToGetException, SQLException {

        // create jobs
        Job job1 = new Job(1, "Software Engineer","Engineering", new BandLevel(1,"Trainee"));
        Job job2 = new Job(2, "QA Tester","Engineering",new BandLevel(1,"Trainee"));
        Job job3 = new Job(2, "Security Engineer", "Cyber Security", new BandLevel(1,"Associate"));

        // create array list of jobs
        List<Job> testJobs = new ArrayList<>();
        // add jobs to array list
        testJobs.add(job1);
        testJobs.add(job2);
        testJobs.add(job3);

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
     * @throws FailedToGetException if sql error caught by dao class
     */
    @Test
    void whenGetJobsCalled_shouldThrowCouldNotGetJobsException_whenDaoThrowsFailedToGetException() throws FailedToGetException {

        // specify could not get jobs exception is thrown when get jobs method is called in dao class
        Mockito.when(jobDao.getJobs()).thenThrow(FailedToGetException.class);

        // check could not get jobs exception is thrown when get jobs method is called in job service class
        Assertions.assertThrows(FailedToGetException.class, jobService::getJobs);
    }

    /**
     * Verify that an empty list of jobs is returned
     * when the job dao class returns no data from the database.
     * @throws FailedToGetException
     */
    @Test
    void whenGetJobsCalled_shouldReturnEmptyList_whenDAOProvidesNoData() throws FailedToGetException {

        // specify that an array list of jobs is returned when get jobs method called in job dao class
        Mockito.when(jobDao.getJobs()).thenReturn(new ArrayList<Job>());
        // job service calls get jobs method
        List<Job> jobs = jobService.getJobs();

        // check array list is not null
        Assertions.assertNotNull(jobs);
        // check array list is empty
        Assertions.assertEquals(jobs.size(),0);
    }

    /**
     * Testing getJobSpec method.
     * Verifies that job service returns the job spec and sharepoint link
     * and job responsibilities
     * when the dao returns these from db.
     */
    @Test
    void whenGetJobSpecCalled_shouldReturnJobSpec() throws FailedToGetException, JobDoesNotExistException {

        // create a job spec request to be returned
        JobSpecificationResponse expectedJobSpecificationResponse = new JobSpecificationResponse("job name","test job specification","https://kainos-sharepoint/job/1", Collections.singletonList("Responsibility1, Responsibility2"));

        // id of job we want to find spec of
        int jobId = 1;

        // specify that the job spec response is returned when get job spec method called in job dao class
        Mockito.when(jobDao.getJobSpec(jobId)).thenReturn(expectedJobSpecificationResponse);

        // job service calls getJobSpec method
        JobSpecificationResponse actualJobSpecificationResponse = jobService.getJobSpec(jobId);

        // check job spec object returned when call made to get job spec by job service
        assertEquals(expectedJobSpecificationResponse, actualJobSpecificationResponse);
    }

    /**
     * Testing getJobSpec method.
     * Verifies that job service throws a job not found exception when
     * no rows are returned from db.
     */
    @Test
    void whenGetJobSpecCalled_shouldThrowJobDoesNotExistExceptionWhenDaoReturnsEmpty() throws FailedToGetException {

        // id of job that doesn't exist
        int jobId = -1;

        // specify that no rows are returned when get jobs method called in job dao class
        Mockito.when(jobDao.getJobSpec(jobId)).thenReturn(null);

        // check job does not exist exception thrown when get job spec method is called
        assertThrows(JobDoesNotExistException.class,
                () -> jobService.getJobSpec(jobId));
    }

    /**
     * Testing getJobSpec method.
     * Verifies that job service throws a could not get job exception
     * when could not get job exception thrown by dao.
     */
    @Test
    void whenGetJobSpecCalled_shouldThrowCouldNotGetJobExceptionWhenFailedToGetExceptionThrownByDao() throws FailedToGetException {

        // id of job we want to get spec of
        int jobId = 1;

        // specify that failed to get exception is thrown
        // when get jobs method called in job dao class
        Mockito.when(jobDao.getJobSpec(jobId)).thenThrow(FailedToGetException.class);

        // check job does not exist exception thrown when get job spec method is called
        assertThrows(FailedToGetException.class,
                () -> jobService.getJobSpec(jobId));
    }
}
