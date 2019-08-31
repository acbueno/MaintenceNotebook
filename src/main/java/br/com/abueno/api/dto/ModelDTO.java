package br.com.abueno.api.dto;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.abueno.api.entity.Brand;
import br.com.abueno.api.enums.FuelType;

public class ModelDTO {

	private Optional<Long> id = Optional.empty();
	private String name;
	private String version;
	private String brand;
	private String fuelType;

	public ModelDTO() {

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

	@NotEmpty(message = "Nome não pode ser vazio")
	@Length(min = 5, max = 200, message = "Nome deve 5 a 200 carcateres.")
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

}
