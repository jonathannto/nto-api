package br.eng.jonathan.geriluh_api.dto.assembler;

import br.eng.jonathan.geriluh_api.controller.DishController;
import br.eng.jonathan.geriluh_api.dto.DishDTO;
import br.eng.jonathan.geriluh_api.dto.mapper.DishMapper;
import br.eng.jonathan.geriluh_api.model.Dish;
import br.eng.jonathan.geriluh_api.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Assembler para transformar a entidade Dish em um recurso rico com HATEOAS.
 *
 * @author Jonathan Nascimento
 * @since 1.0 (Refatorado para MapStruct em 2026)
 */
@Component
@RequiredArgsConstructor
public class DishDTOAssembler implements RepresentationModelAssembler<Dish, EntityModel<DishDTO>> {

    private final DishMapper dishMapper;
    private final DishService dishService;

    public Dish mapToEntity(DishDTO dishDTO) {
        if (dishDTO.getDishId() == null) {
            return dishMapper.toEntity(dishDTO);
        }

        Dish existingDish = dishService.findDishById(dishDTO.getDishId());
        dishMapper.updateEntityFromDto(dishDTO, existingDish);
        return existingDish;
    }

    @Override
    public EntityModel<DishDTO> toModel(Dish dish) {
        DishDTO dto = dishMapper.toDTO(dish);

        return EntityModel.of(dto,
                linkTo(methodOn(DishController.class).getDishById(dish.getDishId())).withSelfRel().withType("GET"),
                linkTo(methodOn(DishController.class).list(null)).withRel("list").withType("GET"),
                linkTo(methodOn(DishController.class).createNewDish(null, null)).withRel("create").withType("POST"),
                linkTo(methodOn(DishController.class).updateDish(dish.getDishId(), null)).withRel("update").withType("PUT"),
                linkTo(methodOn(DishController.class).deleteDish(dish.getDishId())).withRel("delete").withType("DELETE")
        );
    }
}