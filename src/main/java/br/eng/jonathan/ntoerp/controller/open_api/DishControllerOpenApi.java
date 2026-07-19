package br.eng.jonathan.ntoerp.controller.open_api;

import br.eng.jonathan.ntoerp.dto.DishDTO;
import br.eng.jonathan.ntoerp.exception_handler.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import static br.eng.jonathan.ntoerp.utils.APIConstants.PAGE_NUMBER;
import static br.eng.jonathan.ntoerp.utils.APIConstants.PAGE_SIZE;

@Tag(name = "Dish", description = "Manages API's Dishes.")
public interface DishControllerOpenApi {

    @Operation(summary = "List dishes", description = "Retrieves all dishes within the system",
            parameters = {
                    @Parameter(name = "page", description = "Page to be loaded", schema = @Schema(type = "integer", defaultValue = PAGE_NUMBER)),
                    @Parameter(name = "size", description = "Number of records", schema = @Schema(type = "integer", defaultValue = PAGE_SIZE)),
                    @Parameter(name = "sort", description = "Record sorting. Use [fieldName],[order (asc/desc)] e.g.: dishId,desc", schema = @Schema(type = "string"))
            }
    )
    ResponseEntity<Page<EntityModel<DishDTO>>> list(
            @Parameter(hidden = true) Pageable pageable
    );

    @Operation(summary = "Get dish by ID", description = "Retrieves a single dish by its ID")
    ResponseEntity<EntityModel<DishDTO>> getDishById(@PathVariable Long dishId);

    @Operation(summary = "New dish", description = "Registers a new dish.")
    ResponseEntity<EntityModel<DishDTO>> createNewDish(@RequestBody DishDTO dishDTO, HttpServletResponse response) throws NotFoundException;

    @Operation(summary = "Updates a dish", description = "Updates a dish using its ID")
    ResponseEntity<EntityModel<DishDTO>> updateDish(@PathVariable Long dishId, @Valid @RequestBody DishDTO dishDTO);

    @Operation(summary = "Deletes a dish", description = "Deletes a dish using its ID")
    ResponseEntity<Void> deleteDish(@PathVariable Long dishId);
}
