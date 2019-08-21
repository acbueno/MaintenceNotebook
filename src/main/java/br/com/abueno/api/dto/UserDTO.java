package br.com.abueno.api.dto;

import java.util.Optional;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class UserDTO {

	private Optional<Long> id;
	private String name;
	private String login;
	private String email;
	private String password;

	public UserDTO() {

	}

	public Optional<Long> getId() {
		return id;
	}

	public void setId(Optional<Long> id) {
		this.id = id; 
	}

	@NotEmpty(message = "Nome não pode ser vazio")
	@Length(min = 5, max = 200, message = "Nome deve 5 a 200 carcateres.")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotEmpty(message = "Login não pode ser vazio")
	@Length(min = 5, max = 200, message = "Login deve 5 a 200 carcateres.")
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@NotEmpty(message = "Email não pode ser vazio")
	@Length(min = 5, max = 200, message = "Email deve 5 a 200 carcateres.")
	@Email(message = "email invalido")
	public String getEmail() { 
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotEmpty(message = "Password não pode ser vazio")
	@Length(min = 5, max = 200, message = "Password deve 5 a 200 carcateres.")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
