package br.com.abueno.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.abueno.api.entity.Brand;
import br.com.abueno.api.repository.BrandRepository;
import br.com.abueno.api.services.BrandServices;

@Service
public class BrandServicesImpl implements BrandServices {

	private static final Logger log = LoggerFactory.getLogger(BrandServicesImpl.class);

	@Autowired
	private BrandRepository brandRepository;

	@Override
	public Optional<Brand> findById(Long id) {
		log.info("Search Brand by id {} ", id);
		return this.brandRepository.findById(id);
	}

	@Override
	public Optional<Brand> findByName(String name) {
		log.info("Searh Brand by name {} ", name);
		return this.brandRepository.findByName(name);
	}

	@Override
	public Optional<Brand> findByOrigin(String origin) {
		log.info("Search Brand by Origin {} ", origin);
		return this.brandRepository.findByOrigin(origin);
	}

	@Override
	public Brand saveBrand(Brand brand) {
		log.info("Insert Brand {} ", brand);
		return brandRepository.save(brand);
	}

	@Override
	public void delBrandById(Long id) {
		log.info("Delete Brand by id {} ", id);
		this.brandRepository.deleteById(id);

	}

}
