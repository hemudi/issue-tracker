package org.codesquad.team34.issuetracker.common;

import io.jsonwebtoken.JwtException;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.codesquad.team34.issuetracker.auth.LoginToken;
import org.codesquad.team34.issuetracker.member.MemberService;
import org.codesquad.team34.issuetracker.member.dto.MemberDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    public static final String CURRENT_LOGIN = "current_login";
    private static final String BEARER = "Bearer";
    private static final String SPACE = " ";
    private final MemberService memberService;

    public LoginInterceptor(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) {

        try {
            LoginToken loginToken = extractLoginToken(request);
            MemberDto memberDto = memberService.findMember(loginToken.getMemberId());

            request.setAttribute(CURRENT_LOGIN, memberDto);

            return true;
        } catch (NoSuchElementException | IllegalArgumentException | JwtException e) {
            log.error("다음 경로에 대한 요청에서 로그인 에러 발생: {}", request.getRequestURI());
            e.printStackTrace();
            response.setStatus(HttpStatus.UNAUTHORIZED.value());

            return false;
        }
    }

    private LoginToken extractLoginToken(HttpServletRequest request) {
        String token = Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
            .filter(this::isBearerAuth)
            .map(this::getBearerToken)
            .orElseThrow(NoSuchElementException::new);

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
