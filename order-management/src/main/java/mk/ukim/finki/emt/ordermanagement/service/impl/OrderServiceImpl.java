package mk.ukim.finki.emt.ordermanagement.service.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import mk.ukim.finki.emt.ordermanagement.domain.exceptions.OrderIdNotFound;
import mk.ukim.finki.emt.ordermanagement.domain.exceptions.OrderItemIdNotFound;
import mk.ukim.finki.emt.ordermanagement.domain.models.Order;
import mk.ukim.finki.emt.ordermanagement.domain.models.OrderId;
import mk.ukim.finki.emt.ordermanagement.domain.models.OrderItemId;
import mk.ukim.finki.emt.ordermanagement.domain.repository.OrderRepository;
import mk.ukim.finki.emt.ordermanagement.service.OrderService;
import mk.ukim.finki.emt.ordermanagement.service.forms.OrderForm;
import mk.ukim.finki.emt.ordermanagement.service.forms.OrderItemForm;
import mk.ukim.finki.emt.sharedkernel.events.orders.OrderCreated;
import mk.ukim.finki.emt.sharedkernel.infra.DomainEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final Validator validator;
    private final DomainEventPublisher domainEventPublisher;
    @Override
    public OrderId placeOrder(OrderForm orderForm) {
        Objects.requireNonNull(orderForm,"order must not be null!");
        var constraintViolations= validator.validate(orderForm);
        if(constraintViolations.size()>0){
            throw new ConstraintViolationException("The order for is not valid!",constraintViolations);
        }
        var newOrder = orderRepository.saveAndFlush(toDomainObject(orderForm));
        newOrder.getOrderItemList().forEach(item->domainEventPublisher.publish(new OrderCreated(item.getProductId().getId(),item.getQuantity())));
        return newOrder.getId();
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(OrderId orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public void addItem(OrderId orderId, OrderItemForm orderItemForm) throws OrderIdNotFound {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderIdNotFound::new);
        order.addItem(orderItemForm.getProduct(),orderItemForm.getQuantity());
        orderRepository.saveAndFlush(order);
        domainEventPublisher.publish(new OrderCreated(orderItemForm.getProduct().getId().getId(),orderItemForm.getQuantity()));
    }

    @Override
    public void deleteItem(OrderId orderId, OrderItemId orderItemId) throws OrderIdNotFound, OrderItemIdNotFound {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderIdNotFound::new);
        order.removeItem(orderItemId);
        orderRepository.saveAndFlush(order);
    }
    private Order toDomainObject(OrderForm orderForm){
        var order = new Order(Instant.now(),orderForm.getCurrency());
        orderForm.getItems().forEach(item->order.addItem(item.getProduct(),item.getQuantity()));
        return order;
    }
}
