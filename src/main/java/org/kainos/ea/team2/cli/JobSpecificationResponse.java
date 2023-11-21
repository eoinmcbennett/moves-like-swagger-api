package org.kainos.ea.team2.cli;
import java.util.List;

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

    /**
     * Link to full job spec on sharepoint.
     */
    private List<String> responsibilitiesList;

    // constructor

    /**
     * Constructor with args - creates a job specification response
     * with a job name, job spec summary, sharepoint link and responsibilities
     * @param jobName
     * @param jobSpecification
     * @param sharepointLink
     * @param responsibilitiesList
     */
    public JobSpecificationResponse(final String jobName,
                                    final String jobSpecification,
                                    final String sharepointLink,
                                    final List<String> responsibilitiesList) {
        this.jobName = jobName;
        this.jobSpecification = jobSpecification;
        this.sharepointLink = sharepointLink;
        this.responsibilitiesList = responsibilitiesList;
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

    /**
     * Get job responsibilities.
     * @return String job responsibilities
     */
    public List<String> getResponsibilitiesList() {
        return responsibilitiesList;
    }

    /**
     * Set job responsibilities.
     * @param responsibilitiesList
     */
    public void setResponsibilitiesList(List<String> responsibilitiesList) {

        this.responsibilitiesList = responsibilitiesList;
    }
}