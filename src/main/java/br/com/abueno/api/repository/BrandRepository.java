package br.com.abueno.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.abueno.api.entity.Brand;
import br.com.abueno.api.enums.BrandName;
import br.com.abueno.api.enums.Origin;

public interface BrandRepository extends JpaRepository<Brand, Long> {
	
	@Transactional(readOnly = true)
	Optional<Brand> findById(Long id);
	
	@Transactional(readOnly = true)
	Brand findByName(BrandName brandName);
	
	@Transactional(readOnly = true)
	Brand findByOrigin(Origin origin);
	
	
}
