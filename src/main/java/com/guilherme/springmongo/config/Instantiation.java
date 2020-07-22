package com.guilherme.springmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.guilherme.springmongo.entities.User;
import com.guilherme.springmongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository repo;
	
	@Override
	public void run(String... args) throws Exception {
		
		repo.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		repo.saveAll(Arrays.asList(maria, alex, bob));
	}
	
}
