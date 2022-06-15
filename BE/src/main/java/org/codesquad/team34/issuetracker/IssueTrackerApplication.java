package org.codesquad.team34.issuetracker;

import org.codesquad.team34.issuetracker.auth.OAuthProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(OAuthProperties.class)
public class IssueTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(IssueTrackerApplication.class, args);
    }

}
