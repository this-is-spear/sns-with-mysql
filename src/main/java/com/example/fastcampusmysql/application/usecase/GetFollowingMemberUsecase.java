package com.example.fastcampusmysql.application.usecase;

import com.example.fastcampusmysql.domain.follow.dto.FollowDto;
import com.example.fastcampusmysql.domain.follow.repository.FollowReadService;
import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import com.example.fastcampusmysql.domain.member.service.MemberReadService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetFollowingMemberUsecase {

  private final FollowReadService followReadService;
  private final MemberReadService memberReadService;

  public List<MemberDto> execute(Long memberId) {
    return memberReadService.getMember(followReadService.getFollowings(memberId).stream()
        .map(FollowDto::toMemberId).collect(Collectors.toList()));
  }

}