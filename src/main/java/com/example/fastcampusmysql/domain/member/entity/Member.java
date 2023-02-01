package com.example.fastcampusmysql.domain.member.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class Member {

  private final static Long NAME_MAX_LENGTH = 10L;
  private final Long id;
  private String nickname;
  private final String email;
  private final LocalDate birthday;
  private final LocalDateTime createdAt;

  @Builder
  public Member(Long id, String nickname, String email, LocalDate birthday,
      LocalDateTime createdAt) {
    validateNickname(nickname);

    this.id = id;
    this.nickname = Objects.requireNonNull(nickname);
    this.email = Objects.requireNonNull(email);
    this.birthday = Objects.requireNonNull(birthday);
    this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
  }

  private void validateNickname(String nickname) {
    Assert.isTrue(nickname.length() <= NAME_MAX_LENGTH, "최대 길이를 초과했습니다.");
  }
}
