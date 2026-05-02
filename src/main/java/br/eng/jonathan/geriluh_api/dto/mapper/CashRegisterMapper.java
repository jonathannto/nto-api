package br.eng.jonathan.geriluh_api.dto.mapper;

import br.eng.jonathan.geriluh_api.dto.CashRegisterDTO;
import br.eng.jonathan.geriluh_api.model.CashRegister;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CashRegisterMapper {

    CashRegisterDTO toDTO(CashRegister cashRegister);

    CashRegister toEntity(CashRegisterDTO cashRegisterDTO);

    @Mapping(target = "cashRegisterId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntityFromDto(CashRegisterDTO dto, @MappingTarget CashRegister cashRegister);

}
