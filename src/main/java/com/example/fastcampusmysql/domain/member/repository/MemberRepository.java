package com.example.fastcampusmysql.domain.member.repository;

import com.example.fastcampusmysql.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  public Member save(Member member) {
    /*
      member id를 보고 갱신 또는 삽입을 정합
      반환값은 id가 있는 member
     */
    if (member.getId() == null) {
      return insert(member);
    }
    return update(member);
  }

  private Member insert(Member member) {
    // SimpleJdbcInsert를 사용하지 않는다면 키 홀더를 가져와 직접 키를 전달해줘야 하는데, SimpleJdbcInsert가 역할을 해준다.
    // namedParameterJdbcTemplate은 JDBC 템플릿을 composite 했기 때문에 내부에 선언된 JDBC 템플릿을 꺼내야 한다.
    SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(
        namedParameterJdbcTemplate.getJdbcTemplate())
        .withTableName("Member")
        .usingGeneratedKeyColumns("id");

    // bean으로 sql parameter source를 만들어준다.
    SqlParameterSource params = new BeanPropertySqlParameterSource(member);
    var id = simpleJdbcInsert.executeAndReturnKey(params).longValue();
    return Member.builder()
        .id(id)
        .email(member.getEmail())
        .nickname(member.getNickname())
        .birthday(member.getBirthday())
        .createdAt(member.getCreatedAt())
        .build();
  }

  private Member update(Member member) {
    // TODO : implemented
    return member;
  }
}
