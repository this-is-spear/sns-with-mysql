package com.example.fastcampusmysql.domain.post.repository;

import com.example.fastcampusmysql.domain.post.dto.DailyPostCount;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCountRequest;
import com.example.fastcampusmysql.domain.post.entity.Post;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepository {

  private static final String TABLE = "POST";
  private static final RowMapper<DailyPostCount> ROW_MAPPER = ((rs, rowNum) ->
      new DailyPostCount(
          rs.getLong("memberId"),
          rs.getObject("createdDate", LocalDate.class),
          rs.getLong("count")
      )
  );
  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  public List<DailyPostCount> groupByCreatedDate(DailyPostCountRequest request) {
    var sql = String.format("""
        SELECT createdDate, memberId, count(id) as count
        FROM %s
        WHERE memberId = :memberId AND createdDate BETWEEN :firstDate and :lastDate
        GROUP BY memberId, createdDate
        """, TABLE);

    var params = new BeanPropertySqlParameterSource(request);
    return namedParameterJdbcTemplate.query(sql, params, ROW_MAPPER);
  }

  public Long save(Post post) {
    if (post.getId() == null) {
      return insert(post).getId();
    }
    throw new UnsupportedOperationException("Post는 갱신을 지원하지 않습니다.");
  }

  public void bulkInsert(List<Post> posts) {
    var sql = String.format("""
        INSERT INTO %s (memberId, contents, createdDate, createdAt)
        VALUES (:memberId, :contents, :createdDate, :createdAt)
        """, TABLE);

    SqlParameterSource[] params = posts.stream()
        .map(BeanPropertySqlParameterSource::new)
        .toArray(SqlParameterSource[]::new);

    namedParameterJdbcTemplate.batchUpdate(sql, params);
  }

  private Post insert(Post post) {
    var jdbcInsert = new SimpleJdbcInsert(
        namedParameterJdbcTemplate.getJdbcTemplate())
        .withTableName(TABLE)
        .usingGeneratedKeyColumns("id");

    var params = new BeanPropertySqlParameterSource(post);

    var id = jdbcInsert.executeAndReturnKey(params).longValue();
    return Post.builder()
        .id(id)
        .memberId(post.getMemberId())
        .contents(post.getContents())
        .createdDate(post.getCreatedDate())
        .createdAt(post.getCreatedAt())
        .build();
  }
}
