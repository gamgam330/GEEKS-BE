package com.example.geeks.repository;

import com.example.geeks.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("select m from Member m " +
            "left join fetch m.detail " +
            "where m.email = :email")
    List<Member> findByEmail(@Param("email") String email);

    @Query("select m from Member m where m.nickname = :nickname")
    List<Member> findByNickname(@Param("nickname") String nickname);

    @Query("select m from Member m left join fetch m.detail where m.id = :id")
    Member findMemberFetchJoinWithDetail(@Param("id") Long id);

    @Query("select m from Member m left join fetch m.detail where m.id = :id")
    Optional<Member> findMemberFetchDetail(@Param("id") Long id);

    Long findIdByNickname(@Param("nickname") String nickname);

    @Query("select m from Member m left join fetch m.detail where m.id = :id")
    Optional<Member> findByIdFetchDetail(@Param("id") Long id);

}
