package com.example.fastcampusmysql.controller;

import com.example.fastcampusmysql.domain.post.dto.DailyPostCount;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCountRequest;
import com.example.fastcampusmysql.domain.post.dto.PostCommand;
import com.example.fastcampusmysql.domain.post.service.PostReadService;
import com.example.fastcampusmysql.domain.post.service.PostWriteService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("posts")
public class PostController {

  private final PostWriteService postWriteService;
  private final PostReadService postReadService;

  @PostMapping
  public Long create(PostCommand command) {
    return postWriteService.crete(command);
  }

  @GetMapping("/daily-post-count")
  public List<DailyPostCount> getDailyPostCount(DailyPostCountRequest request) {
    return postReadService.getDailyPostCounts(request);
  }
}
