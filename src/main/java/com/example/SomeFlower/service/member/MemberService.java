package com.example.SomeFlower.service.member;

import com.example.SomeFlower.domain.member.Member;
import com.example.SomeFlower.domain.member.dto.MemberAndUserAdapter;
import com.example.SomeFlower.domain.member.MemberStatus;
import com.example.SomeFlower.domain.member.dto.MemberAndDtoAdapter;
import com.example.SomeFlower.domain.member.dto.MemberDto;
import com.example.SomeFlower.domain.member.repository.MemberRepository;
import com.example.SomeFlower.domain.member.repository.QMemberRepository;
import com.example.SomeFlower.util.AuthToken;
import com.example.SomeFlower.util.AuthTokenProvider;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService implements UserDetailsService {

    private final AuthTokenProvider authTokenProvider;
    private final MemberRepository memberRepository;
    private final QMemberRepository qMemberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long join(MemberDto memberDto) throws Exception {
        validateDuplicateEmail(memberDto.getEmail());
        Member member = MemberAndDtoAdapter.dtoToEntity(memberDto);
        member.setPwd(passwordEncoder.encode(memberDto.getPwd()));
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 회원 정보 조회
     */
    public MemberDto getMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return MemberAndDtoAdapter.entityToDto(member);
    }

    /**
     * 회원 정보 수정
     */
    @Transactional
    public MemberDto updateMember(Long id, MemberDto memberDto) {
        Member member = memberRepository.findById(id).get();
        member.update(memberDto);
        return MemberAndDtoAdapter.entityToDto(member);
    }

    /**
     * 회원 삭제 -> 삭제 상태로 변경
     */
    @Transactional
    public void changeMemberStatus(Long id, MemberStatus status){
        Member member = memberRepository.findById(id).get();
        member.setStatus(status);
    }

    /**
     * 이메일 중복 검사
     */
    public void validateDuplicateEmail(String email) throws Exception {
        if (memberRepository.findByEmail(email).isPresent()){
            throw new Exception("이메일 중복");
        }
    }

    /**
     * 로그인
     */
    @Transactional
    public AuthToken login(MemberDto.MemberLoginDto memberLoginDto){
        try{
            Member member = memberRepository.findByEmail(memberLoginDto.getEmail()).orElseThrow(NullPointerException::new);
            if (passwordEncoder.matches(memberLoginDto.getPwd(), member.getPwd())){
                return authTokenProvider.createTokens(MemberAndDtoAdapter.entityToDto(member));
            }
            throw new RuntimeException();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

//    @Transactional(readOnly = true)
//    public UserIsFirstDto isFirstLoginUserCheck(String email) {
//        Member member = memberRepository.findByEmail(email).orElseThrow(NullPointerException::new);
//
//        return UserIsFirstDto.builder().isFirst(false).build();
//
//    }

    /** username이 DB에 존재하는지 확인 **/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return MemberAndUserAdapter.from(MemberAndDtoAdapter.entityToDto(member));
    }
}
