package br.com.abueno.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "model")
public class Model implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4582958320688711051L;

	private Long id;
	private String modelName;
	private Brand brand;
	private ModelYear modelYear;

	public Model() {

	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "model_name", nullable = false)
	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_year_id")
	public ModelYear getModelYear() {
		return modelYear;
	}

	public void setModelYear(ModelYear modelYear) {
		this.modelYear = modelYear;
	}

}
