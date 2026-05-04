package br.eng.jonathan.ntoerp.controller;

import br.eng.jonathan.ntoerp.controller.open_api.PaymentTypeControllerOpenApi;
import br.eng.jonathan.ntoerp.dto.PaymentTypeDTO;
import br.eng.jonathan.ntoerp.dto.assembler.PaymentTypeDTOAssembler;
import br.eng.jonathan.ntoerp.service.PaymentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/payment-types", produces = "application/json")
@RequiredArgsConstructor
public class PaymentTypeController implements PaymentTypeControllerOpenApi {

    private final PaymentTypeService service;
    private final PaymentTypeDTOAssembler assembler;

    @GetMapping
    public ResponseEntity<Page<EntityModel<PaymentTypeDTO>>> listPaymentType(Pageable pageable) {
        var paymentTypes = service.listAllPaymentType(pageable)
                .map( assembler::toModel);
        return ResponseEntity.ok(paymentTypes);
    }

    @GetMapping("/{paymentTypeId}")
    public ResponseEntity<EntityModel<PaymentTypeDTO>> getPaymentTypeById(@PathVariable Long paymentTypeId) {
        var paymentType = service.findPaymentTypeById(paymentTypeId);
        return ResponseEntity.ok(assembler.toModel(paymentType));
    }

    @PostMapping
    public ResponseEntity<EntityModel<PaymentTypeDTO>> createPaymentType(@RequestBody PaymentTypeDTO paymentTypeDTO) {
        var createdPaymentType = service.createPaymentType(assembler.mapToEntity(paymentTypeDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(assembler.toModel(createdPaymentType));
    }

    @PutMapping("/{paymentTypeId}")
    public ResponseEntity<EntityModel<PaymentTypeDTO>> updatePaymentType(@PathVariable Long paymentTypeId, @RequestBody PaymentTypeDTO paymentTypeDTO) {
        var updatedPaymentType = service.updatePaymentType(paymentTypeId, paymentTypeDTO);
        return ResponseEntity.ok(assembler.toModel(updatedPaymentType));
    }

    @DeleteMapping("/{paymentTypeId}")
    public ResponseEntity<Void> deletePaymentType(@PathVariable Long paymentTypeId) {
        service.deletePaymentType(paymentTypeId);
        return ResponseEntity.noContent().build();
    }
}
