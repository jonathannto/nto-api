package br.eng.jonathan.ntoerp.dto.assembler;

import br.eng.jonathan.ntoerp.controller.OrderController;
import br.eng.jonathan.ntoerp.dto.OrderDTO;
import br.eng.jonathan.ntoerp.dto.mapper.OrderMapper;
import br.eng.jonathan.ntoerp.model.Order;
import br.eng.jonathan.ntoerp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@RequiredArgsConstructor
public class OrderDTOAssembler implements RepresentationModelAssembler<Order, EntityModel<OrderDTO>> {

    private final OrderMapper orderMapper;
    private final OrderService orderService;

    public Order mapToEntity(OrderDTO orderDTO) {
        if (orderDTO.getOrderId() == null) {
            return orderMapper.toEntity(orderDTO);
        }
        Order existingOrder = orderService.findOrderById(orderDTO.getOrderId());
        orderMapper.updateEntityFromDto(orderDTO, existingOrder);
        return existingOrder;
    }

    @Override
    public EntityModel<OrderDTO> toModel(Order order) {
        OrderDTO dto = orderMapper.toDTO(order);
        return EntityModel.of(dto,
                linkTo(methodOn(OrderController.class).getOrderById(order.getOrderId())).withSelfRel().withType("GET"),
                linkTo(methodOn(OrderController.class).list(null)).withRel("list").withType("GET"),
                linkTo(methodOn(OrderController.class).createNewOrder(null, null)).withRel("create").withType("POST"),
                linkTo(methodOn(OrderController.class).updateOrder(order.getOrderId(), null)).withRel("update").withType("PUT"),
                linkTo(methodOn(OrderController.class).deleteOrder(order.getOrderId())).withRel("delete").withType("DELETE")
        );
    }
}