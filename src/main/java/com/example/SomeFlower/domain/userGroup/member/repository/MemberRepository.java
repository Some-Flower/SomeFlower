package com.example.SomeFlower.domain.userGroup.member.repository;
import com.example.SomeFlower.domain.userGroup.Status;
import com.example.SomeFlower.domain.userGroup.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

    //select m from user m where m.nickname = ?
    Optional<Member> findByNickName(@Param("nickName") String nickName);

    Optional<Member> findByEmail(@Param("email") String email);

    Page<Member> findMemberByStatus(Status status, Pageable pageable);

}
