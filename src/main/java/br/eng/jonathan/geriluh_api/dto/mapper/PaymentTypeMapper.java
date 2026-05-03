package br.eng.jonathan.geriluh_api.dto.mapper;

import br.eng.jonathan.geriluh_api.dto.PaymentTypeDTO;
import br.eng.jonathan.geriluh_api.model.PaymentType;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PaymentTypeMapper {

    PaymentTypeDTO toDTO(PaymentType paymentType);

    PaymentType toEntity(PaymentTypeDTO paymentTypeDTO);

    @Mapping(target = "paymentTypeId", ignore = true)
    void updateEntityFromDto(PaymentTypeDTO dto, @MappingTarget PaymentType entity);
}