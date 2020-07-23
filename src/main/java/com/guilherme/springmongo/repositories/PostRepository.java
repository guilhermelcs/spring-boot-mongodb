package com.guilherme.springmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.guilherme.springmongo.entities.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
	
}
