package mk.ukim.finki.emt.productcatalog.xport.events;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.productcatalog.domain.valueobjects.ProductId;
import mk.ukim.finki.emt.productcatalog.domain.service.ProductService;
import mk.ukim.finki.emt.sharedkernel.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.events.DomainEvent;
import mk.ukim.finki.emt.sharedkernel.events.orders.OrderCancelled;
import mk.ukim.finki.emt.sharedkernel.events.orders.OrderCreated;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductEventListener {
    private final ProductService productService;
@KafkaListener(topics = TopicHolder.TOPIC_ORDER_CREATED,groupId = "productCatalog")
    public void consumeOrderItemCreatedEvent(String jsonMessage){
    try{
        OrderCreated event = DomainEvent.fromJson(jsonMessage,OrderCreated.class);
        productService.orderItemCreated(ProductId.of(event.getProductId()),event.getQuantity());
    } catch (Exception e){

    }
}
    public void consumeOrderItemRemovedEvent(String jsonMessage){
        try{
            OrderCancelled event = DomainEvent.fromJson(jsonMessage,OrderCancelled.class);
            productService.orderItemRemoved(ProductId.of(event.getProductId()),event.getQuantity());
        } catch (Exception e){

        }
    }
}
