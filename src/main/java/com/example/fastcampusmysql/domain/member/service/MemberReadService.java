package com.example.fastcampusmysql.domain.member.service;

import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberReadService {

  private final MemberRepository memberRepository;

  public MemberDto getMember(Long id) {
    Member member = memberRepository.findById(id).orElseThrow();
    return toDto(member);
  }

  private MemberDto toDto(Member member) {
    return new MemberDto(member.getId(), member.getEmail(), member.getNickname(),
        member.getBirthday());
  }
}
