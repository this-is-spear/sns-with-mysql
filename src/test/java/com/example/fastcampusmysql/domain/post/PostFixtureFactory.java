package com.example.fastcampusmysql.domain.post;

import static org.jeasy.random.FieldPredicates.inClass;
import static org.jeasy.random.FieldPredicates.named;
import static org.jeasy.random.FieldPredicates.ofType;

import com.example.fastcampusmysql.domain.post.entity.Post;
import java.time.LocalDate;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

public class PostFixtureFactory {

  public static EasyRandom get(Long memberId, LocalDate firstDate, LocalDate lastDate) {
    var idPredicate = named("id")
        .and(ofType(Long.class))
        .and(inClass(Post.class));

    var memberIdPredicate = named("memberId")
        .and(ofType(Long.class))
        .and(inClass(Post.class));

    var parameters = new EasyRandomParameters()
        .excludeField(idPredicate)
        .dateRange(firstDate, lastDate)
        .randomize(memberIdPredicate, () -> memberId);

    return new EasyRandom(parameters);
  }
}
