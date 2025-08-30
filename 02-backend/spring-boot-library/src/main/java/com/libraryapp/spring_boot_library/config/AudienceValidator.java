package com.libraryapp.spring_boot_library.config;

import java.util.List;

import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.Assert;

/**
 * Validates that the JWT contains the expected audience
 */
public class AudienceValidator implements OAuth2TokenValidator<Jwt> {
    private final String expectedAudience;

    public AudienceValidator(String expectedAudience) {
        Assert.hasText(expectedAudience, "expectedAudience cannot be null or empty");
        this.expectedAudience = expectedAudience;
    }

    @Override
    public OAuth2TokenValidatorResult validate(Jwt jwt) {
        List<String> audiences = jwt.getAudience();

        if (audiences != null && audiences.contains(this.expectedAudience)) {
            return OAuth2TokenValidatorResult.success();
        }

        OAuth2Error error = new OAuth2Error("invalid_audience", "The required audience is missing", null);
        return OAuth2TokenValidatorResult.failure(error);
    }
}
