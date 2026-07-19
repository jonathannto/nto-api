package br.eng.jonathan.ntoerp.service;

import br.eng.jonathan.ntoerp.dto.OrderDTO;
import br.eng.jonathan.ntoerp.dto.mapper.OrderMapper;
import br.eng.jonathan.ntoerp.exception_handler.exceptions.NotFoundException;
import br.eng.jonathan.ntoerp.model.Order;
import br.eng.jonathan.ntoerp.repository.OrderRepository;
import lombok.AllArgsConstructor;
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
public class OrderService {

    private static final String ORDER_SEARCH_ERROR = "ORDER.SEARCH_ERROR";

    private final OrderMapper orderMapper;
    private final MessageSource messageSource;
    private final OrderRepository repository;

    public Page<Order> listAllOrders(Pageable pagination) {
        return repository.findAll(pagination);
    }

    public Order findOrderById(Long orderId) throws NotFoundException {
        return repository.findById(orderId).orElseThrow(() -> new NotFoundException(getErrorMessage()));
    }

    public Order createOrder(Order order) {
        return repository.save(order);
    }

    public Order updateOrder(Long orderId, OrderDTO orderDTO) {
        Order order = findOrderById(orderId);

        orderMapper.updateEntityFromDto(orderDTO, order);
        return repository.save(order);
    }

    public void deleteOrder(Long orderId) {
        if (!repository.existsById(orderId)) {
            throw new NotFoundException(getErrorMessage());
        }
        repository.deleteById(orderId);
    }

    private String getErrorMessage() {
        return messageSource.getMessage(ORDER_SEARCH_ERROR, null, LocaleContextHolder.getLocale());
    }
}
