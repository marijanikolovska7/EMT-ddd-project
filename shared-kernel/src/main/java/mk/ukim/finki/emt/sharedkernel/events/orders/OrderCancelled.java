package mk.ukim.finki.emt.sharedkernel.events.orders;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.events.DomainEvent;

@Getter
public class OrderCancelled extends DomainEvent {
    private String productId;
    private int quantity;

    public OrderCancelled (String topic){
        super(TopicHolder.TOPIC_ORDER_CANCELLLED);
    }

    public OrderCancelled(String topic, String productId, int quantity) {
        super(TopicHolder.TOPIC_ORDER_CANCELLLED);
        this.productId = productId;
        this.quantity = quantity;
    }
}
