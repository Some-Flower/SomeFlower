package com.example.SomeFlower.service.member;

import com.example.SomeFlower.config.annotation.Validation;
import com.example.SomeFlower.config.resTemplate.ResponseException;
import com.example.SomeFlower.constant.ResponseTemplateStatus;
import com.example.SomeFlower.domain.userGroup.member.dto.MemberAdapter;
import com.example.SomeFlower.util.AuthToken;
import com.example.SomeFlower.util.JwtService;
import com.example.SomeFlower.domain.userGroup.member.Member;
import com.example.SomeFlower.domain.userGroup.member.MemberStatus;
import com.example.SomeFlower.domain.userGroup.member.dto.MemberAndDtoAdapter;
import com.example.SomeFlower.domain.userGroup.member.dto.MemberDto;
import com.example.SomeFlower.domain.userGroup.member.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.SomeFlower.constant.ResponseTemplateStatus.LOGIN_USER_ERROR;

@Service @Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /**
     * 회원가입
     */
    @Transactional
    @Validation
    public Long join(MemberDto.JoinDto joinDto) {
        validateDuplicateEmail(joinDto.getEmail());
        validateDuplicateNickName(joinDto.getNickName());
        Member member = MemberAndDtoAdapter.dtoToEntity(joinDto);
        member.setPwd(passwordEncoder.encode(joinDto.getPwd()));
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 로그인
     */
    @Transactional
    @Validation
    public AuthToken login(MemberDto.LoginDto loginDto){
        try{
            Member member = memberRepository.findByEmail(loginDto.getEmail()).orElseThrow(NullPointerException::new);
            if (passwordEncoder.matches(loginDto.getPwd(), member.getPwd())){
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginDto.getEmail(),
                                loginDto.getPwd()
                        )
                );
                AuthToken authToken = jwtService.generateToken(new MemberAdapter(member));
                return authToken;
            }
            throw new ResponseException(LOGIN_USER_ERROR);
        }
        catch (NullPointerException e){
            e.printStackTrace();
            throw new ResponseException(LOGIN_USER_ERROR);
        }
    }

    /**
     * 회원 정보 수정
     */
    @Transactional
    @Validation
    public Member update(Long id, MemberDto.UpdateDto updateDto){
        validateDuplicateNickName(updateDto.getNickName());
        Member member = memberRepository.findById(id).get();
        member.update(updateDto);
        return member;
    }

    /**
     * 회원 삭제 -> 삭제 상태로 변경
     */
    @Transactional
    public void changeMemberStatus(Long id){
        Member member = memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        member.setStatus(MemberStatus.DELETED);
    }

    /**
     * 이메일 중복 검사
     */
    public void validateDuplicateEmail(String email) throws ResponseException {
        if (memberRepository.findByEmail(email).isPresent()){
            throw new ResponseException(ResponseTemplateStatus.EMAIL_DUPLICATE);
        }
    }

    /**
     * 닉네임 중복 검사
     */
    public void validateDuplicateNickName(String nickName) throws ResponseException {
        if (memberRepository.findByNickName(nickName).isPresent()){
            throw new ResponseException(ResponseTemplateStatus.NICKNAME_DUPLICATE);
        }
    }

}
