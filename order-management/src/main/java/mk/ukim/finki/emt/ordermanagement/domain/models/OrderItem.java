package mk.ukim.finki.emt.ordermanagement.domain.models;


import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.ordermanagement.domain.valueobjects.ProductId;
import mk.ukim.finki.emt.sharedkernel.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.base.DomainObjectId;
import mk.ukim.finki.emt.sharedkernel.financial.Money;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="order_item")
@Getter
public class OrderItem extends AbstractEntity<OrderItemId> {
    private Money itemPrice;
    @Column(name="quantity",nullable = false)
    private int quantity;

    @AttributeOverride(name="id",column = @Column(name="product_id",nullable = false))
    private ProductId productId;

    public OrderItem(){
        super(DomainObjectId.randomId(OrderItemId.class));
    }
    public Money subtotal(){
        return itemPrice.multiply(quantity);
    }
    public OrderItem(@NonNull ProductId productId,@NonNull Money itemPrice,int quantity){
        super(DomainObjectId.randomId(OrderItemId.class));
        this.productId = productId;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
    }

}
