package br.com.abueno.api.dto;

import java.util.Date;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class CreateModelDTO {
	
	private Optional<Long> id = Optional.empty();
	private Optional<Long> brandId;
	private Optional<Long> fuelId;
	private Optional<Long> userId = Optional.empty();
	private Optional<Long> idDistanceDriven = Optional.empty();
	private String version;
	private String name;
	private Date modelYear;
	private Date manufacturedYear;
	
	public CreateModelDTO() {
		
	}

	public Optional<Long> getId() {
		return id;
	}

	public void setId(Optional<Long> id) {
		this.id = id;
	}

	public Optional<Long> getBrandId() {
		return brandId;
	}

	public void setBrandId(Optional<Long> brandId) {
		this.brandId = brandId;
	}

	public Optional<Long> getFuelId() {
		return fuelId;
	}

	public void setFuelId(Optional<Long> fuelId) {
		this.fuelId = fuelId;
	}

	public Optional<Long> getUserId() {
		return userId;
	}

	public void setUserId(Optional<Long> userId) {
		this.userId = userId;
	}

	public Optional<Long> getIdDistanceDriven() {
		return idDistanceDriven;
	}

	public void setIdDistanceDriven(Optional<Long> idDistanceDriven) {
		this.idDistanceDriven = idDistanceDriven;
	}
	
	@NotEmpty(message = "Nome não pode ser vazio")
	@Length(min = 2, max = 200, message = "Nome deve 5 a 200 carcateres.")
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	@NotEmpty(message = "Nome não pode ser vazio")
	@Length(min = 5, max = 200, message = "Nome deve 5 a 200 carcateres.")
	public String getName() {
		return name;
	}

	public void setName(String name) { 
		this.name = name;
	}

	public Date getModelYear() {
		return modelYear;
	}

	public void setModelYear(Date modelYear) {
		this.modelYear = modelYear;
	}

	public Date getManufacturedYear() {
		return manufacturedYear;
	}

	public void setManufacturedYear(Date manufacturedYear) {
		this.manufacturedYear = manufacturedYear;
	}
	
	

}
