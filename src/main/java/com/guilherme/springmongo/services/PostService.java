package com.guilherme.springmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.springmongo.entities.Post;
import com.guilherme.springmongo.repositories.PostRepository;
import com.guilherme.springmongo.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repo;
	
	public Post findById( String id ) {
		Optional<Post> post = repo.findById(id);
		return post.orElseThrow( () -> new ObjectNotFoundException("Post não encontrado") );
	}
	
	public List<Post> findByTitle( String text ) {
		return repo.findByTitleContainingIgnoreCase(text);
	}
}
