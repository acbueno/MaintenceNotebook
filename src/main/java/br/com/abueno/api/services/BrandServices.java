package br.com.abueno.api.services;

import java.util.Optional;

import br.com.abueno.api.entity.Brand;

public interface BrandServices {

	Optional<Brand> findById(Long id);

	Optional<Brand> findByName(String name);

	Optional<Brand> findByOrigin(String origin);

	Brand saveBrand(Brand brand);

	void delBrandById(Long id);

}
