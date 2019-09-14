package br.com.abueno.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "distance_driven")
public class DistanceDriven {

	private Long id;
	private Long distanceDriven;
	private String meausreType;

	public DistanceDriven() {

	}
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
 
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "distance_driven", nullable = false)
	public Long getDistanceDriven() {
		return distanceDriven;
	}
	
	public void setDistanceDriven(Long distanceDriven) {
		this.distanceDriven = distanceDriven;
	}

	@Column(name = "measure_type",  nullable = false)
	public String getMeausreType() {
		return meausreType;
	}

	public void setMeausreType(String meausreType) {
		this.meausreType = meausreType;
	} 

	@Override
	public String toString() {
		return "DistanceDriven [id=" + id + ", distanceDriven=" + distanceDriven + ", meausreType=" + meausreType + "]";
	}

}
