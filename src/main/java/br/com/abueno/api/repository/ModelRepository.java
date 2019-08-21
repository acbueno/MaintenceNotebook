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

@NamedQueries({
		@NamedQuery(name = " ModelRepository.findByBrandId", query = "SELECT mo FROM Model mo "
				+ " WHERE mo.brand.id = :brandId"),
		@NamedQuery(name = "ModelRepository.findByUserId", query = "SELECT mo FROM Model mo WHERE mo.user.id = :userId") })
public interface ModelRepository extends JpaRepository<Model, Long> {

	@Transactional(readOnly = true)
	Optional<Model> findById(Long id);

	@Transactional(readOnly = true)
	Model findByModelName(String modelName);

	Model findByBrandId(@Param("brandId") Long brandId);

	Page<Model> findByBrandId(@Param("brandId") Long brandId, Pageable pageable);

	Model findByUserId(@Param("userId") Long userId);

	Page<Model> findByUserId(@Param("userId") Long userId, Pageable pageable);

}
