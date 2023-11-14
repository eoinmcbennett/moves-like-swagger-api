/**
 * Creates objects from data returned from dao.
 */
package org.kainos.ea.team2.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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
     * constructor with args - creates a job with a job id and job name.
     * @param jobId
     * @param jobName
     */
    @JsonCreator
    public Job(@JsonProperty("jobId") final int jobId,
               @JsonProperty("jobName") final String jobName) {
        this.jobId = jobId;
        this.jobName = jobName;
    }

    // getters and setters

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
     * get name of job.
     * @return jobId.
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * set name of job.
     * @param jobName
     */
    public void setJobName(final String jobName) {
        this.jobName = jobName;
    }
}
