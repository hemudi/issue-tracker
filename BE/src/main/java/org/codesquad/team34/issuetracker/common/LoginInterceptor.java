package org.codesquad.team34.issuetracker.common;

import io.jsonwebtoken.JwtException;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codesquad.team34.issuetracker.auth.LoginToken;
import org.codesquad.team34.issuetracker.member.Member;
import org.codesquad.team34.issuetracker.member.MemberRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    public static final String CURRENT_LOGIN = "current_login";
    private static final String BEARER = "Bearer";
    private static final String SPACE = " ";
    private final MemberRepository memberRepository;

    public LoginInterceptor(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) {

        try {
            LoginToken loginToken = extractLoginToken(request);

            Member member = memberRepository.findById(loginToken.getMemberId())
                .orElseThrow(NoSuchElementException::new);

            request.setAttribute(CURRENT_LOGIN, member);

            return true;
        } catch (NoSuchFieldException | NoSuchElementException | JwtException e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());

            return false;
        }
    }

    private LoginToken extractLoginToken(HttpServletRequest request) throws NoSuchFieldException {
        String token = Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
            .filter(this::isBearerAuth)
            .map(this::getBearerToken)
            .orElseThrow(NoSuchFieldException::new);

        return new LoginToken(token);
    }

    private boolean isBearerAuth(String authorization) {
        return authorization.startsWith(BEARER);
    }

    private String getBearerToken(String bearerAuth) {
        return Optional.of(bearerAuth.split(SPACE))
            .filter(arr -> arr.length == 2)
            .map(arr -> arr[1])
            .orElseThrow(NoSuchElementException::new);
    }

}
