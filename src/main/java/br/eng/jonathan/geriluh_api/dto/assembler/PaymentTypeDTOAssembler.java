package br.eng.jonathan.geriluh_api.dto.assembler;

import br.eng.jonathan.geriluh_api.controller.PaymentTypeController; // Ajustado para o controller correto
import br.eng.jonathan.geriluh_api.dto.PaymentTypeDTO;
import br.eng.jonathan.geriluh_api.dto.mapper.PaymentTypeMapper;
import br.eng.jonathan.geriluh_api.model.PaymentType;
import br.eng.jonathan.geriluh_api.service.PaymentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@RequiredArgsConstructor
public class PaymentTypeDTOAssembler implements RepresentationModelAssembler<PaymentType, EntityModel<PaymentTypeDTO>> {

    private final PaymentTypeMapper paymentTypeMapper;
    private final PaymentTypeService paymentTypeService;

    public PaymentType mapToEntity(PaymentTypeDTO paymentTypeDTO) {
        if (paymentTypeDTO.getPaymentTypeId() == null) {
            return paymentTypeMapper.toEntity(paymentTypeDTO);
        }
        PaymentType existing = paymentTypeService.findPaymentTypeById(paymentTypeDTO.getPaymentTypeId());
        paymentTypeMapper.updateEntityFromDto(paymentTypeDTO, existing);
        return existing;
    }

    @Override
    public EntityModel<PaymentTypeDTO> toModel(PaymentType pt) {
        PaymentTypeDTO dto = paymentTypeMapper.toDTO(pt);
        return EntityModel.of(dto,
                linkTo(methodOn(PaymentTypeController.class).getPaymentTypeById(pt.getPaymentTypeId())).withSelfRel().withType("GET"),
                linkTo(methodOn(PaymentTypeController.class).list(null)).withRel("list").withType("GET"),
                linkTo(methodOn(PaymentTypeController.class).createNewPaymentType(null, null)).withRel("create").withType("POST"),
                linkTo(methodOn(PaymentTypeController.class).updatePaymentType(pt.getPaymentTypeId(), null)).withRel("update").withType("PUT"),
                linkTo(methodOn(PaymentTypeController.class).deletePaymentType(pt.getPaymentTypeId())).withRel("delete").withType("DELETE")
        );
    }
}