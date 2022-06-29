package org.codesquad.team34.issuetracker.member;

import org.codesquad.team34.issuetracker.common.LoginInterceptor;
import org.codesquad.team34.issuetracker.member.dto.MemberDto;
import org.codesquad.team34.issuetracker.member.dto.MemberListResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity<MemberListResponse> listMembers(Pageable pageable) {
        return ResponseEntity.ok(memberService.findAll(pageable));
    }

    @GetMapping("/current")
    public ResponseEntity<MemberDto> currentUser(@
        RequestAttribute(name = LoginInterceptor.CURRENT_LOGIN) MemberDto currentMember) {

        return ResponseEntity.ok(currentMember);
    }
}
