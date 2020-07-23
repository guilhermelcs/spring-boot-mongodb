package com.guilherme.springmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.guilherme.springmongo.entities.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
}
