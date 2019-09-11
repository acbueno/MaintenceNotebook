package br.com.abueno.api.entity;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "model")
public class Model implements Serializable {

	private static final long serialVersionUID = -4582958320688711051L;

	private Long id;
	private String name;
	private String version;
	private Brand brand;
	private Fuel fuel;
	private Date modelYear;
	private Date manufacturedYear;
	private User user;

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

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "version", nullable = false)
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "brand_id", referencedColumnName = "id")
	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fuel_id", referencedColumnName = "id")
	public Fuel getFuel() {
		return fuel;
	}

	public void setFuel(Fuel fuel) {
		this.fuel = fuel;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "model_year")
	@DateTimeFormat(pattern = "yyyy")
	public Date getModelYear() {
		return modelYear;
	}

	public void setModelYear(Date modelYear) {
		this.modelYear = modelYear;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "manufactured_year")
	@DateTimeFormat(pattern = "yyyy")
	public Date getManufacturedYear() {
		return manufacturedYear;
	}

	public void setManufacturedYear(Date manufacturedYear) {
		this.manufacturedYear = manufacturedYear;
	}

	@ManyToOne 
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
