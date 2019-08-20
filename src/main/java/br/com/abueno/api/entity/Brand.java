package br.com.abueno.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.abueno.api.enums.BrandName;
import br.com.abueno.api.enums.Origin;

@Entity
@Table(name = "brand")
public class Brand implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1319856962935913170L;

	private Long id;
	private BrandName name;
	private Origin origin;

	public Brand() {

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
	@Column(name = "brand_name", nullable = false)
	public BrandName getName() {
		return name;
	}

	public void setName(BrandName name) {
		this.name = name;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "origin", nullable = false)
	public Origin getOrigin() {
		return origin;
	}

	public void setOrigin(Origin origin) {
		this.origin = origin;
	}

}
