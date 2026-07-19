package br.eng.jonathan.ntoerp.controller.open_api;

import br.eng.jonathan.ntoerp.dto.PaymentDTO;
import br.eng.jonathan.ntoerp.dto.PaymentDTO;
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

@Tag(name = "Payments", description = "Manages the API payments.")
public interface PaymentControllerOpenApi {

    @Operation(summary = "List payments", description = "Retrieves all operational records within the system",
            parameters = {
                    @Parameter(name = "page", description = "Page to be loaded", schema = @Schema(type = "integer", defaultValue = PAGE_NUMBER)),
                    @Parameter(name = "size", description = "Number of records", schema = @Schema(type = "integer", defaultValue = PAGE_SIZE)),
                    @Parameter(name = "sort", description = "Record sorting. Use [fieldName],[order (asc/desc)] e.g.: paymentId,desc", schema = @Schema(type = "string"))
            }
    )
    ResponseEntity<Page<EntityModel<PaymentDTO>>> list(
            @Parameter(hidden = true) Pageable pageable
    );

    @Operation(summary = "Get payment by ID", description = "Retrieves a single payment by their ID")
    ResponseEntity<EntityModel<PaymentDTO>> getPaymentById(@PathVariable Long paymentId);

    @Operation(summary = "New payment", description = "Registers a new payment.")
    ResponseEntity<EntityModel<PaymentDTO>> createNewPayment(PaymentDTO paymentDTO, HttpServletResponse response) throws NotFoundException;

    @Operation(summary = "Updates a payment", description = "Updates a payment using their payment ID")
    ResponseEntity<EntityModel<PaymentDTO>> updatePayment(@PathVariable Long paymentId, @Valid @RequestBody PaymentDTO paymentDTO);

    @Operation(summary = "Deletes a payment", description = "Deletes a payment using their payment ID")
    ResponseEntity<Void> deletePayment(@PathVariable Long paymentId);

}
