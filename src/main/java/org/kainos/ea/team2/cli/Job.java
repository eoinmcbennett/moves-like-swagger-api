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

    private String capabilityName;

    /**
     * Constructor with args - creates a job with a name and id.
     * @param jobId
     * @param jobName
     */
    public Job(final int jobId, final String jobName, final String capabilityName) {
        this.jobName = jobName;
        this.jobId = jobId;
        this.capabilityName = capabilityName;
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

    /**
     * set name of capability.
     * @param capabilityName
     */
    public void setCapabilityName(String capabilityName) { this.capabilityName = capabilityName;}

    /**
     * get name of capability.
     * @return capabilityName.
     */
    public String getCapabilityName() { return capabilityName; }
}
