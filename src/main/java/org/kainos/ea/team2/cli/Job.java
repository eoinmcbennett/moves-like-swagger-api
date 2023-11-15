/**
 * Creates objects from data returned from dao.
 */
package org.kainos.ea.team2.cli;

/**
 * Job object contains attributes of jobId and jobName.
 */
public class Job {

    // instance vars

    /**
     * Name of job.
     */
    private String jobName;

    /**
     * id of job.
     */
    private int jobId;

    /**
     * Constructor with args - creates a job with a name and id.
     * @param jobId
     * @param jobName
     */
    public Job(final int jobId, final String jobName) {
        this.jobName = jobName;
        this.jobId = jobId;
    }

    // getters and setters

    /**
     * get name of job.
     * @return String jobName
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * set name of job.
     * @param jobName String jobName
     */
    public void setJobName(final String jobName) {
        this.jobName = jobName;
    }

    /**
     * get id of job.
     * @return jobId.
     */
    public int getJobId() {
        return jobId;
    }

    /**
     * set id of job.
     * @param jobId
     */
    public void setJobId(final int jobId) {
        this.jobId = jobId;
    }


}
