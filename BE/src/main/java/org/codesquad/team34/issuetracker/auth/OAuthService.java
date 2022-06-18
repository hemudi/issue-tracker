package org.codesquad.team34.issuetracker.auth;

import lombok.RequiredArgsConstructor;
import org.codesquad.team34.issuetracker.member.Member;
import org.codesquad.team34.issuetracker.member.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member upsertMember(Member member) {
        return memberRepository.findByUserIdAndOAuthProvider(
                member.getUserId(),
                member.getOAuthProvider())
            .map(existingMember -> existingMember.updateProfile(member))
            .orElseGet(() -> memberRepository.save(member));
    }
}
