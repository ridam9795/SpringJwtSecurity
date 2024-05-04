package com.spring.main.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.spring.main.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	public Optional<User> findByEmail(String email);
}
