package com.example.fastcampusmysql.domain.member.service;

import com.example.fastcampusmysql.domain.member.dto.RegisterMemberCommand;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberWriteService {

  private final MemberRepository memberRepository;

  public Member create(RegisterMemberCommand command) {
    /*
      TODO - 회원정보(이메일, 닉네임, 생년월일)를 등록한다.
      TODO - 닉네임은 10자를 넘길 수 없다.
      parameter - memberRegisterCommand
      val member = Member.of(memberRegisterCommand)
      memberRepository.save(member)
     */
    Member member = Member.builder()
        .nickname(command.nickname())
        .email(command.email())
        .birthday(command.birthday())
        .build();

    return memberRepository.save(member);
  }

}
