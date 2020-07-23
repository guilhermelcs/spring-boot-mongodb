package com.guilherme.springmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.guilherme.springmongo.dto.AuthorDTO;
import com.guilherme.springmongo.dto.CommentDTO;
import com.guilherme.springmongo.entities.Post;
import com.guilherme.springmongo.entities.User;
import com.guilherme.springmongo.repositories.PostRepository;
import com.guilherme.springmongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository serviceRepo;

	@Autowired
	private PostRepository postRepo;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		serviceRepo.deleteAll();
		postRepo.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		serviceRepo.saveAll(Arrays.asList(maria, alex, bob));
		
		Post p1 = new Post(null, sdf.parse( "21/03/2018"),  "Partiu viagem",  "Vou viajar para São Paulo. Abraços!", new AuthorDTO( maria ));
		Post p2 = new Post(null, sdf.parse( "21/03/2018"),  "Bora lá",  "Vou viajar para São Paulo. Abraços!", new AuthorDTO( alex ));
		Post p3 = new Post(null, sdf.parse( "21/03/2018"),  "Sou foda",  "Vou viajar para São Paulo. Abraços!", new AuthorDTO ( maria ));
		
		maria.getPosts().addAll( Arrays.asList( p1, p3 ) );
		alex.getPosts().addAll( Arrays.asList( p2 ) );
		
		CommentDTO comment1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2018"), new AuthorDTO( alex ) );
		CommentDTO comment2 = new CommentDTO("Aproveite!", sdf.parse("21/03/2018"), new AuthorDTO( bob ) );	
		
		p1.getComments().addAll(Arrays.asList(comment1, comment2));
		
		postRepo.saveAll(Arrays.asList(p1, p2, p3));
		serviceRepo.saveAll(Arrays.asList(maria, alex));
		
	}
}
