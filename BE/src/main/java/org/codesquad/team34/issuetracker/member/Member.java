package org.codesquad.team34.issuetracker.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.codesquad.team34.issuetracker.auth.OAuthProvider;
import org.codesquad.team34.issuetracker.common.BaseEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String userId;
    private String name;
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private OAuthProvider oAuthProvider;

    public Member(String userId, String name, String imageUrl, OAuthProvider oAuthProvider) {
        this.userId = userId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.oAuthProvider = oAuthProvider;
    }

    public Member updateProfile(Member member) {
        this.name = member.name;
        this.imageUrl = member.imageUrl;

        return this;
    }
}
