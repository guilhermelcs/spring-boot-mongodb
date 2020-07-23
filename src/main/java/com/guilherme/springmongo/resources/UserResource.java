package com.guilherme.springmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guilherme.springmongo.dto.UserDTO;
import com.guilherme.springmongo.entities.Post;
import com.guilherme.springmongo.entities.User;
import com.guilherme.springmongo.services.UserService;

@RestController
@RequestMapping( value = "/users" )
public class UserResource {
	
	@Autowired
	private UserService services;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> users = services.findAll();
		List<UserDTO> usersDTO = users.stream().map( x -> new UserDTO( x ) ).collect(Collectors.toList());
		return ResponseEntity.ok().body(usersDTO);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<UserDTO> findById( @PathVariable String id ) {
		User user = services.findById(id);
		return ResponseEntity.ok().body( new UserDTO( user ) );
	}
	
	@PostMapping
	public ResponseEntity<User> insert( @RequestBody UserDTO userDTO ) {
		User user = services.fromDTO( userDTO );
		user = services.insert(user);
		return ResponseEntity.ok().body(user);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete( @PathVariable String id ) {
		services.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value="{id}")
	public ResponseEntity<User> insert( @RequestBody UserDTO userDTO, @PathVariable String id) {
		User user = services.fromDTO( userDTO );
		user.setId(id);
		services.update( user );
		return ResponseEntity.ok().body(user);
	}
	
	@GetMapping(value="/{id}/posts")
	public ResponseEntity<List<Post>> findPosts( @PathVariable String id ) {
		User user = services.findById(id);
		return ResponseEntity.ok().body( user.getPosts() );
	}
}
