package br.com.abueno.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.abueno.api.entity.User;
import br.com.abueno.api.repository.UserRepository;
import br.com.abueno.api.services.UserServices;

@Service
public class UserServiceImpl implements UserServices { 
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	 
	@Autowired
	UserRepository userRepository;

	
	public Optional<User> findUserById(Long id) {
		log.info("Buscando User  userId: {} ", id);
		return this.userRepository.findById(id); 
	}

	
	public User findUserByName(String name) {
		log.info("Buscando User userName: {} ", name);
		return this.userRepository.findByName(name);
	}

	
	public Optional<User> findUserByEmail(String email) {
		log.info("Buscando User email: {} ", email);
		return Optional.ofNullable(this.userRepository.findByEmail(email));
	}

	
	public Optional<User> findUserByLogin(String login) {
		log.info("Buscando user login: {} ", login);
		return Optional.ofNullable(this.userRepository.findByLogin(login));
	}

	
	public User saveUser(User user) {
		log.info("Insert User ", user);
		return this.userRepository.save(user);
	}

	
	public void delUser(Long id) {
		log.info("delete user " , id); 
		this.userRepository.deleteById(id);
	}

}
