package com.guilherme.springmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.guilherme.springmongo.entities.Post;
import com.guilherme.springmongo.entities.User;
import com.guilherme.springmongo.repositories.PostRepository;
import com.guilherme.springmongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository repo;

	@Autowired
	private PostRepository postRepo;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		repo.deleteAll();
		postRepo.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		Post p1 = new Post(null, sdf.parse( "21/03/2018"),  "Partiu viagem",  "Vou viajar para São Paulo. Abraços!", maria);
		Post p2 = new Post(null, sdf.parse( "21/03/2018"),  "Partiu viagem",  "Vou viajar para São Paulo. Abraços!", alex);
		Post p3 = new Post(null, sdf.parse( "21/03/2018"),  "Partiu viagem",  "Vou viajar para São Paulo. Abraços!", bob);
		
		repo.saveAll(Arrays.asList(maria, alex, bob));
		postRepo.saveAll(Arrays.asList(p1, p2, p3));
	}
	
}
