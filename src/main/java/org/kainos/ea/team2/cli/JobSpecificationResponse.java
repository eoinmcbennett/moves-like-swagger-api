package org.kainos.ea.team2.cli;

public class JobSpecificationResponse {

    // attributes

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
     * Constructor with args - creates a job spec request with a
     * specification summary and
     * sharepoint link.
     * @param jobSpecification
     * @param sharepointLink
     */
    public JobSpecificationResponse(final String jobSpecification,
                                    final String sharepointLink) {
        this.jobSpecification = jobSpecification;
        this.sharepointLink = sharepointLink;
    }

    // getters and setters

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
