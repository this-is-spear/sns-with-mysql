package com.example.fastcampusmysql.domain.follow.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.fastcampusmysql.domain.follow.dto.FollowDto;
import com.example.fastcampusmysql.domain.follow.entity.Follow;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FollowReadService {

	private final FollowRepository followRepository;

	public List<FollowDto> getFollowings(Long memberId) {
		return followRepository.findAllByFromMemberId(memberId).stream().map(this::toDto)
			.collect(Collectors.toList());
	}

	private FollowDto toDto(Follow follow) {
		return new FollowDto(follow.getToMemberId());
	}

}
