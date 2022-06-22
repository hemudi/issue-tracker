package org.codesquad.team34.issuetracker.member;

import java.util.Optional;
import org.codesquad.team34.issuetracker.auth.OAuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByoAuthProviderAndUserId(OAuthProvider oAuthProvider, String userId);
}
