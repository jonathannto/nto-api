package br.eng.jonathan.geriluh_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.eng.jonathan.geriluh_api.controller.open_api.PaymentTypeControllerOpenApi;
import br.eng.jonathan.geriluh_api.dto.PaymentTypeDTO;
import br.eng.jonathan.geriluh_api.dto.assembler.PaymentTypeDTOAssembler;
import br.eng.jonathan.geriluh_api.service.PaymentTypeService;

@RestController
@RequestMapping(value = "/v1/payment-types", produces = "application/json")
public class PaymentTypeController implements PaymentTypeControllerOpenApi {

    @Autowired
    private PaymentTypeService service;

    @Autowired
    private PaymentTypeDTOAssembler assembler;

    @GetMapping
    public ResponseEntity<Page<EntityModel<PaymentTypeDTO>>> listPaymentType(Pageable pageable) {
        var paymentTypes = service.listAllPaymentType(pageable)
                .map( paymentType -> assembler.mapToEntityModelDTO(paymentType));
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
