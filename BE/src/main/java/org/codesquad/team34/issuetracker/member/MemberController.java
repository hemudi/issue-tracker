package org.codesquad.team34.issuetracker.member;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.codesquad.team34.issuetracker.common.LoginInterceptor;
import org.codesquad.team34.issuetracker.member.dto.MemberDto;
import org.codesquad.team34.issuetracker.member.dto.MemberListResponse;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Members")
@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("api/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @Operation(summary = "회원 목록 조회")
    @GetMapping
    public ResponseEntity<MemberListResponse> listMembers(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(memberService.findAll(pageable));
    }

    @Operation(summary = "현재 사용자 정보 조회")
    @GetMapping("/current")
    public ResponseEntity<MemberDto> currentUser(
        @RequestAttribute(name = LoginInterceptor.CURRENT_LOGIN) MemberDto currentMember) {

        return ResponseEntity.ok(currentMember);
    }
}
