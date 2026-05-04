package br.eng.jonathan.ntoerp.dto.mapper;

import br.eng.jonathan.ntoerp.dto.CashRegisterDTO;
import br.eng.jonathan.ntoerp.model.CashRegister;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CashRegisterMapper {

    @Mapping(target = "user", ignore = true)
    CashRegister toEntity(CashRegisterDTO cashRegisterDTO);

    @Mapping(target = "userId", source = "user.userId")
    CashRegisterDTO toDTO(CashRegister cashRegister);

    @Mapping(target = "cashRegisterId", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateEntityFromDto(CashRegisterDTO dto, @MappingTarget CashRegister entity);
}