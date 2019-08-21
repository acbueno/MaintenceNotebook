package br.com.abueno.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "part")
public class Part implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3075759403541233628L;
	
	private Long id;
	private String name;
	private Double price;
	private boolean genuine;
	private Maintence maintence;
	
	public Part() {
		
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
	
	@Column(name = "price", nullable = false)
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	@Column(name = "geunine", nullable = false)
	public boolean isGenuine() {
		return genuine;
	} 

	public void setGenuine(boolean genuine) {
		this.genuine = genuine;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	public Maintence getMaintence() {
		return maintence;
	}

	public void setMaintence(Maintence maintence) {
		this.maintence = maintence;
	}
	
	
	
	

}
