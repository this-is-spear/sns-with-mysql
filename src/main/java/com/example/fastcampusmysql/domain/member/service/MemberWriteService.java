package com.example.fastcampusmysql.domain.member.service;

import org.springframework.stereotype.Service;

import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import com.example.fastcampusmysql.domain.member.dto.RegisterMemberCommand;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.entity.MemberNicknameHistory;
import com.example.fastcampusmysql.domain.member.repository.MemberNicknameHistoryRepository;
import com.example.fastcampusmysql.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberWriteService {

	private final MemberRepository memberRepository;
	private final MemberNicknameHistoryRepository memberNicknameHistoryRepository;

	public MemberDto create(RegisterMemberCommand command) {
		var member = Member.builder()
			.nickname(command.nickname())
			.email(command.email())
			.birthday(command.birthday())
			.build();
		var savedMember = memberRepository.save(member);
		saveNicknameHistory(savedMember);
		return toDto(savedMember);
	}

	public void changeNickname(Long memberId, String nickname) {
		var member = memberRepository.findById(memberId).orElseThrow();
		member.changeName(nickname);
		memberRepository.save(member);
		saveNicknameHistory(member);
	}

	private void saveNicknameHistory(Member member) {
		var history = MemberNicknameHistory.builder()
			.memberId(member.getId()).nickname(member.getNickname()).build();
		memberNicknameHistoryRepository.save(history);
	}

	private MemberDto toDto(Member member) {
		return new MemberDto(member.getId(), member.getEmail(), member.getNickname(),
			member.getBirthday());
	}

}
