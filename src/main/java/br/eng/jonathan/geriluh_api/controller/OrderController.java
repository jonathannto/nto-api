package br.eng.jonathan.geriluh_api.controller;

import br.eng.jonathan.geriluh_api.controller.open_api.OrderControllerOpenApi;
import br.eng.jonathan.geriluh_api.dto.OrderDTO;
import br.eng.jonathan.geriluh_api.dto.assembler.OrderDTOAssembler;
import br.eng.jonathan.geriluh_api.exception_handler.exceptions.NotFoundException;
import br.eng.jonathan.geriluh_api.service.OrderService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/orders", produces = "application/json")
@RequiredArgsConstructor
public class OrderController implements OrderControllerOpenApi {

    private final OrderService service;
    private final OrderDTOAssembler assembler;

    @GetMapping
    public ResponseEntity<Page<EntityModel<OrderDTO>>> list(Pageable pageable) {
        var orders = service.listAllOrders(pageable)
                .map(assembler::toModel);

        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<EntityModel<OrderDTO>> getOrderById(@PathVariable Long orderId) {
        var order = service.findOrderById(orderId);

        return ResponseEntity.ok()
                .body(assembler.toModel(order));
    }

    @PostMapping
    public ResponseEntity<EntityModel<OrderDTO>> createNewOrder(@Valid @RequestBody OrderDTO orderDTO, HttpServletResponse response) throws NotFoundException {
        var order = service.createOrder(assembler.mapToEntity(orderDTO));

        return new ResponseEntity<>(assembler.toModel(order), HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<EntityModel<OrderDTO>> updateOrder(@PathVariable Long orderId, @Valid @RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok(assembler.toModel(
                service.updateOrder(orderId, orderDTO)));
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        service.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

}
