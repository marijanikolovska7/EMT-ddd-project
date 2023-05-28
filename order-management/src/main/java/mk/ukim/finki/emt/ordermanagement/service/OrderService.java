package mk.ukim.finki.emt.ordermanagement.service;

import mk.ukim.finki.emt.ordermanagement.domain.exceptions.OrderIdNotFound;
import mk.ukim.finki.emt.ordermanagement.domain.exceptions.OrderItemIdNotFound;
import mk.ukim.finki.emt.ordermanagement.domain.models.Order;
import mk.ukim.finki.emt.ordermanagement.domain.models.OrderId;
import mk.ukim.finki.emt.ordermanagement.domain.models.OrderItemId;
import mk.ukim.finki.emt.ordermanagement.service.forms.OrderForm;
import mk.ukim.finki.emt.ordermanagement.service.forms.OrderItemForm;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    OrderId placeOrder(OrderForm orderForm);
    List<Order> findAll();
    Optional<Order> findById(OrderId orderId);
    void addItem(OrderId orderId, OrderItemForm orderItemForm) throws OrderIdNotFound;
    void deleteItem(OrderId orderId, OrderItemId orderItemId) throws OrderIdNotFound, OrderItemIdNotFound;
}
