package org.kainos.ea.team2.client;

import org.kainos.ea.team2.cli.BasicCredentials;
import org.kainos.ea.team2.cli.CreateJob;

public class CreateJobValidator {

    public String validate(final CreateJob job) {

        if (job == null) {
            return "The job is null";
        }

        if (job.getJobName() == null) {
            return "The job name is null";
        }

        if (job.getJobSpecification() == null) {
            return "The job spec is null";
        }

        if (job.getSharepointLink() == null) {
            return "The sharepoint link is null";
        }

        if (job.getJobName().isEmpty() || job.getJobName().length()>80) {
            return "The job name must be between 1 and 80 characters";
        }

        if (job.getJobSpecification().isEmpty()) {
            return "The job spec must have at least 1 character";
        }

        if (job.getSharepointLink().isEmpty()) {
            return "The sharepoint link must have at least 1 character";
        }

        return null;
    }
}
