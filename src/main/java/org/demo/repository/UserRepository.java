package org.demo.repository;

import org.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{

	User findByUsername(String username);
	
	Page<User> findAll(Pageable page);
	
	Page<User> findByPassword(String password, Pageable page);
}
