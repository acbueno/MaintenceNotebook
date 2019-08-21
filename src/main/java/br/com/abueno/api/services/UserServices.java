package br.com.abueno.api.services;

import java.util.Optional;

import br.com.abueno.api.entity.User;

public interface UserServices {
	
   Optional<User>findUserById(Long id); 
   
   User findUserByName(String name);
   
   Optional<User> findUserByEmail(String email);
   
   Optional<User> findUserByLogin(String login);
   
   User saveUser(User user); 
   
   void delUser(Long id);

}
