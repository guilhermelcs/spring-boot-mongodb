package com.guilherme.springmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.springmongo.dto.UserDTO;
import com.guilherme.springmongo.entities.User;
import com.guilherme.springmongo.repositories.UserRepository;
import com.guilherme.springmongo.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public List<User> findAll() {
		return repo.findAll();
	}
	
	public User findById( String id ) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("O Objeto não foi encontrado") );
	}
	
	public User insert( User user ) {
		return repo.insert( user );
	}
	
	public void delete( String id ) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User update( User obj ) {
		User newObj = findById(obj.getId());
		updateObj( newObj, obj );
		return repo.save(newObj);
	}
	
	private void updateObj(User newObj, User obj) {
		newObj.setName( obj.getName() );
		newObj.setEmail( obj.getEmail() );
		
	}

	public User fromDTO( UserDTO obj ) {
		return new User(obj.getId(), obj.getName(), obj.getEmail());
	}
}
