package br.eng.jonathan.geriluh_api.dto.mapper;

import br.eng.jonathan.geriluh_api.dto.OrderDTO;
import br.eng.jonathan.geriluh_api.model.Order;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {

    OrderDTO toDTO(Order order);

    Order toEntity(OrderDTO orderDTO);

    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "cashRegisterId", ignore = true)
    @Mapping(target = "userId", ignore = true)
    void updateEntityFromDto(OrderDTO dto, @MappingTarget Order entity);
}