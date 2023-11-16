package org.kainos.ea.team2.cli;

public class JobSpecificationResponse {

    // attributes

    /**
     * Name of job.
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

    // constructor

    /**
     * Constructor with args - creates a job specification response
     * with a job name, job spec summary and sharepoint link.
     * @param jobName
     * @param jobSpecification
     * @param sharepointLink
     */
    public JobSpecificationResponse(final String jobName,
                                    final String jobSpecification,
                                    final String sharepointLink) {
        this.jobName = jobName;
        this.jobSpecification = jobSpecification;
        this.sharepointLink = sharepointLink;
    }


    // getters and setters

    /**
     * Get name of job.
     * @return String jobName
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * Set name of job.
     * @param jobName
     */
    public void setJobName(final String jobName) {
        this.jobName = jobName;
    }

    /**
     * Get job spec.
     * @return String jobSpecification.
     */
    public String getJobSpecification() {
        return jobSpecification;
    }

    /**
     * Set job spec.
     * @param jobSpecification
     */
    public void setJobSpecification(final String jobSpecification) {

        this.jobSpecification = jobSpecification;
    }

    /**
     * Get sharepoint link.
     * @return String sharepoint link.
     */
    public String getSharepointLink() {
        return sharepointLink;
    }

    /**
     * Set sharepoint link.
     * @param sharepointLink
     */
    public void setSharepointLink(final String sharepointLink) {

        this.sharepointLink = sharepointLink;
    }
}
