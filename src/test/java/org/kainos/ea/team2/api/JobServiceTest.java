package org.kainos.ea.team2.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.team2.cli.Job;
import org.kainos.ea.team2.db.IJobDAO;
import org.kainos.ea.team2.db.JobDao;
import org.kainos.ea.team2.exception.CouldNotGetJobsException;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class JobServiceTest {

    private static final IJobDAO jobDao = Mockito.mock(JobDao.class);
    private static final JobService jobService = new JobService(jobDao);

    @Test
    void whenGetJobsCalled_jobServicesReturnsListOfJobs() throws CouldNotGetJobsException {
        List<Job> testJobs = new ArrayList<>();
        testJobs.add(new Job(1, "Software Engineer"));
        testJobs.add(new Job(2, "QA Tester"));

        Mockito.when(jobDao.getJobs()).thenReturn(testJobs);

        List<Job> jobs = jobService.getJobs();

        Assertions.assertEquals(jobs,testJobs);
    }

    @Test
    void whenGetJobsCalled_shouldThrowCouldNotGetJobsException_whenDaoThrowsCouldNotGetJobsException() throws CouldNotGetJobsException {
        Mockito.when(jobDao.getJobs()).thenThrow(CouldNotGetJobsException.class);

        Assertions.assertThrows(CouldNotGetJobsException.class, jobService::getJobs);
    }

    @Test
    void whenGetJobsCalled_shouldReturnEmptyList_whenDAOProvidesNoData() throws CouldNotGetJobsException {
        Mockito.when(jobDao.getJobs()).thenReturn(new ArrayList<Job>());

        List<Job> jobs = jobService.getJobs();

        Assertions.assertNotNull(jobs);
        Assertions.assertEquals(jobs.size(),0);
    }
}
