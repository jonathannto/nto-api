package br.eng.jonathan.ntoerp.dto.mapper;

import br.eng.jonathan.ntoerp.dto.DishDTO;
import br.eng.jonathan.ntoerp.model.Dish;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DishMapper {

    DishDTO toDTO(Dish dish);

    Dish toEntity(DishDTO dishDTO);

    @Mapping(target = "dishId", ignore = true)
    void updateEntityFromDto(DishDTO dto, @MappingTarget Dish entity);
}