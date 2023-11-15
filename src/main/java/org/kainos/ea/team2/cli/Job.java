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
     * Name of job.
     */
    private String jobName;

    /**
     * id of job.
     */
    private int jobId;

    /**
     * The band level of the position
     */
    private BandLevel bandLevel;

    /**
     * constructor with args - creates a job with a job id and job name.
     * @param jobId
     * @param jobName
     */
    @JsonCreator
    public Job(@JsonProperty("jobId") final int jobId,
               @JsonProperty("jobName") final String jobName,
                @JsonProperty("bandLevel") final BandLevel bandLevel) {
        this.jobId = jobId;
        this.jobName = jobName;
        this.bandLevel = bandLevel;
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
     * Gets the band level of the position
     * @return the band level
     */
    public BandLevel getBandLevel() {
        return this.bandLevel;
    }

    /**
     * Sets the band level for the position
     * @param bandLevel
     */
    public void setBandLevel(BandLevel bandLevel) {
        this.bandLevel = bandLevel;
    }
}
