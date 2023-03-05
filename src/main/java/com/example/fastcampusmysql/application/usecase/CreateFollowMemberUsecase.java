package com.example.fastcampusmysql.application.usecase;

import org.springframework.stereotype.Component;

import com.example.fastcampusmysql.domain.follow.service.FollowWriteService;
import com.example.fastcampusmysql.domain.member.service.MemberReadService;

import lombok.RequiredArgsConstructor;

/**
 * 유즈케이스는 가능한 로직이 없어야 한다. 비즈니스 로직은 각 도메인 서비에스에서 진행해야 한다. 해당 유즈케이스는 도메인 서비스의 흐름을 제어하는 역할만을 해야 한다.
 */
@Component
@RequiredArgsConstructor
public class CreateFollowMemberUsecase {

	private final MemberReadService memberReadService;
	private final FollowWriteService followWriteService;

	public void execute(Long fromMemberId, Long toMemberId) {
    /*
    1. 입력 받은 member id로 회원 조회
    2. call FollowWriteService.create()
     */
		var fromMember = memberReadService.getMember(fromMemberId);
		var toMember = memberReadService.getMember(toMemberId);

		followWriteService.create(fromMember, toMember);
	}
}
