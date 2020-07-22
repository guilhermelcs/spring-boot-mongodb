package com.guilherme.springmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guilherme.springmongo.dto.UserDTO;
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
}
