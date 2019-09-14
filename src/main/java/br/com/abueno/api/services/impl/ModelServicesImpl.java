package br.com.abueno.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.abueno.api.entity.Fuel;
import br.com.abueno.api.entity.Model;
import br.com.abueno.api.enums.FuelType;
import br.com.abueno.api.repository.ModelRepository;
import br.com.abueno.api.services.ModelServices;

@Service
public class ModelServicesImpl implements ModelServices {

	private Logger log = LoggerFactory.getLogger(ModelServicesImpl.class);

	@Autowired
	private ModelRepository modelRepository;

	@Override
	public Optional<Model> findModelById(Long id) {
		log.info("Search by ModelID");
		return this.modelRepository.findById(id);
	}

	@Override
	public Optional<Model> findModelByName(String name) {
		log.info("Search by Model Name");
		return Optional.ofNullable(this.modelRepository.findByName(name));
	}

	@Override
	public Optional<Model> findModelByVersion(String version) {
		log.info("Serch by Molde By Version");
		return this.modelRepository.findByVersion(version);
	}

	@Override
	public List<Model> findModelByBrand(String brand) {
		log.info("Search by Model Brand");
		return (List<Model>) this.modelRepository.findByBrandName(brand);
	}

	@Override
	public Page<Model> findModelByFuel(String type, PageRequest pageRequest) {
		log.info("Search by FuelType");
		return this.modelRepository.findByFuelType(type, pageRequest);
	}

	public Page<Model> findModelByBrand(String brandName, PageRequest pageRequest) {
		log.info("Search by ModelBrand");
		return modelRepository.findByBrandName(brandName, pageRequest);
	}

	@Override
	public Page<Model> findModelByName(String name, PageRequest pageRequest) {
		log.info("Search by ModelByName");
		return modelRepository.findByName(name, pageRequest);
	}

	@Override
	public Page<Model> findModelByVersion(String version, PageRequest pageRequest) {
		log.info("Search byModelByVersion");
		return modelRepository.findByVersion(version, pageRequest);
	}

	@Override
	public Page<Model> findModelByUserId(Long id, PageRequest pageRequest) {
		log.info("Search byModelByUser");
		return modelRepository.findByUserId(id, pageRequest);
	}

	@Override
	public Optional<Model> findModelByFuel(String type) {
		return Optional.of(modelRepository.findByFuelType(type));
	}

	@Override
	public Page<Model> findModelByFuelId(Long id, PageRequest pageRequest) {
		log.info("Searh Model By FuelType");
		return modelRepository.findByFuelId(id, pageRequest);
	}

	@Override
	public void deleteModel(long id) {
		log.info("Delete model");
		this.modelRepository.deleteById(id);
	}

}
