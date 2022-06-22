package org.codesquad.team34.issuetracker.member;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Member upsertMember(Member member) {
        return memberRepository.findByUserIdAndOAuthProvider(
                member.getUserId(),
                member.getOAuthProvider())
            .map(existingMember -> existingMember.updateProfile(member))
            .orElseGet(() -> memberRepository.save(member));
    }
}
