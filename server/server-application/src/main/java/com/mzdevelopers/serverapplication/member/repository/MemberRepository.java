package com.mzdevelopers.serverapplication.member.repository;

import com.mzdevelopers.serverapplication.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
//    Optional<Member> findByName(String name);
//    Optional<Member> findByRefreshToken(String refreshToken);
//    Optional<Member> findBySocialTypeAndSocialId(SocialType socialType, String socialId);
    Member findByMemberId(long memberId);

}
