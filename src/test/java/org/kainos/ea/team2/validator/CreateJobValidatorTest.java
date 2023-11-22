package org.kainos.ea.team2.validator;

import org.junit.jupiter.api.Test;
import org.kainos.ea.team2.cli.CreateJob;
import org.kainos.ea.team2.client.CreateJobValidator;

import static org.junit.jupiter.api.Assertions.*;

public class CreateJobValidatorTest {
    CreateJobValidator validator = new CreateJobValidator();

    @Test
    public void isValidJob_shouldReturnNull_whenValidJob() {

        CreateJob job = new CreateJob(
                "Director",
                "Makes films.",
                "www.movies.com",
                1,
                4
        );

        assertNull(validator.validate(job));
    }

    @Test
    public void isInvalidJob_shouldReturnMessage_whenJobNameNull() {

        CreateJob job = new CreateJob(
                null,
                "Makes films.",
                "www.movies.com",
                1,
                4
        );
        String expected="The job name is null";

        assertEquals(expected, validator.validate(job));
    }

    @Test
    public void isInvalidJob_shouldReturnMessage_whenJobNameEmpty() {

        CreateJob job = new CreateJob(
                "",
                "Makes films.",
                "www.movies.com",
                1,
                4
        );
        String expected="The job name must be between 1 and 80 characters";

        assertEquals(expected, validator.validate(job));
    }

    @Test
    public void isInvalidJob_shouldReturnMessage_whenJobNameHasTooManyChars() {

        CreateJob job = new CreateJob(
                "abpwtupudmdhxsilqzppyvqdnmnmlkzdclvvoafueluqimgttkjhbqzvntlishfysivlxefzjgbnmoxvlqb",
                "Makes films.",
                "www.movies.com",
                1,
                4
        );
        String expected="The job name must be between 1 and 80 characters";

        assertEquals(expected, validator.validate(job));
    }

    @Test
    public void isInvalidJob_shouldReturnMessage_whenJobSpecNull() {

        CreateJob job = new CreateJob(
                "Director",
                null,
                "www.movies.com",
                1,
                4
        );
        String expected="The job spec is null";

        assertEquals(expected, validator.validate(job));
    }

    @Test
    public void isInvalidJob_shouldReturnMessage_whenJobSpecEmpty() {

        CreateJob job = new CreateJob(
                "Director",
                "",
                "www.movies.com",
                1,
                4
        );
        String expected="The job spec must have at least 1 character";

        assertEquals(expected, validator.validate(job));
    }

    @Test
    public void isInvalidJob_shouldReturnMessage_whenSharepointLinkIsNull() {

        CreateJob job = new CreateJob(
                "Director",
                "Makes films.",
                null,
                1,
                4
        );
        String expected="The sharepoint link is null";

        assertEquals(expected, validator.validate(job));
    }

    @Test
    public void isInvalidJob_shouldReturnMessage_whenSharepointLinkEmpty() {

        CreateJob job = new CreateJob(
                "Director",
                "Makes Movies",
                "",
                1,
                4
        );
        String expected="The sharepoint link must have at least 1 character";

        assertEquals(expected, validator.validate(job));
    }
}
