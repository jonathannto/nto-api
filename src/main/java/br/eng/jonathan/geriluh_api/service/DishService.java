package br.eng.jonathan.geriluh_api.service;

import br.eng.jonathan.geriluh_api.dto.DishDTO;
import br.eng.jonathan.geriluh_api.dto.mapper.DishMapper;
import br.eng.jonathan.geriluh_api.exception_handler.exceptions.NotFoundException;
import br.eng.jonathan.geriluh_api.model.Dish;
import br.eng.jonathan.geriluh_api.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DishService {

    private static final String DISH_SEARCH_ERRO = "DISH.SEARCH_ERROR";

    private final DishMapper dishMapper;
    private final MessageSource messageSource;
    private final DishRepository repository;

    public Page<Dish> listAllDishes(Pageable pagination) {
        return repository.findAll(pagination);
    }

    public Dish findDishById(Long dishId) throws NotFoundException {
        return repository.findById(dishId).orElseThrow(() -> new NotFoundException(getMessageErro()));
    }

    public Dish createDish(Dish dish) {
        return repository.save(dish);
    }

    public Dish updateDish(Long dishId, DishDTO dishDTO) {
        Dish dish = findDishById(dishId);

        dishMapper.updateEntityFromDto(dishDTO, dish);

        return repository.save(dish);
    }

    public void deleteDish(Long dishId) {
        if (!repository.existsById(dishId)) {
            throw new NotFoundException("Dish not found");
        }
        repository.deleteById(dishId);
    }

    private String getMessageErro() {
        return messageSource.getMessage(DishService.DISH_SEARCH_ERRO, null, LocaleContextHolder.getLocale());
    }

}
