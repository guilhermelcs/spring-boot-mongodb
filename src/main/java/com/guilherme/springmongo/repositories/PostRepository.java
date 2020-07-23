package com.guilherme.springmongo.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.guilherme.springmongo.entities.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
	
	@Query( "{ 'title': { $regex: ?0, $options: 'i' } }" )
	List<Post> searchTitle( String text );
	
	List<Post> findByTitleContainingIgnoreCase( String title );
	
	//@Query( "{ $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] }" )
	@Query("{ $or: [ { 'title': {$regex: ?0, $options: 'i'} }, { 'body': {$regex: ?0, $options: 'i'} }, { 'comments.text': {$regex: ?0, $options: 'i'} } ] }")
	List<Post> searchText ( String text );
}
