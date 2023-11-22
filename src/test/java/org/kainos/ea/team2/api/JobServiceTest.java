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
import org.kainos.ea.team2.exception.FailedToCreateJobException;
import org.kainos.ea.team2.exception.FailedToGetException;
import org.kainos.ea.team2.exception.InvalidJobException;
import org.kainos.ea.team2.exception.JobDoesNotExistException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.ArrayList;
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

    //create instance of create job class to test
    private static CreateJob job;

    /**
     * Initialise variables before each test is run.
     */
    @BeforeEach
    void setupVars() {
        // create mock instance of jobdao class
        jobDao = Mockito.mock(JobDao.class);

        // initialise job service class (being tested)
        jobService = new JobService(jobDao);

        //initialise job details to test
        job = new CreateJob(
                "Farmer",
                "Farming",
                "www.farm.com",
                2,
                2
        );

    }

    /**
     * Verify that the correct list of jobs is returned
     * when getJobs method is called in service class.
     *
     * @throws FailedToGetException if sql error caught
     */
    @Test
    void whenGetJobsCalled_jobServicesReturnsListOfJobs() throws FailedToGetException, SQLException {

        // create jobs
        Job job1 = new Job(1, "Software Engineer", "Engineering", new BandLevel(1, "Trainee"));
        Job job2 = new Job(2, "QA Tester", "Engineering", new BandLevel(1, "Trainee"));
        Job job3 = new Job(2, "Security Engineer", "Cyber Security", new BandLevel(1, "Associate"));

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
        Assertions.assertEquals(jobs, testJobs);
    }

    /**
     * Verify that could not get jobs exception is thrown by job service class
     * when the exception is thrown by dao class.
     *
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
     *
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
        Assertions.assertEquals(jobs.size(), 0);
    }

    /**
     * Verify that the correct list of band levels is returned
     * when getBandLevels method is called in service class.
     * @throws FailedToGetException if sql error caught
     */
    @Test
    void whenGetBandLevelsCalled_jobServicesReturnsListOfBandLevels() throws FailedToGetException, SQLException {

        // create band levels
        BandLevel bandLevel1 = new BandLevel(1, "Trainee");
        BandLevel bandLevel2 = new BandLevel(2, "Associate");

        // create array list of band levels
        List<BandLevel> testBandLevels = new ArrayList<>();
        // add band levels to array list
        testBandLevels.add(bandLevel1);
        testBandLevels.add(bandLevel2);

        // specify return of band levels when dao is called
        Mockito.when(jobDao.getBandLevels()).thenReturn(testBandLevels);

        // job service class calls to job dao class to get band levels
        List<BandLevel> bandLevels = jobService.getBandLevels();

        // check band levels returned from job service class equal test band levels list
        Assertions.assertEquals(bandLevels,testBandLevels);
    }

    /**
     * Verify that could not get band levels exception is thrown by job service class
     * when the exception is thrown by dao class.
     * @throws FailedToGetException if sql error caught by dao class
     */
    @Test
    void whenGetBandLevelsCalled_shouldThrowFailedToGetException_whenDaoThrowsFailedToGetException() throws FailedToGetException {

        // specify could not get jobs exception is thrown when get jobs method is called in dao class
        Mockito.when(jobDao.getBandLevels()).thenThrow(FailedToGetException.class);

        // check could not get jobs exception is thrown when get jobs method is called in job service class
        Assertions.assertThrows(FailedToGetException.class, jobService::getBandLevels);
    }

    /**
     * Verify that an empty list of band levels is returned
     * when the job dao class returns no data from the database.
     * @throws FailedToGetException
     */
    @Test
    void whenGetBandLevelsCalled_shouldReturnEmptyList_whenDAOProvidesNoData() throws FailedToGetException {

        // specify that an array list of jobs is returned when get jobs method called in job dao class
        Mockito.when(jobDao.getBandLevels()).thenReturn(new ArrayList<BandLevel>());
        // job service calls get jobs method
        List<BandLevel> bandLevels = jobService.getBandLevels();

        // check array list is not null
        Assertions.assertNotNull(bandLevels);
        // check array list is empty
        Assertions.assertEquals(bandLevels.size(),0);
    }

    /**
     * Verify that the correct list of job families is returned
     * when getJobFamilies method is called in service class.
     * @throws FailedToGetException if sql error caught
     */
    @Test
    void whenGetJobFamiliesCalled_jobServicesReturnsListOfJobFamilies() throws FailedToGetException, SQLException {

        // create job families
        JobFamily jobFamily1 = new JobFamily(1, "Catering");
        JobFamily jobFamily2 = new JobFamily(2, "Administration");

        // create array list of job families
        List<JobFamily> testJobFamilies = new ArrayList<>();

        // add job families to array list
        testJobFamilies.add(jobFamily1);
        testJobFamilies.add(jobFamily2);

        // specify return of job families when dao is called
        Mockito.when(jobDao.getJobFamilies()).thenReturn(testJobFamilies);

        // job service class calls to job dao class to get job families
        List<JobFamily> jobFamilies = jobService.getJobFamilies();

        // check job families returned from job service class equal test job families list
        Assertions.assertEquals(jobFamilies,testJobFamilies);
    }

    /**
     * Verify that could not get job families exception is thrown by job service class
     * when the exception is thrown by dao class.
     * @throws FailedToGetException if sql error caught by dao class
     */
    @Test
    void whenGetJobFamiliesCalled_shouldThrowFailedToException_whenDaoThrowsFailedToGetException() throws FailedToGetException {

        // specify could not get jobs exception is thrown when get jobs method is called in dao class
        Mockito.when(jobDao.getJobFamilies()).thenThrow(FailedToGetException.class);

        // check could not get jobs exception is thrown when get jobs method is called in job service class
        Assertions.assertThrows(FailedToGetException.class, jobService::getJobFamilies);
    }

    /**
     * Verify that an empty list of job families is returned
     * when the job dao class returns no data from the database.
     * @throws FailedToGetException
     */
    @Test
    void whenGetJobFamiliesCalled_shouldReturnEmptyList_whenDAOProvidesNoData() throws FailedToGetException {

        // specify that an array list of jobs is returned when get jobs method called in job dao class
        Mockito.when(jobDao.getJobFamilies()).thenReturn(new ArrayList<JobFamily>());
        // job service calls get jobs method
        List<JobFamily> jobFamilies = jobService.getJobFamilies();

        // check array list is not null
        Assertions.assertNotNull(jobFamilies);
        // check array list is empty
        Assertions.assertEquals(jobFamilies.size(),0);
    }

    /**
     * Testing getJobSpec method.
     * Verifies that job service returns the job spec and sharepoint link
     * when the dao returns these from db.
     */
    @Test
    void whenGetJobSpecCalled_shouldReturnJobSpec() throws FailedToGetException, JobDoesNotExistException {

        // create a job spec request to be returned
        JobSpecificationResponse expectedJobSpecificationResponse = new JobSpecificationResponse(1, "job name", "test job specification", "https://kainos-sharepoint/job/1");

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

    @Test
    void createJob_shouldReturnId_whenDaoReturnsId() throws InvalidJobException, FailedToCreateJobException {
        int expectedResult =1;
        Mockito.when(jobDao.createJob(job)).thenReturn(expectedResult);

        int result = jobService.createJob(job);

        assertEquals(result, expectedResult);
    }

    /**
     * Verify that Failed to create job exception is thrown by job service class
     * when the exception is thrown by dao class.
     * @throws FailedToCreateJobException if sql error caught by dao class
     */
    @Test
    void createJob_shouldThrowFailedToCreateJobException_whenDaoReturnsId() throws InvalidJobException, FailedToCreateJobException {

        // specify failed to create job exception is thrown when create job method is called in dao class
        Mockito.when(jobDao.createJob(job)).thenThrow(FailedToCreateJobException.class);

        // check failed to create job exception thrown when get create job method is called
        assertThrows(FailedToCreateJobException.class,
                () -> jobService.createJob(job));
    }
    
    /**
     * Test for deleteJob method
     * Verifies that the FailedToGetException is passed up from the JobDao
     * @throws JobDoesNotExistException
     * @throws FailedToGetException
     */
    @Test
    void deleteJob_shouldThrowFailedToGetException_whenDaoThrowsFailedToGetException() throws JobDoesNotExistException, FailedToGetException {

        // id of the job to delete
        int jobId = 1;

        // Confirm that the job exists (otherwise a different exception will be thrown)
        JobSpecificationResponse response = new JobSpecificationResponse(1, "test", "test", "test");
        Mockito.when(jobDao.getJobSpec(jobId)).thenReturn(response);

        // Tell the DAO to throw the FailedToGetException
        Mockito.doThrow(new FailedToGetException("Failed to get job.")).when(jobDao).deleteJob(jobId);

        // Assert that the JobService also throws the FailedToGetException
        assertThrows(FailedToGetException.class,
                () -> jobService.deleteJob(jobId));
    }

    /**
     * Test for deleteJob method
     * Verifies that the method throws a JobDoesNotExistException when the job does not exist in the database
     * @throws FailedToGetException
     */
    @Test
    void deleteJob_shouldThrowJobDoesNotExistException_whenJobDoesNotExist() throws FailedToGetException {

        // id of the job to delete
        int jobId = 1;

        // Tell the Dao to return null when checking if the job exists
        Mockito.when(jobDao.getJobSpec(jobId)).thenReturn(null);

        // Assert that the JobService throws JobDoesNotExistException
        assertThrows(JobDoesNotExistException.class,
                () -> jobService.deleteJob(jobId));
    }

    /**
     * Test for deleteJob method
     * Verifies that the method actually calls the JobDao.deleteJob method
     * @throws JobDoesNotExistException
     * @throws FailedToGetException
     */
    @Test
    void deleteJobOnService_shouldCallDeleteJob_onDao() throws FailedToGetException, JobDoesNotExistException {

        // id of the job to delete
        int jobId = 1;

        // Confirm that the job exists (otherwise a different exception will be thrown)
        JobSpecificationResponse response = new JobSpecificationResponse(1, "test", "test", "test");
        Mockito.when(jobDao.getJobSpec(jobId)).thenReturn(response);

        jobService.deleteJob(jobId);

        // Verify that the deleteJob method of the DAO was called
        Mockito.verify(jobDao).deleteJob(1);
    }
}
