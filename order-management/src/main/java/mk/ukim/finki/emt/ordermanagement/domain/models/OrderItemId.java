package mk.ukim.finki.emt.ordermanagement.domain.models;

import mk.ukim.finki.emt.sharedkernel.base.DomainObjectId;

public class OrderItemId extends DomainObjectId {
    public OrderItemId(String uuid){
        super(uuid);
    }
    private OrderItemId() {
        super(OrderItemId.randomId(OrderItemId.class).getId());
    }

}
