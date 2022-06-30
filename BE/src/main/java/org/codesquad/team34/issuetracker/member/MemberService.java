package org.codesquad.team34.issuetracker.member;

import java.util.NoSuchElementException;
import org.codesquad.team34.issuetracker.member.dto.MemberDto;
import org.codesquad.team34.issuetracker.member.dto.MemberListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Member saveMember(Member member) {
        return memberRepository.findByoAuthProviderAndUserId(
                member.getOAuthProvider(),
                member.getUserId())
            .map(existingMember -> existingMember.updateProfile(member))
            .orElseGet(() -> memberRepository.save(member));
    }

    @Transactional(readOnly = true)
    public MemberDto findMember(Long id) {
        return memberRepository.findById(id)
            .map(MemberDto::fromEntity)
            .orElseThrow(() -> new NoSuchElementException("존재하지 않는 회원입니다."));
    }

    @Transactional(readOnly = true)
    public MemberListResponse findAll(Pageable pageable) {
        Page<Member> members = memberRepository.findAll(pageable);

        return MemberListResponse.fromEntities(
            members.getTotalElements(),
            members.getNumber() + 1,
            members.getSize(),
            members.getContent()
        );
    }
}
