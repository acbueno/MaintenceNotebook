package br.com.abueno.api.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.abueno.api.dto.UserDTO;
import br.com.abueno.api.entity.User;
import br.com.abueno.api.response.Response;
import br.com.abueno.api.services.UserServices;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	UserServices userServices;

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<UserDTO>> listById(@PathVariable("id") Long id) {
		log.info("Search user by ID: {}", id);
		Response<UserDTO> response = new Response<UserDTO>();
		Optional<User> user = this.userServices.findUserById(id);

		if (!user.isPresent()) {
			log.info("User not find ID ", id);
			response.getErrors().add("User not find ID " + id);
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(this.convertUserDto(user.get()));
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/email/{email}")
	public ResponseEntity<Response<UserDTO>> listByEmail(@PathVariable("email") String email) {
		log.info("Search user by Email: {} ", email);
		Response<UserDTO> response = new Response<UserDTO>();
		Optional<User> user = this.userServices.findUserByEmail(email);

		if (!user.isPresent()) {
			log.info("User nod find Email ", email);
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(this.convertUserDto(user.get()));
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/login/{login}")
	public ResponseEntity<Response<UserDTO>> listByLogin(@PathVariable("login") String login) {

		log.info("Search user by Login");
		Response<UserDTO> response = new Response<UserDTO>();
		Optional<User> user = this.userServices.findUserByLogin(login);

		if (!user.isPresent()) {
			log.info("User not find login", login);
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(this.convertUserDto(user.get()));
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<UserDTO>> insertUser(@Valid @RequestBody UserDTO userDTO, BindingResult result) {

		log.info("Insert User: {}", userDTO.toString());
		Response<UserDTO> response = new Response<UserDTO>();
		User user = this.convertDtoToUser(userDTO, result);

		if (result.hasErrors()) {
			log.error("Error validate user: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));

			return ResponseEntity.badRequest().body(response);
		}

		user = this.userServices.saveUser(user);
		response.setData(this.convertUserDto(user));

		return ResponseEntity.ok(response);
	}

	@PutMapping(value = "update/{id}") 
	public ResponseEntity<Response<UserDTO>> updateUser(@PathVariable("id") Long id,
			@Valid @RequestBody UserDTO userDTO, BindingResult result) {

		log.info("Update User: {}", userDTO.toString());
		Response<UserDTO> response = new Response<UserDTO>();
		userDTO.setId(Optional.of(id));
		User user = this.convertDtoToUser(userDTO, result);

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		user = this.userServices.saveUser(user);
		response.setData(this.convertUserDto(user));

		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "remove/{id}")
	public ResponseEntity<Response<String>> remover(@PathVariable Long id) {
		log.info("Remove User : {}", id);
		Response<String> response = new Response<String>();
		Optional<User> user = this.userServices.findUserById(id);

		if (!user.isPresent()) {
			log.error("Error remove user by id ", id);
			response.getErrors().add("Error remve user id " + id);
			return ResponseEntity.badRequest().body(response);
		}

		this.userServices.delUser(id);
		return ResponseEntity.ok(new Response<String>());

	}

	private User convertDtoToUser(@Valid UserDTO userDTO, BindingResult result) {
		User user = new User();

		if (userDTO.getId() != null) {
			Optional<User> us = this.userServices.findUserById(userDTO.getId().get());
			if (us.isPresent()) {
				user = us.get();
			} else {
				result.addError(new ObjectError("user", "User not found"));
			}

		} 
		user.setName(userDTO.getName());
		user.setLogin(userDTO.getLogin());
		user.setPassword(userDTO.getPassword());
		user.setEmail(userDTO.getEmail());
		return user;
	}

	private UserDTO convertUserDto(User user) {

		UserDTO userDTO = new UserDTO();
		userDTO.setId(Optional.of(user.getId()));
		userDTO.setName(user.getName());
		userDTO.setLogin(user.getLogin());
		userDTO.setPassword(user.getPassword());
		userDTO.setEmail(user.getEmail());

		return userDTO;
	}

}
