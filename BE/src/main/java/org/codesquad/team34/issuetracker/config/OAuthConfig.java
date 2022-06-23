package org.codesquad.team34.issuetracker.config;

import org.codesquad.team34.issuetracker.auth.OAuthProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(OAuthProperties.class)
public class OAuthConfig {

}
