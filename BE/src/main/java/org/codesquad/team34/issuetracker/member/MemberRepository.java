package org.codesquad.team34.issuetracker.member;

import java.util.Optional;
import org.codesquad.team34.issuetracker.auth.OAuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m "
        + "where m.userId = :userId "
        + "and m.oAuthProvider = :oAuthProvider")
    Optional<Member> findByUserIdAndOAuthProvider(String userId, OAuthProvider oAuthProvider);
}
