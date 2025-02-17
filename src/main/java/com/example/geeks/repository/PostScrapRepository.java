package com.example.geeks.repository;

import com.example.geeks.domain.Member;
import com.example.geeks.domain.Post;
import com.example.geeks.domain.PostScrap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostScrapRepository extends JpaRepository<PostScrap, Long> {
    Optional<PostScrap> findByMemberAndPost(@Param("member")Member member,
                                            @Param("post")Post post);

    Optional<PostScrap> findByMemberIdAndPostId(@Param("memberId") Long memberId,
                                                @Param("postId") Long postId);

    @Query("select ps from PostScrap ps " +
            "left join fetch ps.post p " +
            "where ps.member.id = :userId")
    List<PostScrap> findPostUserScraps(@Param("userId") Long userId);

    @Modifying
    @Query("delete from PostScrap ps " +
            "where ps.member.id = :id ")
    void deleteByMemberId(@Param("id") Long id);
}
