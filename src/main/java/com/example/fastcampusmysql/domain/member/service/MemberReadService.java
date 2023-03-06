package com.example.fastcampusmysql.domain.member.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import com.example.fastcampusmysql.domain.member.dto.MemberNicknameHistoryDto;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.entity.MemberNicknameHistory;
import com.example.fastcampusmysql.domain.member.repository.MemberNicknameHistoryRepository;
import com.example.fastcampusmysql.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberReadService {

	private final MemberRepository memberRepository;
	private final MemberNicknameHistoryRepository memberNicknameHistoryRepository;

	public MemberDto getMember(Long id) {
		var member = memberRepository.findById(id).orElseThrow();
		return toDto(member);
	}

	public List<MemberDto> getMember(List<Long> ids) {
		var members = memberRepository.findAllByIdIn(ids);
		return members.stream().map(this::toDto).collect(Collectors.toList());
	}

	public List<MemberNicknameHistoryDto> getNicknameHistories(Long memberId) {
		return memberNicknameHistoryRepository.findAllByMemberId(memberId).stream()
			.map(this::toDto).collect(Collectors.toList());
	}

	private MemberDto toDto(Member member) {
		return new MemberDto(member.getId(), member.getEmail(), member.getNickname(),
			member.getBirthday());
	}

	private MemberNicknameHistoryDto toDto(MemberNicknameHistory history) {
		return new MemberNicknameHistoryDto(
			history.getId(), history.getMemberId(), history.getNickname(), history.getCreatedAt());
	}

}
