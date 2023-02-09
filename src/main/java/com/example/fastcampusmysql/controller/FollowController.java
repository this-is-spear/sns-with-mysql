package com.example.fastcampusmysql.controller;

import com.example.fastcampusmysql.application.usecase.CreateFollowMemberUsecase;
import com.example.fastcampusmysql.application.usecase.GetFollowingMemberUsecase;
import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("follow")
public class FollowController {

  private final CreateFollowMemberUsecase createFollowMemberUsecase;
  private final GetFollowingMemberUsecase getFollowingMemberUsecase;

  @PostMapping("/{fromId}/{toId}")
  public void create(@PathVariable Long fromId, @PathVariable Long toId) {
    createFollowMemberUsecase.execute(fromId, toId);
  }

  @GetMapping("/members/{fromId}")
  public List<MemberDto> get(@PathVariable Long fromId) {
    return getFollowingMemberUsecase.execute(fromId);
  }

}
