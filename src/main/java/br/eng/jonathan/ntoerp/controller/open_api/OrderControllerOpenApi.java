package br.eng.jonathan.ntoerp.controller.open_api;

import br.eng.jonathan.ntoerp.dto.OrderDTO;
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

@Tag(name = "Order", description = "Manages API's Orders.")
public interface OrderControllerOpenApi {

    @Operation(summary = "List orders", description = "Retrieves all orders within the system",
            parameters = {
                    @Parameter(name = "page", description = "Page to be loaded", schema = @Schema(type = "integer", defaultValue = PAGE_NUMBER)),
                    @Parameter(name = "size", description = "Number of records", schema = @Schema(type = "integer", defaultValue = PAGE_SIZE)),
                    @Parameter(name = "sort", description = "Record sorting. Use [fieldName],[order (asc/desc)] e.g.: orderId,desc", schema = @Schema(type = "string"))
            }
    )
    ResponseEntity<Page<EntityModel<OrderDTO>>> list(
            @Parameter(hidden = true) Pageable pageable
    );

    @Operation(summary = "Get order by ID", description = "Retrieves a single order by its ID")
    ResponseEntity<EntityModel<OrderDTO>> getOrderById(@PathVariable Long orderId);

    @Operation(summary = "New order", description = "Registers a new order.")
    ResponseEntity<EntityModel<OrderDTO>> createNewOrder(@RequestBody @Valid OrderDTO orderDTO, HttpServletResponse response) throws NotFoundException;

    @Operation(summary = "Updates an order", description = "Updates an order using its ID")
    ResponseEntity<EntityModel<OrderDTO>> updateOrder(@PathVariable Long orderId, @Valid @RequestBody OrderDTO orderDTO);

    @Operation(summary = "Deletes an order", description = "Deletes an order using its ID")
    ResponseEntity<Void> deleteOrder(@PathVariable Long orderId);

}
