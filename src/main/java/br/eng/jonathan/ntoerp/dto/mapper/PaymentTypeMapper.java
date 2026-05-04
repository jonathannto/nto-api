package br.eng.jonathan.ntoerp.dto.mapper;

import br.eng.jonathan.ntoerp.dto.PaymentTypeDTO;
import br.eng.jonathan.ntoerp.model.PaymentType;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PaymentTypeMapper {

    PaymentTypeDTO toDTO(PaymentType paymentType);

    PaymentType toEntity(PaymentTypeDTO paymentTypeDTO);

    @Mapping(target = "paymentTypeId", ignore = true)
    void updateEntityFromDto(PaymentTypeDTO dto, @MappingTarget PaymentType entity);
}