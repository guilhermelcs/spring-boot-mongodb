package com.guilherme.springmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.guilherme.springmongo.entities.Post;
import com.guilherme.springmongo.resources.util.URL;
import com.guilherme.springmongo.services.PostService;

@RestController
@RequestMapping( value = "/posts")
public class PostResource {
	
	@Autowired
	private PostService service;
	
	@GetMapping( value = "{id}" )
	public ResponseEntity<Post> findById( @PathVariable String id ) {
		Post post = service.findById(id);
		return ResponseEntity.ok().body(post);
	}
	
	@GetMapping( value = "/titlesearch" )
	public ResponseEntity<List<Post>> findByTitle( @RequestParam( value = "text" ) String text ) {
		String title = URL.decodeParam(text);
		List<Post> posts = service.findByTitle(title);
		return ResponseEntity.ok().body(posts);
	}
}
