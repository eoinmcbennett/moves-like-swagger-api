/**
 * Creates objects from data returned from dao.
 */
package org.kainos.ea.team2.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Job object contains attributes of jobId and jobName.
 */
public class Job {

    // instance vars

    /**
     * id of job.
     */
    private int jobId;

    /**
     * name of job.
     */
    private String jobName;

    /**
     * capability of job.
     */
    private String jobCapabilityName;

    /**
     * Constructor with args - creates a job with a name and id.
     * @param jobId
     * @param jobName
     * @param jobCapabilityName
     */
    @JsonCreator
    public Job(@JsonProperty("jobId") final int jobId,
               @JsonProperty("jobName") final String jobName,
               @JsonProperty("capability_name")
                   final String jobCapabilityName) {
        this.jobId = jobId;
        this.jobName = jobName;
        this.jobCapabilityName = jobCapabilityName;
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
     * set name of job.
     * @param jobName
     */
    public void setJobName(final String jobName) {
        this.jobName = jobName;
    }

    /**
     * get capability of job.
     * @return jobId.
     */
    public String getJobCapabilityName() {
        return jobCapabilityName;
    }

    /**
     * set capability of job.
     * @param jobCapabilityName
     */
    public void setJobCapabilityName(final String jobCapabilityName) {
        this.jobCapabilityName = jobCapabilityName;
    }

}
