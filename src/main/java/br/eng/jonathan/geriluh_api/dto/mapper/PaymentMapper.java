package br.eng.jonathan.geriluh_api.dto.mapper;

import br.eng.jonathan.geriluh_api.dto.PaymentDTO;
import br.eng.jonathan.geriluh_api.model.Payment;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PaymentMapper {

    @Mapping(target = "paymentType", ignore = true)
    Payment toEntity(PaymentDTO paymentDTO);

    @Mapping(target = "paymentTypeId", source = "paymentType.paymentTypeId")
    PaymentDTO toDTO(Payment payment);

    @Mapping(target = "paymentId", ignore = true)
    @Mapping(target = "paymentType", ignore = true)
    void updateEntityFromDto(PaymentDTO dto, @MappingTarget Payment entity);
}