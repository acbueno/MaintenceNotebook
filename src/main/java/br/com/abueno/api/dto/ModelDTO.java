package br.com.abueno.api.dto;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class ModelDTO {

	private Optional<Long> id = Optional.empty();
	private String name;
	private String version;
	private String brand;
	private String fuelType;
	private String yearModel;
	private String yearManufacture;
	private Optional<Long> userId = Optional.empty();

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
	
	@NotEmpty
	@Length(min = 4, max = 4, message = "Digite data no padrao YYYY")
	public String getYearModel() {
		return yearModel;
	}

	public void setYearModel(String yearModel) {
		this.yearModel = yearModel;
	}

	public String getYearManufacture() {
		return yearManufacture;
	}

	public void setYearManufacture(String yearManufacture) {
		this.yearManufacture = yearManufacture;
	}
	
	

	public Optional<Long> getUserId() {
		return userId;
	}

	public void setUserId(Optional<Long> userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "ModelDTO [id=" + id + ", name=" + name + ", version=" + version + ", brand=" + brand + ", fuelType="
				+ fuelType + ", yearModel=" + yearModel + ", yearKanufacture=" + yearManufacture + "]";
	}
	
	

}
