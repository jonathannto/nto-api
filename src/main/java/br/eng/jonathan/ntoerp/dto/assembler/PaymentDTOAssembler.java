package br.eng.jonathan.ntoerp.dto.assembler;

import br.eng.jonathan.ntoerp.controller.PaymentController;
import br.eng.jonathan.ntoerp.dto.PaymentDTO;
import br.eng.jonathan.ntoerp.dto.mapper.PaymentMapper;
import br.eng.jonathan.ntoerp.model.Payment;
import br.eng.jonathan.ntoerp.model.PaymentType;
import br.eng.jonathan.ntoerp.service.PaymentService;
import br.eng.jonathan.ntoerp.service.PaymentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@RequiredArgsConstructor
public class PaymentDTOAssembler implements RepresentationModelAssembler<Payment, EntityModel<PaymentDTO>> {

    private final PaymentMapper paymentMapper;
    private final PaymentService paymentService;
    private final PaymentTypeService paymentTypeService;

    public Payment mapToEntity(PaymentDTO paymentDTO) {
        Payment payment;
        if (paymentDTO.getPaymentId() == null) {
            payment = paymentMapper.toEntity(paymentDTO);
        } else {
            payment = paymentService.findPaymentById(paymentDTO.getPaymentId());
            paymentMapper.updateEntityFromDto(paymentDTO, payment);
        }

        if (paymentDTO.getPaymentTypeId() != null) {
            PaymentType pt = paymentTypeService.findPaymentTypeById(paymentDTO.getPaymentTypeId());
            payment.setPaymentType(pt);
        }
        return payment;
    }

    @Override
    public EntityModel<PaymentDTO> toModel(Payment payment) {
        PaymentDTO dto = paymentMapper.toDTO(payment);
        return EntityModel.of(dto,
                linkTo(methodOn(PaymentController.class).getPaymentById(payment.getPaymentId())).withSelfRel().withType("GET"),
                linkTo(methodOn(PaymentController.class).list(null)).withRel("list").withType("GET"),
                linkTo(methodOn(PaymentController.class).createNewPayment(null, null)).withRel("create").withType("POST"),
                linkTo(methodOn(PaymentController.class).updatePayment(payment.getPaymentId(), null)).withRel("update").withType("PUT"),
                linkTo(methodOn(PaymentController.class).deletePayment(payment.getPaymentId())).withRel("delete").withType("DELETE")
        );
    }
}