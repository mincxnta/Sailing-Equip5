package cat.institutmarianao.sailing.ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.institutmarianao.sailing.ws.model.TripType;
import cat.institutmarianao.sailing.ws.model.TripType.Category;
import cat.institutmarianao.sailing.ws.model.dto.TripTypeDto;
import cat.institutmarianao.sailing.ws.service.TripTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;

@Tag(name = "Trip Type", description = "Trip type API")

@RestController
@RequestMapping("/triptypes")
public class TripTypeController {

	@Autowired
	private ConversionService conversionService;

	@Autowired
	private TripTypeService tripTypeService;

	/* Swagger */
	@Operation(summary = "Find all trip types")
	@ApiResponse(responseCode = "200", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TripType.class))) }, description = "Shipments retrieved ok")
	/**/
	@GetMapping("/find/all")
	public Page<TripTypeDto> findAllTripTypes(Pageable pagination) {
		return tripTypeService.findAll(pagination).map(tripType -> conversionService.convert(tripType, TripTypeDto.class));
	}

	/* Swagger */
	@Operation(summary = "Find all trip types by category")
	@ApiResponse(responseCode = "200", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TripType.class))) }, description = "Shipments retrieved ok")
	/**/
	@GetMapping("/find/all/{category}")
	public Page<TripTypeDto> findAllTripTypesByCategory(@PathVariable("category") Category category,
			Pageable pagination) {
		return tripTypeService.findAllTripTypesByCategory(category, pagination).map(tripType -> conversionService.convert(tripType, TripTypeDto.class));
	}

	/* Swagger */
	@Operation(summary = "Find all group trip types")
	@ApiResponse(responseCode = "200", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TripType.class))) }, description = "Shipments retrieved ok")
	/**/
	@GetMapping("/find/all/group")
	public Page<TripTypeDto> findAllGroupTripTypes(Pageable pagination) {
		return tripTypeService.findAllTripTypesByCategory(Category.GROUP, pagination).map(tripType -> conversionService.convert(tripType, TripTypeDto.class));
	}

	/* Swagger */
	@Operation(summary = "Find all particular trip types")
	@ApiResponse(responseCode = "200", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TripType.class))) }, description = "Shipments retrieved ok")
	/**/
	@GetMapping("/find/all/private")
	public Page<TripTypeDto> findAllPrivateTripTypes(Pageable pagination) {
		return tripTypeService.findAllTripTypesByCategory(Category.PRIVATE, pagination).map(tripType -> conversionService.convert(tripType, TripTypeDto.class));
	}

	/* Swagger */
	@Operation(summary = "Get trip type by id")
	@ApiResponse(responseCode = "200", description = "User retrieved ok")
	@ApiResponse(responseCode = "404", description = "Resource not found")

	@GetMapping("/get/by/id/{id}")
	public TripTypeDto findById(@PathVariable("id") @NotNull Long id) {
		return conversionService.convert(tripTypeService.findById(id), TripTypeDto.class);
	}
}
