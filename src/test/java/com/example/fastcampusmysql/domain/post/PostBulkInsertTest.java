package com.example.fastcampusmysql.domain.post;

import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.post.repository.PostRepository;
import java.time.LocalDate;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

@SpringBootTest
public class PostBulkInsertTest {

  private static final int MILLION = 1_000_000;
  @Autowired
  private PostRepository postRepository;

  @Test
  public void bulkInsert() {
    var easyRandom = PostFixtureFactory.get(
        2L,
        LocalDate.of(2021, 1, 1),
        LocalDate.of(2023, 2, 1)
    );

    var streamStopWatch = new StopWatch();
    streamStopWatch.start();

    // 1_000_000건을 병렬로 객체를 생성하지 않는 경우 소요되는 시간 : 3.147 s
    // 1_000_000건을 병렬로 객체를 생성하는 경우 소요되는 시간 : 27.976 s
    var posts = IntStream.range(0, MILLION)
        .parallel()
        .mapToObj(i -> easyRandom.nextObject(Post.class))
        .toList();

    streamStopWatch.stop();
    System.out.println("객체 생성 시간 : " + streamStopWatch.getTotalTimeMillis());

    var queryStopWatch = new StopWatch();

    queryStopWatch.start();
    postRepository.bulkInsert(posts);
    queryStopWatch.stop();

    // 1_000_000 건 벌크 인서트 시 68.372 가 소요된다...
    System.out.println("db 쿼리 시간 : " + queryStopWatch.getTotalTimeMillis());
  }
}
