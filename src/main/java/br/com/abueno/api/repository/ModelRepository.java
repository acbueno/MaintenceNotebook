package br.com.abueno.api.repository;

import java.util.Optional;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.abueno.api.entity.Model;
import br.com.abueno.api.enums.FuelType;

@NamedQueries({ @NamedQuery(name = " ModelRepository.findByBrandName", query = "SELECT mo FROM Model mo "
		+ "JOIN Brand br on br.id=mo.brand_id " + " WHERE mo.brand.id = :brandName")})
public interface ModelRepository extends JpaRepository<Model, Long> {

	@Transactional(readOnly = true)
	Optional<Model> findById(Long id);

	@Transactional(readOnly = true)
	Model findByName(String name);

	@Transactional(readOnly = true)
	Page<Model> findByName(String name, Pageable pePageable);
	
	Page<Model> findByVersion(String version, Pageable pePageable);

	Optional<Model> findByVersion(String version); 

	Optional<Model> findByFuelType(FuelType fuelType); 
	
	

	Model findByBrandName(@Param("brandName") String brandName);

	Page<Model> findByBrandName(@Param("brandName") String brandName, Pageable pageable);
	
	Page<Model> findByUserId(Long id, Pageable pePageable);
	
	

}
