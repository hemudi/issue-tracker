package org.codesquad.team34.issuetracker.member;

import org.codesquad.team34.issuetracker.member.dto.MemberListResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
