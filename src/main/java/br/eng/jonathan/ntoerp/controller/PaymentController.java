package br.eng.jonathan.ntoerp.controller;

import br.eng.jonathan.ntoerp.controller.open_api.PaymentControllerOpenApi;
import br.eng.jonathan.ntoerp.dto.PaymentDTO;
import br.eng.jonathan.ntoerp.dto.assembler.PaymentDTOAssembler;
import br.eng.jonathan.ntoerp.service.PaymentService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/payments", produces = "application/json")
@RequiredArgsConstructor
public class PaymentController implements PaymentControllerOpenApi {

    private final PaymentService service;
    private final PaymentDTOAssembler assembler;

    @GetMapping
    public ResponseEntity<Page<EntityModel<PaymentDTO>>> list(Pageable pageable) {
        var payments = service.listAllPayments(pageable)
                .map(assembler::toModel);

        return ResponseEntity.ok(payments);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<EntityModel<PaymentDTO>> getPaymentById(@PathVariable Long paymentId) {

        var payment = service.findPaymentById(paymentId);

        return ResponseEntity.ok()
                .body(assembler.toModel(payment));
    }

    @PostMapping
    public ResponseEntity<EntityModel<PaymentDTO>> createNewPayment(@Valid @RequestBody PaymentDTO paymentDTO, HttpServletResponse response) {
        var payment = service.createPayment(assembler.mapToEntity(paymentDTO));

        return new ResponseEntity<EntityModel<PaymentDTO>>(assembler.toModel(payment), HttpStatus.CREATED);
    }

    @PutMapping("/{paymentId}")
    public ResponseEntity<EntityModel<PaymentDTO>> updatePayment(@PathVariable Long paymentId, @Valid @RequestBody PaymentDTO paymentDTO) {
        return ResponseEntity.ok(assembler.toModel(
                service.updatePayment(paymentId, paymentDTO)));
    }

    @DeleteMapping("/{paymentId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deletePayment(@PathVariable Long paymentId) {
        service.deletePayment(paymentId);
        return ResponseEntity.noContent().build();
    }
}
