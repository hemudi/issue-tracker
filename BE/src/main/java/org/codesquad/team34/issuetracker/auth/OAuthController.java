package org.codesquad.team34.issuetracker.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login/oauth2/{provider}")
public class OAuthController {

    private final OAuthProperties oAuthProperties;

    @GetMapping
    public RedirectView requestAuthorization(@PathVariable(name = "provider") String provider) {
        return new RedirectView(oAuthProperties.get(provider)
            .getAuthorizationUri()
            .toString());
    }
}
