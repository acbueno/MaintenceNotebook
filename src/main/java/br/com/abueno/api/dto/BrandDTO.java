package br.com.abueno.api.dto;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class BrandDTO {

	private Optional<Long> id;
	private String brandName;
	private String origin;
	private String i18nBrandName;

	public BrandDTO() {

	}

	public Optional<Long> getId() {
		return id;
	}

	public void setId(Optional<Long> id) {
		this.id = id;
	}

	@NotEmpty(message = "BrandName não pode ser vazio")
	@Length(min = 2, max = 200, message = "Brand Name deve 2 a 200 carcateres.")
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	@NotEmpty(message = "Origin não pode ser vazio")
	@Length(min = 2, max = 200, message = "Nome deve 2 a 200 carcateres.")
	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}
	@NotEmpty(message = "i18BrandName não pode ser vazio")
	@Length(min = 2, max = 200, message = "Nome deve 2 a 200 carcateres.")
	public String getI18nBrandName() {
		return i18nBrandName;
	}
 
	public void setI18nBrandName(String i18nBrandName) {
		this.i18nBrandName = i18nBrandName;
	}
	
	

}
