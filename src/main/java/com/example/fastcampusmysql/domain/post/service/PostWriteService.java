package com.example.fastcampusmysql.domain.post.service;

import org.springframework.stereotype.Service;

import com.example.fastcampusmysql.domain.post.dto.PostCommand;
import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostWriteService {

	private final PostRepository postRepository;

	public Long crete(PostCommand command) {
		var post = Post.builder()
			.memberId(command.memberId())
			.contents(command.contents())
			.build();
		return postRepository.save(post);
	}

}
