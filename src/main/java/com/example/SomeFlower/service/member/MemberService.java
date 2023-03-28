package com.example.SomeFlower.service.member;

import com.example.SomeFlower.domain.member.Role;
import com.example.SomeFlower.domain.member.dto.MemberAdapter;
import com.example.SomeFlower.util.AuthToken;
import com.example.SomeFlower.util.JwtService;
import com.example.SomeFlower.domain.member.Member;
import com.example.SomeFlower.domain.member.MemberStatus;
import com.example.SomeFlower.domain.member.dto.MemberAndDtoAdapter;
import com.example.SomeFlower.domain.member.dto.MemberDto;
import com.example.SomeFlower.domain.member.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public Long join(MemberDto joinDto) throws Exception {
        validateDuplicateEmail(joinDto.getEmail());
        Member member = MemberAndDtoAdapter.dtoToEntity(joinDto);
        member.setPwd(passwordEncoder.encode(joinDto.getPwd()));
        member.setRole(Role.valueOf("USER"));

        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 로그인
     */
    @Transactional
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
            throw new RuntimeException();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * 회원 정보 수정
     *
     * @return
     */
    @Transactional
    public MemberDto update(Long id, MemberDto.UpdateDto updateDto) throws Exception{
        Member member = memberRepository.findById(id).get();
        member.update(updateDto);
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


}
