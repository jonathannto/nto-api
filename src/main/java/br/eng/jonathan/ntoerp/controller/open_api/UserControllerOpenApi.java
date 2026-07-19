package br.eng.jonathan.ntoerp.controller.open_api;

import br.eng.jonathan.ntoerp.dto.UserDTO;
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

@Tag(name = "Users", description = "Manages the API users.")
public interface UserControllerOpenApi {

    @Operation(summary = "List users", description = "Retrieves all operational records within the system",
            parameters = {
                    @Parameter(name = "page", description = "Page to be loaded", schema = @Schema(type = "integer", defaultValue = PAGE_NUMBER)),
                    @Parameter(name = "size", description = "Number of records", schema = @Schema(type = "integer", defaultValue = PAGE_SIZE)),
                    @Parameter(name = "sort", description = "Record sorting. Use [fieldName],[order (asc/desc)] e.g.: userId,desc", schema = @Schema(type = "string"))
            }
    )
    ResponseEntity<Page<EntityModel<UserDTO>>> list(
            @Parameter(hidden = true) Pageable pageable
    );

    @Operation(summary = "Get user by ID", description = "Retrieves a single user by their ID")
    ResponseEntity<EntityModel<UserDTO>> getUserById(@PathVariable Long userId);

    @Operation(summary = "New user", description = "Registers a new user.")
    ResponseEntity<EntityModel<UserDTO>> createNewUser(@RequestBody UserDTO userDTO, HttpServletResponse response) throws NotFoundException;

    @Operation(summary = "Updates a user", description = "Updates a user using their user ID")
    ResponseEntity<EntityModel<UserDTO>> updateUser(@PathVariable Long idUser, @Valid @RequestBody UserDTO userDTO);

    @Operation(summary = "Deletes a user", description = "Deletes a user using their user ID")
    ResponseEntity<Void> deleteUser(@PathVariable Long userId);

}
