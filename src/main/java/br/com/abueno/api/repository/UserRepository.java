package br.com.abueno.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.abueno.api.entity.User;


public interface UserRepository extends JpaRepository<User,Long > { 
	
	@Transactional(readOnly = true)
	Optional<User> findById(Long id);
	
	@Transactional(readOnly = true)
	User findByName(String name);
	
	@Transactional(readOnly = true)
	User findByLogin(String login);
	
	@Transactional(readOnly = true)
	User findByEmail(String email);
	



}
