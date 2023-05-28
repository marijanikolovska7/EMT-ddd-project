package mk.ukim.finki.emt.productcatalog.domain.models;


import lombok.Getter;
import mk.ukim.finki.emt.productcatalog.domain.valueobjects.ProductId;
import mk.ukim.finki.emt.sharedkernel.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.financial.Money;

import javax.persistence.*;

@Entity
@Table(name="product")
@Getter
public class Product extends AbstractEntity<ProductId> {
    private String productName;
   private int sales = 0;
    @AttributeOverrides({
           @AttributeOverride(name
                   = "amount", column = @Column(name = "price_amount")),
           @AttributeOverride(name = "currency", column = @Column(name = "price_currency"))
   })
   private Money price;
    private Product(){
        super(ProductId.randomId(ProductId.class));
    }
    public static Product build(String productName,Money price,int sales){
        Product p = new Product();
        p.productName = productName;
        p.price = price;
        p.sales = sales;
        return p;
    }
    public void addSales(int quantity){
        this.sales = this.sales-quantity;
    }
    public void removeSales(int quantity){
        this.sales -= quantity;
    }
}
