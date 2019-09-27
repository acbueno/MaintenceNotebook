package br.com.abueno.api.controller;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.abueno.api.dto.CreateModelDTO;
import br.com.abueno.api.dto.ModelDTO;
import br.com.abueno.api.entity.Brand;
import br.com.abueno.api.entity.DistanceDriven;
import br.com.abueno.api.entity.Fuel;
import br.com.abueno.api.entity.Model;
import br.com.abueno.api.entity.User;
import br.com.abueno.api.response.Response;
import br.com.abueno.api.services.BrandServices;
import br.com.abueno.api.services.ModelServices;
import br.com.abueno.api.services.UserServices;

@RestController
@RequestMapping("/api/model")
@CrossOrigin(origins = "*")
public class ModelController {

	@Autowired
	private ModelServices modelServices;
	
	@Autowired
	private BrandServices brandservices;
	
	@Autowired
	private UserServices userservices;
	

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
	public ResponseEntity<Response<Page<ModelDTO>>> listByModelByBrand(@PathVariable("name") String brandName,
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {
		log.info("Search Model by Brand");
		Response<Page<ModelDTO>> response = new Response<Page<ModelDTO>>();
		PageRequest pageRequest = new PageRequest(pag, this.qtdPorPagina, Direction.valueOf(dir), ord);
		Page<Model> models = this.modelServices.findModelByBrand(brandName, pageRequest);
		Page<ModelDTO> modelsDto = models.map(model -> this.convertModelDTO(model));

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

	@GetMapping(value = "fuel/type/{fuelType}")
	public ResponseEntity<Response<Page<ModelDTO>>> listByFuel(@PathVariable("fuelType") String fuelType,
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {
		log.info("Search Model by Fuel");

		Response<Page<ModelDTO>> response = new Response<Page<ModelDTO>>();
		PageRequest pageRequest = new PageRequest(pag, this.qtdPorPagina, Direction.valueOf(dir), ord);
		Page<Model> models = this.modelServices.findModelByFuel(fuelType, pageRequest);

		if (models == null) {
			log.info("user id not found", fuelType);

			return ResponseEntity.badRequest().body(response);

		}

		Page<ModelDTO> modelDto = models.map(model -> this.convertModelDTO(model));
		response.setData(modelDto);

		return ResponseEntity.ok(response);

	}

	@GetMapping(value = "fuel/id/{fuelId}")
	public ResponseEntity<Response<Page<ModelDTO>>> listByFuelId(@PathVariable("fuelId") Long id,
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {
		log.info("Search By Fuel");

		Response<Page<ModelDTO>> response = new Response<Page<ModelDTO>>();
		PageRequest pageRequest = new PageRequest(pag, this.qtdPorPagina);
		Page<Model> models = this.modelServices.findModelByFuelId(id, pageRequest);

		if (models == null) {
			log.info("User id not found", id);
			return ResponseEntity.badRequest().body(response);
		}

		Page<ModelDTO> modelDto = models.map(model -> this.convertModelDTO(model));

		response.setData(modelDto);

		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "delete/{id}")
	public ResponseEntity<Response<String>> removeModel(@PathVariable("id") Long id) {
		log.info("Remove Model {} ", id);
		Response<String> response = new Response<String>();
		Optional<Model> model = this.modelServices.findModelById(id);

		if (!model.isPresent()) {
			log.info("Error remove Model ID {}", id);
			response.getErrors().add("Erro remove model. Regiter not found" + id);

			return ResponseEntity.badRequest().body(response);
		}

		this.modelServices.deleteModel(id);
		return ResponseEntity.ok(new Response<String>());
	}


	@PostMapping
	public ResponseEntity<Response<ModelDTO>> addModel(@Valid @RequestBody CreateModelDTO createModelDTO,
			BindingResult result) throws NoSuchAlgorithmException, ParseException {
		log.info("Adicionar Model: {}", createModelDTO.toString());
		Response<ModelDTO> response = new Response<ModelDTO>();
		validateModel(createModelDTO, result);
		Model model = this.convertDtoToModel(createModelDTO, result);

		if (result.hasErrors()) {
			log.error("Erro adicionar o model {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		model = this.modelServices.insert(model);
		response.setData(this.convertModelDTO(model));

		return ResponseEntity.ok(response);

	}

	private Model convertDtoToModel(@Valid CreateModelDTO createModelDTO, BindingResult result) throws ParseException {

		Model model = new Model();

		if (createModelDTO.getId().isPresent()) {
			Optional<Model> mod = this.modelServices.findModelById(createModelDTO.getId().get());
			if (mod.isPresent()) {
				model = mod.get();
			} else {
				result.addError(new ObjectError("Model", " Model not found"));
			}
		} else {
			model.setBrand(new Brand());
			model.getBrand().setId(createModelDTO.getBrandId().get());
			Optional<Brand> brand = this.brandservices.findById(model.getId());
			model.getBrand().setName(brand.get().getName());
			model.getBrand().setOrigin(brand.get().getOrigin());
			model.getBrand().setI18nName(brand.get().getI18nName());
			model.setFuel(new Fuel());
			model.getFuel().setId(createModelDTO.getFuelId().get());
			model.setUser(new User());
			model.getUser().setId(createModelDTO.getUserId().get());
			model.setManufacturedYear(new Date());
			model.getManufacturedYear().setYear(createModelDTO.getManufacturedYear().getYear());
			model.setModelYear(new Date());
			model.getModelYear().setYear(createModelDTO.getModelYear().getYear());
			model.setDistanceDriven(new DistanceDriven());
			model.getDistanceDriven().setId(createModelDTO.getIdDistanceDriven().get());
			model.setName(createModelDTO.getName());
			model.setVersion(createModelDTO.getVersion());

		}

		return model;
	}

	private void validateModel(@Valid CreateModelDTO createModelDto, BindingResult result) {
		if (!createModelDto.getBrandId().isPresent()) {
			result.addError(new ObjectError("Brand", "Brand not information"));
			return;
		}
		
	    Optional<Brand> brand = this.brandservices.findById(createModelDto.getBrandId().get());
	    
	 
		if (!createModelDto.getFuelId().isPresent()) {
			result.addError(new ObjectError("Version", "Version not information"));
			return;
		}
		

	}
	
	/*
	 * private void validarFuncionario(@Valid LancamentoDto lancamentoDto,
	 * BindingResult result) { if (lancamentoDto.getFuncionarioId() == null) {
	 * result.addError(new ObjectError("funcionario", "Funcionario nao informado"));
	 * return; }
	 * 
	 * log.info("Validando funcionario id {}", lancamentoDto.getFuncionarioId());
	 * Optional<Funcionario> funcionario =
	 * this.funcionarioServices.buscaPorId(lancamentoDto.getFuncionarioId()); if
	 * (!funcionario.isPresent()) { result.addError(new ObjectError("funcionario",
	 * "Funcionario nao encontrado. ID inexistente.")); }
	 * 
	 * }
	 */



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
		if (model.getDistanceDriven() != null) {
			modelDTO.setDistance_driven(Optional.ofNullable(model.getDistanceDriven().getDistanceDriven()));
			modelDTO.setMeausreType(Optional.ofNullable(model.getDistanceDriven().getMeausreType()));
		} else {
			long driven = 0;
			String measureType = "KM";
			modelDTO.setDistance_driven(Optional.of(driven));
			modelDTO.setMeausreType(Optional.of(measureType));
		}

		return modelDTO;
	}

}
