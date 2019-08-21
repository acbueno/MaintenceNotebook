package br.com.abueno.api.repository;

import java.util.Optional;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import br.com.abueno.api.entity.Part;

@NamedQueries({
	@NamedQuery(name = "PartRepository.findByMaintenceId", query = "SELECT pa FROM Part  pa "
			+ " WHERE pa.maintence.id = :maintenceId") })
public interface PartRepository extends JpaRepository<Part, Long> {
	
	@Transactional(readOnly = true)
	Optional<Part> findById(Long id);
	
	@Transactional(readOnly = true)
	Part findByName(String partName);
	
	@Transactional(readOnly = true)
	Part findByGenuine(boolean isGenuine);
	
	Part findByMaintenceId(@Param("maintenceId") Long maintenceId);
	
	Page<Part> findByMaintenceId(@Param("maintenceId") Long maintenceId, Pageable pageable);
	
	
	
	

}
