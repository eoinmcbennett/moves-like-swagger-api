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
     * The band level of the position.
     */
    private BandLevel bandLevel;


    /**
     * Constructor with args - creates a job with a name and id.
     * @param jobId the id of the job
     * @param jobName the name of the job
     * @param jobCapabilityName the capability the job belongs to.
     * @param bandLevel the band level the job is in
     */
    @JsonCreator
    public Job(@JsonProperty("jobId") final int jobId,
               @JsonProperty("jobName") final String jobName,
               @JsonProperty("capability_name")
                   final String jobCapabilityName,
    @JsonProperty("bandLevel") final BandLevel bandLevel) {
        this.jobId = jobId;
        this.jobName = jobName;
        this.jobCapabilityName = jobCapabilityName;
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
     * get id of job.
     * @return jobId.
     */
    public int getJobId() {
        return jobId;
    }

    /**
     * set id of job.
     * @param jobId the id of the job
     */
    public void setJobId(final int jobId) {
        this.jobId = jobId;
    }

    /**
     * Gets the band level of the position.
     * @return the band level
     */
    public BandLevel getBandLevel() {
        return this.bandLevel;
    }

    /**
     * set name of job.
     * @param jobName the name of the job
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
     * @param jobCapabilityName the capability to set
     */
    public void setJobCapabilityName(final String jobCapabilityName) {
        this.jobCapabilityName = jobCapabilityName;
    }

    /**
     * Sets the band level for the position.
     * @param bandLevel the band level object to set
     */
    public void setBandLevel(final BandLevel bandLevel) {
        this.bandLevel = bandLevel;
    }
}
