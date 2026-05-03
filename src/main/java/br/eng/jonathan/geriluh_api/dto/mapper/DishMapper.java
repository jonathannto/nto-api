package br.eng.jonathan.geriluh_api.dto.mapper;

import br.eng.jonathan.geriluh_api.dto.DishDTO;
import br.eng.jonathan.geriluh_api.model.Dish;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DishMapper {

    DishDTO toDTO(Dish dish);

    Dish toEntity(DishDTO dishDTO);

    @Mapping(target = "dishId", ignore = true)
    void updateEntityFromDto(DishDTO dto, @MappingTarget Dish entity);
}