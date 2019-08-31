package br.com.abueno.api.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.abueno.api.dto.BrandDTO;
import br.com.abueno.api.entity.Brand;
import br.com.abueno.api.entity.User;
import br.com.abueno.api.repository.BrandRepository;
import br.com.abueno.api.response.Response;
import br.com.abueno.api.services.BrandServices;

@RestController
@RequestMapping("/api/brand")
@CrossOrigin(origins = "*")
public class BrandController {

	@Autowired
	BrandServices brandServices;

	private static final Logger log = LoggerFactory.getLogger(BrandRepository.class);

	@GetMapping(value = "/search/{id}")
	public ResponseEntity<Response<BrandDTO>> listBrandById(@PathVariable("id") long id) {
		log.info("Search Brand By {} ", id);
		Response<BrandDTO> response = new Response<BrandDTO>();
		Optional<Brand> brand = this.brandServices.findById(id);

		if (!brand.isPresent()) {
			log.info("Brand not found id", id);
			response.getErrors().add("Brand not find id" + id);
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(convertBrandDto(brand.get()));

		return ResponseEntity.ok(response);

	}

	private BrandDTO convertBrandDto(Brand brand) {
		BrandDTO brandDTO = new BrandDTO();

		brandDTO.setId((Optional.of(brand.getId())));
		brandDTO.setBrandName(brand.getName());
		brandDTO.setOrigin(brand.getOrigin());
		brandDTO.setI18nBrandName(brand.getI18nName());

		return brandDTO;
	}

	@GetMapping(value = "/name/{name}")
	public ResponseEntity<Response<BrandDTO>> listBrandByName(@PathVariable("name") String brandName) {

		log.info("Search Brand by BrandName", brandName);
		Response<BrandDTO> response = new Response<BrandDTO>();
		Optional<Brand> brand = this.brandServices.findByName(brandName);

		if (!brand.isPresent()) {
			log.info("Brand not found name ", brandName);
			response.getErrors().add("Brand not find name" + brandName);
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(convertBrandDto(brand.get()));

		return ResponseEntity.ok(response);
	}
	
	@PostMapping(value = "insert") 
	public ResponseEntity<Response<BrandDTO>> insertBrand(@Valid @RequestBody BrandDTO brandDTO, BindingResult result) {

		log.info("Insert new Brand", brandDTO.toString());
		Response<BrandDTO> response = new Response<BrandDTO>();
		Brand brand = this.convertDtoBrand(brandDTO, result);

		if (result.hasErrors()) {
			log.error("Error validate Brand:", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));

			return ResponseEntity.badRequest().body(response);

		}

		brand = this.brandServices.saveBrand(brand);
		response.setData(this.convertBrandDto(brand));

		return ResponseEntity.ok(response);

	}
	
	@PutMapping(value = "update/{id}")
	public ResponseEntity<Response<BrandDTO>> updateUser(@PathVariable("id") Long id,
			@Valid @RequestBody BrandDTO brandDTO, BindingResult result) {

		log.info("Update Brand: {}", brandDTO.toString());
		Response<BrandDTO> response = new Response<BrandDTO>();
		brandDTO.setId(Optional.of(id)); 
		Brand brand = this.convertDtoBrand(brandDTO, result);

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		brand = this.brandServices.saveBrand(brand);
		response.setData(this.convertBrandDto(brand));

		return ResponseEntity.ok(response);
	}
	 
	private Brand convertDtoBrand(@Valid BrandDTO brandDTO, BindingResult result) {
		
		Brand brand = new Brand();		
		
		if (brandDTO.getId() != null) {
			Optional<Brand> brandObject = this.brandServices.findById((brandDTO.getId().get()));
			if (brandObject.isPresent()) {
				brand = brandObject.get();
				
			} else {
				result.addError(new ObjectError("brand", "Brand noound"));
				
				
			}
		}

		brand.setName(brandDTO.getBrandName());
		brand.setI18nName(brandDTO.getI18nBrandName());
		brand.setOrigin(brandDTO.getOrigin());

		return brand;
	}
	
	@DeleteMapping(value = "remove/{id}")
	public ResponseEntity<Response<String>> removeBrand(@PathVariable Long id) {
		log.info("Remove Brand by: {}", id);
		Response<String> response = new Response<String>();
		Optional<Brand> brand = this.brandServices.findById(id);
		
		if(!brand.isPresent()) {
			log.error("Error remove brand by id", id);
			response.getErrors().add("Error remove brand id " + id);
			return ResponseEntity.badRequest().body(response);
		}
		
		this.brandServices.delBrandById(id);
		return ResponseEntity.ok(new Response<String>());
	}
	
}
