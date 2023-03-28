package com.example.SomeFlower.domain.member.repository;

import com.example.SomeFlower.domain.member.Member;
import com.example.SomeFlower.domain.member.dto.MemberDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import static com.example.SomeFlower.domain.member.QMember.member;

@Repository
@RequiredArgsConstructor
@Slf4j
public class QMemberRepository {

    private final JPAQueryFactory jpaQueryFactory;

    /**
     * 로그인 querydsl 연습
     */
    public Member login(MemberDto.LoginDto loginDto){
        return jpaQueryFactory.selectFrom(member)
                .where(member.email.eq(loginDto.getEmail()))
                .fetchOne();
    }

    /**
     * 회원 삭제
     */
    public long deleteMember(String email){
        return jpaQueryFactory.
                delete(member)
                .where(member.email.eq(email))
                .execute();
    }
}
