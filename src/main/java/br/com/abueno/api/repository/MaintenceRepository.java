package br.com.abueno.api.repository;



import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.abueno.api.entity.Maintence;
import br.com.abueno.api.enums.TypeMaintence;

@NamedQueries({
	@NamedQuery(name = "MaintenceRepository.findByPartId", query = "SELECT ma FROM Maintence  ma "
			+ " WHERE ma.part.id = :partId") })
public interface MaintenceRepository extends JpaRepository<Maintence, Long> {
	
	@Transactional(readOnly = true)
	Maintence findById(long id);
	
	@Transactional(readOnly = true)
	Maintence findByTypeMaintence(TypeMaintence typeMaintence);
	
	@Transactional(readOnly = true)
	Maintence findByDescription(String description);
	
	Maintence findByPartId(@Param("partId") Long partId);
	
	Page<Maintence> findByPartId(@Param("partId") Long partId,  Pageable pageable);
	
	

}
