package br.com.abueno.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.abueno.api.entity.Model;
import br.com.abueno.api.enums.FuelType;


public interface ModelServices {
	
	 Optional<Model>findModelById(Long id);
	 
	 Optional<Model> findModelByName(String name);
	 
	 Optional<Model> findModelByVersion(String version);
	 
	 List<Model> findModelByBrand(String brand);  
	 
	 Page<Model> findModelByBrand(String brandModel, PageRequest pageRequest);
	 
	 Page<Model> findModelByName(String name, PageRequest pageRequest); 
	 
	 Page<Model> findModelByVersion(String version, PageRequest pageRequest); 
	 
	 Optional<Model> findModelByFuelType(FuelType fuelType);
	 
	
}
