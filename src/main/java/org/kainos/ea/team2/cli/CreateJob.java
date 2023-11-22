package org.kainos.ea.team2.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateJob {
    /**
     * name of job.
     */
    private String jobName;

    /**
     * Specification summary of job.
     */
    private String jobSpecification;

    /**
     * Link to full job spec on sharepoint.
     */
    private String sharepointLink;

    /**
     * Band level ID of job.
     */
    private int jobBandLevelId;

    /**
     * Job family ID of job.
     */
    private int jobFamilyId;

    /**
     * Constructor with args - creates a job with a name, specification,
     * sharepoint link, band level and job family.
     * @param jobName
     * @param jobSpecification
     * @param sharepointLink
     * @param jobBandLevelId
     * @param jobFamilyId
     */
    @JsonCreator
    public CreateJob(@JsonProperty("jobName") final String jobName,
               @JsonProperty("jobSpecification") final String jobSpecification,
               @JsonProperty("sharepointLink") final String sharepointLink,
               @JsonProperty("jobBandLevelId") final int jobBandLevelId,
               @JsonProperty("jobFamilyId") final int jobFamilyId)  {
        this.jobName = jobName;
        this.jobSpecification = jobSpecification;
        this.sharepointLink = sharepointLink;
        this.jobBandLevelId = jobBandLevelId;
        this.jobFamilyId = jobFamilyId;
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
     * @param jobName
     */
    public void setJobName(final String jobName) {
        this.jobName = jobName;
    }

    /**
     * get specification of job.
     * @return jobSpecification.
     */
    public String getJobSpecification() {
        return jobSpecification;
    }

    /**
     * set specification of job.
     * @param jobSpecification
     */
    public void setJobSpecification(final String jobSpecification) {
        this.jobSpecification = jobSpecification;
    }

    /**
     * get link to job sharepoint.
     * @return sharepointLink.
     */
    public String getSharepointLink() {
        return sharepointLink;
    }

    /**
     * set link to job sharepoint.
     * @param sharepointLink
     */
    public void setSharepointLink(final String sharepointLink) {
        this.sharepointLink = sharepointLink;
    }

    /**
     * get job band level ID.
     * @return jobBandLevelId.
     */
    public int getJobBandLevelId() {
        return jobBandLevelId;
    }

    /**
     * set job band level ID.
     * @param jobBandLevelId
     */
    public void setJobBandLevelId(final int jobBandLevelId) {
        this.jobBandLevelId = jobBandLevelId;
    }

    /**
     * get job family ID.
     * @return jobFamilyId.
     */
    public int getJobFamilyId() {
        return jobFamilyId;
    }

    /**
     * set job family ID.
     * @param jobFamilyId
     */
    public void setJobFamilyId(final int jobFamilyId) {
        this.jobFamilyId = jobFamilyId;
    }


}
