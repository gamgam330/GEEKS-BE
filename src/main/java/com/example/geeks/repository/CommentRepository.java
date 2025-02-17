package com.example.geeks.repository;

import com.example.geeks.domain.Comment;
import com.example.geeks.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom {

    @Query("select c from Comment c " +
            "left join fetch c.post p " +
            "where c.member.id = :userId " +
            "order by c.createdDate desc ")
    List<Comment> findCommentHistory(@Param("userId") Long userId);

    @Modifying
    @Query("delete from Comment c " +
            "where c.member.id = :id ")
    void deleteByMemberId(@Param("id") Long id);
}
