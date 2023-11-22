package org.kainos.ea.team2.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobFamily {

    /**
     * ID of job family.
     */
    private int jobFamilyId;

    /**
     * name of job family.
     */
    private String jobFamilyName;

    /**
     * Constructor with args - creates a job family with a name and id.
     * @param jobFamilyId
     * @param jobFamilyName
     */
    @JsonCreator
    public JobFamily(@JsonProperty("jobFamily") final int jobFamilyId,
                     @JsonProperty("jobFamilyName")
                     final String jobFamilyName) {
        this.jobFamilyId = jobFamilyId;
        this.jobFamilyName = jobFamilyName;
    }

    /**
     * get ID of job family.
     * @return String jobFamilyName
     */
    public int getJobFamilyId() {
        return jobFamilyId;
    }

    /**
     * set ID of job family.
     * @param jobFamilyId
     */
    public void setJobFamilyId(final int jobFamilyId) {

        this.jobFamilyId = jobFamilyId;
    }

    /**
     * get name of job family.
     * @return String jobFamilyName
     */
    public String getJobFamilyName() {
        return jobFamilyName;
    }

    /**
     * set name of job family.
     * @param jobFamilyName
     */
    public void setJobFamilyName(final String jobFamilyName) {

        this.jobFamilyName = jobFamilyName;
    }
}
