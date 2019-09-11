package br.com.abueno.api.controller;

import java.text.SimpleDateFormat;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.abueno.api.dto.ModelDTO;
import br.com.abueno.api.entity.Model;
import br.com.abueno.api.response.Response;
import br.com.abueno.api.services.ModelServices;

@RestController
@RequestMapping("/api/model")
@CrossOrigin(origins = "*")
public class ModelController {

	@Autowired
	private ModelServices modelServices;

	@Value("${paginacao.qtd_por_pagina}")
	private int qtdPorPagina;

	private static final Logger log = LoggerFactory.getLogger(ModelController.class);
	
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
	
	 public ModelController() {
		
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<ModelDTO>> listById(@PathVariable("id") Long id) {
		log.info("Search Model by ID");
		Response<ModelDTO> response = new Response<ModelDTO>();
		Optional<Model> model = this.modelServices.findModelById(id);

		if (!model.isPresent()) {
			log.info("Search not find ID", id);
			response.getErrors().add("User not find ID " + id);
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(this.convertModelDTO(model.get()));
		return ResponseEntity.ok(response);

	}

	
	@GetMapping(value = "name/{name}")
	public ResponseEntity<Response<Page<ModelDTO>>> listModelByName(@PathVariable("name") String name,
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {

		log.info("Search Model by ");
		Response<Page<ModelDTO>> response = new Response<Page<ModelDTO>>();
		PageRequest pageRequest = new PageRequest(pag, this.qtdPorPagina, Direction.valueOf(dir), ord);
		Page<Model> models = this.modelServices.findModelByName(name, pageRequest);
		Page<ModelDTO> modlesDto = models.map(model -> this.convertModelDTO(model));

		response.setData(modlesDto);

		return ResponseEntity.ok(response);
	}
	
	

	@GetMapping(value = "version/{version}") 
	public ResponseEntity<Response<Page<ModelDTO>>> listByModelVersion(@PathVariable("version") String versionName,
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {
		
		log.info("Search Model by Version");
		Response<Page<ModelDTO>> response = new Response<Page<ModelDTO>>();
		PageRequest pageRequest = new PageRequest(pag, this.qtdPorPagina, Direction.valueOf(dir), ord);
		Page<Model> models = this.modelServices.findModelByName(versionName, pageRequest);
		Page<ModelDTO> modelsDto = models.map(model -> this.convertModelDTO(model));

		response.setData(modelsDto);

		return ResponseEntity.ok(response); 
	}
	

	@GetMapping(value = "brand/{name}")
	public ResponseEntity<Response<Page<ModelDTO>>> listByModelByBrand(
			@PathVariable("name") String brandName,
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {
		log.info("Search Model by Brand");
		Response<Page<ModelDTO>> response = new Response<Page<ModelDTO>>();
		PageRequest pageRequest = new PageRequest(pag, this.qtdPorPagina, Direction.valueOf(dir), ord);
		Page<Model> models = this.modelServices.findModelByBrand(brandName, pageRequest);
		Page<ModelDTO> modelsDto =  models.map(model -> this.convertModelDTO(model));
		
		response.setData(modelsDto);
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping(value = "user/{userId}")
	public ResponseEntity<Response<Page<ModelDTO>>> listByUser(@PathVariable("userId") Long idUser,
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {
		log.info("Search Mobel by user");
		Response<Page<ModelDTO>> response = new Response<Page<ModelDTO>>();
		PageRequest pageRequest = new PageRequest(pag, this.qtdPorPagina, Direction.valueOf(dir), ord);
		Page<Model> models = this.modelServices.findModelByUserId(idUser, pageRequest);
		if (models == null) {
			log.info("user Id not found", idUser);
			response.getErrors().add("idUSer not found" + idUser);

			return ResponseEntity.badRequest().body(response);
		}

		Page<ModelDTO> modelsDto = models.map(model -> this.convertModelDTO(model));
		response.setData(modelsDto);
		return ResponseEntity.ok(response);
	}
	
	
	private ModelDTO convertModelDTO(Model model) {
		ModelDTO modelDTO = new ModelDTO();
		modelDTO.setId(Optional.of(model.getId()));
		modelDTO.setName(model.getName());
		modelDTO.setFuelType(model.getFuel().getType());
		modelDTO.setVersion(model.getVersion());
		modelDTO.setBrand(model.getBrand().getName());
		modelDTO.setYearModel(this.dateFormat.format(model.getModelYear()));
		modelDTO.setYearManufacture(this.dateFormat.format(model.getManufacturedYear()));
		modelDTO.setUserId(Optional.of(model.getUser().getId()));

		return modelDTO;
	}

}
