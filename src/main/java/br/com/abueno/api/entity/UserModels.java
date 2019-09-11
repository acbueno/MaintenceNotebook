//package br.com.abueno.api.entity;
//
//import java.io.Serializable;
//import java.util.Date;
//import java.util.List;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToMany;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "user_models")
//public class UserModels implements Serializable {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = -4582958320688711051L;
//
//	private Long id;
//	private List<Model> model;
//	private Date year;
//	private Date modelYear;
//	private User user;
//   
//
//	public UserModels() { 
//
//	}
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//	
//	
//	@ManyToMany
//	public List<Model> getModel() {
//		return model;
//	}
//
//	public void setModel(List<Model> model) {
//		this.model = model;
//	}
//	@Column(name = "year", nullable = false)
//	public Date getYear() {
//		return year;
//	}
//
//	public void setYear(Date year) {
//		this.year = year;
//	}
//
//	@Column(name = "model_year", nullable = false)
//	public Date getModelYear() {
//		return modelYear;
//	}
//
//	public void setModelYear(Date modelYear) {
//		this.modelYear = modelYear;
//	}
//	
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name="user_id")
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//
//}
