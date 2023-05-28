package mk.ukim.finki.emt.sharedkernel.events.orders;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.events.DomainEvent;

@Getter
public class OrderCreated extends DomainEvent {
    private String productId;
    private int quantity;

    public OrderCreated(String topic, int quantity){
        super(TopicHolder.TOPIC_ORDER_CREATED);
    }

    public OrderCreated(String topic, String productId, int quantity) {
        super(TopicHolder.TOPIC_ORDER_CREATED);
        this.productId = productId;
        this.quantity = quantity;
    }
}
