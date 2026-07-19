package br.eng.jonathan.ntoerp.controller.open_api;

import br.eng.jonathan.ntoerp.dto.PaymentTypeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

import static br.eng.jonathan.ntoerp.utils.APIConstants.PAGE_NUMBER;
import static br.eng.jonathan.ntoerp.utils.APIConstants.PAGE_SIZE;

@Tag(name = "Payment Type", description = "Manage payment types in the system.")
public interface PaymentTypeControllerOpenApi {

    @Operation(summary = "List payment types", description = "Retrieve all payment types.",
            parameters = {
                    @Parameter(name = "page", description = "Page to be loaded", schema = @Schema(type = "integer", defaultValue = PAGE_NUMBER)),
                    @Parameter(name = "size", description = "Number of records", schema = @Schema(type = "integer", defaultValue = PAGE_SIZE)),
                    @Parameter(name = "sort", description = "Record sorting. Use [fieldName],[order (asc/desc)] e.g.: paymentTypeId,desc", schema = @Schema(type = "string"))
            }
    )
    ResponseEntity<Page<EntityModel<PaymentTypeDTO>>> listPaymentType(
            @Parameter(hidden = true) Pageable pageable
    );

    @Operation(summary = "Get payment type by ID", description = "Retrieve a payment type by its ID.")
    ResponseEntity<EntityModel<PaymentTypeDTO>> getPaymentTypeById(Long paymentTypeId);

    @Operation(summary = "Create a new payment type", description = "Add a new payment type to the system.")
    ResponseEntity<EntityModel<PaymentTypeDTO>> createPaymentType(PaymentTypeDTO paymentTypeDTO);

    @Operation(summary = "Update a payment type", description = "Update an existing payment type by its ID.")
    ResponseEntity<EntityModel<PaymentTypeDTO>> updatePaymentType(Long paymentTypeId, PaymentTypeDTO paymentTypeDTO);

    @Operation(summary = "Delete a payment type", description = "Remove a payment type by its ID.")
    ResponseEntity<Void> deletePaymentType(Long paymentTypeId);
}
