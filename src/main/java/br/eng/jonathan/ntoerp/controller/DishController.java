package br.eng.jonathan.ntoerp.controller;

import br.eng.jonathan.ntoerp.controller.open_api.DishControllerOpenApi;
import br.eng.jonathan.ntoerp.dto.DishDTO;
import br.eng.jonathan.ntoerp.dto.assembler.DishDTOAssembler;
import br.eng.jonathan.ntoerp.service.DishService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/dishes", produces = "application/json")
@RequiredArgsConstructor
public class DishController implements DishControllerOpenApi {

    private final DishService service;
    private final DishDTOAssembler assembler;

    @GetMapping
    public ResponseEntity<Page<EntityModel<DishDTO>>> list(Pageable pageable) {
        var dishes = service.listAllDishes(pageable)
                .map(assembler::toModel);

        return ResponseEntity.ok(dishes);
    }

    @GetMapping("/{dishId}")
    public ResponseEntity<EntityModel<DishDTO>> getDishById(@PathVariable Long dishId) {
        var dish = service.findDishById(dishId);

        return ResponseEntity.ok()
                .body(assembler.toModel(dish));
    }

    @PostMapping
    public ResponseEntity<EntityModel<DishDTO>> createNewDish(@Valid @RequestBody DishDTO dishDTO, HttpServletResponse response) {
        var dish = service.createDish(assembler.mapToEntity(dishDTO));

        return new ResponseEntity<>(assembler.toModel(dish), HttpStatus.CREATED);
    }

    @PutMapping("/{dishId}")
    public ResponseEntity<EntityModel<DishDTO>> updateDish(@PathVariable Long dishId, @Valid @RequestBody DishDTO dishDTO) {
        return ResponseEntity.ok(assembler.toModel(
                service.updateDish(dishId, dishDTO)));
    }

    @DeleteMapping("/{dishId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteDish(@PathVariable Long dishId) {
        service.deleteDish(dishId);
        return ResponseEntity.noContent().build();
    }
}
