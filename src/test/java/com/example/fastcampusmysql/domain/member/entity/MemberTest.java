package com.example.fastcampusmysql.domain.member.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.fastcampusmysql.util.MemberFixtureFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// ObjectMother 를 찾아보자 - 테스트를 위한 객체를 생성하는 걸 도와주는 방법이다.
class MemberTest {

  @Test
  @DisplayName("회원은 닉네임을 변경할 수 있다.")
  void testChangeName() {
    Member member = MemberFixtureFactory.create();
    var expected = "pnu";

    member.changeName(expected);

    assertThat(member.getNickname()).isEqualTo(expected);
  }

  @Test
  @DisplayName("회원 닉네임은 10자를 초과할 수 없다.")
  public void testNicknameMaxLength() {
    Member member = MemberFixtureFactory.create();
    var overMaxLength = "pnupnupnupnupnupnu";

    assertThatThrownBy(() -> member.changeName(overMaxLength))
        .isInstanceOf(IllegalArgumentException.class);
  }
}
