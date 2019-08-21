package br.com.abueno.api.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.abueno.api.enums.TypeMaintence;

@Entity
@Table(name = "maintence")
public class Maintence implements Serializable {

	private static final long serialVersionUID = -833453560446290208L;

	private Long id;
	private TypeMaintence typeMaintence;
	private String description;
	private Date dateMaintence;
	private Double priceMaintence;
	private List<Part> part;
	
	public Maintence() {
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type_maintence", nullable = false)
	public TypeMaintence getTypeMaintence() {
		return typeMaintence;
	}

	public void setTypeMaintence(TypeMaintence typeMaintence) {
		this.typeMaintence = typeMaintence;
	}
	
	@Column(name = "description", nullable = false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String descricao) {
		this.description = descricao;
	}
	
	@Column(name = "date_maintence", nullable = false)
	public Date getDateMaintence() {
		return dateMaintence;
	}
	
	public void setDateMaintence(Date dataMaintence) {
		this.dateMaintence = dataMaintence;
	}
	@Column(name = "price_maintence", nullable = false)
	public Double getPriceMaintence() {
		return priceMaintence;
	}

	public void setPriceMaintence(Double priceMaintence) {
		this.priceMaintence = priceMaintence;
	}

	@OneToMany(mappedBy = "maintence", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Part> getPart() {
		return part;
	}

	public void setPart(List<Part> part) {
		this.part = part;
	}
	
	
	
	

}
