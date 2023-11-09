package org.kainos.ea.team2.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Job {

    // instance vars

    /**
     * id of job
     */
    int jobId;

    /**
     * name of job
     */
    String jobName;

    /**
     * constructor with args - creates a job with a job id and job name
     * @param jobId
     * @param jobName
     */
    @JsonCreator
    public Job(@JsonProperty("jobId") int jobId, @JsonProperty("jobName") String jobName) {
        this.jobId = jobId;
        this.jobName = jobName;
    }

    // getters and setters

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
}
