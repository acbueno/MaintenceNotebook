package br.com.abueno.api.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "model_year")
public class ModelYear implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7210987049141467598L;

	private Long id;
	private Date year;
	private Date model_year;

	public ModelYear() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "year", nullable = false)
	public Date getYear() {
		return year;
	}

	public void setYear(Date year) {
		this.year = year;
	}

	@Column(name = "model_year", nullable = false)
	public Date getModel_year() {
		return model_year;
	}

	public void setModel_year(Date model_year) {
		this.model_year = model_year;
	}

}
