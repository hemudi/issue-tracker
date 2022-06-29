package org.codesquad.team34.issuetracker.member;

import java.util.NoSuchElementException;
import org.codesquad.team34.issuetracker.member.dto.MemberDto;
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
}
