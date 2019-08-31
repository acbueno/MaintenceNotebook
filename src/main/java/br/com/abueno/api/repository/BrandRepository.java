package br.com.abueno.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.abueno.api.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {

	@Transactional(readOnly = true)
	Optional<Brand> findById(Long id);

	@Transactional(readOnly = true)
	Optional<Brand> findByName(String brandName);

	@Transactional(readOnly = true)
	Optional<Brand> findByOrigin(String origin);

}
