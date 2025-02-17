package com.example.geeks.repository;

import com.example.geeks.domain.Member;
import com.example.geeks.domain.Point;
import com.example.geeks.domain.RoomMate;
import com.example.geeks.domain.SaveRoomMate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SaveRoomMateRepository extends JpaRepository<SaveRoomMate, Long> {
    @Query("select s from SaveRoomMate s " +
            "left join fetch s.me m " +
            "where m.id = :memberId ")
    List<SaveRoomMate> findAllByIdFetch(@Param("memberId")Long memberId);

    //@Query("select s from SaveRoomMate s where s.me.id = :meId and s.you.id = :youId")
    List<SaveRoomMate> findByMeIdAndYouId(@Param("meId") Long meId,
                                          @Param("youId") Long youId);

    SaveRoomMate findByMeAndYou(Member me, Member you);

    void deleteByMeAndYou(Member me, Member you);


    @Modifying
    @Query("delete from SaveRoomMate sm " +
            "where sm.me = :member or sm.you = :member")
    void deleteByMeOrYou(@Param("member") Member member);

}
