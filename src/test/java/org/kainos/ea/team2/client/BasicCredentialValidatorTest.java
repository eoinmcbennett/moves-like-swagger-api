package org.kainos.ea.team2.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kainos.ea.team2.cli.BasicCredentials;

public class BasicCredentialValidatorTest {
    private static final IValidator<BasicCredentials> validator = new BasicCredentialValidator();

    @Test
    void validator_shouldReturnErrorMessage_whenCredentialObjectIsNull() {
        Assertions.assertNotNull(validator.validate(null));
    }

    @Test
    void validator_shouldReturnErrorMessage_whenCredentialObjectFieldsAreNull() {
        Assertions.assertNotNull(validator.validate(new BasicCredentials(null,null)));
    }

    @Test
    void validator_shouldReturnErrorMessage_whenUserNameIsEmptyString() {
        Assertions.assertNotNull(validator.validate(new BasicCredentials("","testing")));
    }

    @Test
    void validator_shouldReturnErrorMessage_whenPasswordIsEmptyString() {
        Assertions.assertNotNull(validator.validate(new BasicCredentials("test","")));
    }
}
