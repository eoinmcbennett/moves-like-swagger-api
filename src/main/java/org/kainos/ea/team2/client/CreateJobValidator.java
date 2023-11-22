package org.kainos.ea.team2.client;

import org.kainos.ea.team2.api.JobService;
import org.kainos.ea.team2.cli.BandLevel;
import org.kainos.ea.team2.cli.CreateJob;
import org.kainos.ea.team2.cli.JobFamily;
import org.kainos.ea.team2.db.JobDao;
import org.kainos.ea.team2.exception.FailedToGetException;
import java.util.List;

public class CreateJobValidator {

    /**
     * Constant to assign value 80 for use as max
     * number of chars, to avoid use of magic numbers.
     */
    public static final int MAX_CHARACTERS = 80;

    /**
     * Create instance of job DAO class.
     */
    private JobDao jobDao;

    /**
     * Create instance of job service class.
     */
    private JobService jobService;

    /**
     * List for band levels.
     */
    private List<BandLevel> bandLevels;

    /**
     * List for job families.
     */
    private List<JobFamily> jobFamilies;

    /**
     *Attempts to validate job attributes.
     *@param job the job to validate
     * @return String describing error. null if ok
     */
    public String validate(final CreateJob job) {

            jobDao = new JobDao();
            jobService = new JobService(jobDao);

            try {
                bandLevels = jobService.getBandLevels();

                if (job.getJobBandLevelId() < 1
                        || job.getJobBandLevelId() > bandLevels.size()) {
                    return "The band level does not exist";
                }
            } catch (FailedToGetException e) {

                System.err.println(e.getMessage());
            }

            try {
                jobFamilies = jobService.getJobFamilies();

                if (job.getJobFamilyId() < 1
                        || job.getJobFamilyId() > jobFamilies.size()) {
                    return "The job family does not exist";
                }
            } catch (FailedToGetException e) {

                System.err.println(e.getMessage());

            }

            if (job == null) {
                return "The job is null";
            }

            if (job.getJobName() == null) {
                return "The job name is null";
            }

            if (job.getJobSpecification() == null) {
                return "The job spec is null";
            }

            if (job.getSharepointLink() == null) {
                return "The sharepoint link is null";
            }

            if (job.getJobName().isEmpty() || job.getJobName().length()
                    >= MAX_CHARACTERS) {
                return "The job name must be between 1 and 80 characters";
            }

            if (job.getJobSpecification().isEmpty()) {
                return "The job spec must have at least 1 character";
            }

            if (job.getSharepointLink().isEmpty()) {
                return "The sharepoint link must have at least 1 character";
            }

            return null;

        }
    }

