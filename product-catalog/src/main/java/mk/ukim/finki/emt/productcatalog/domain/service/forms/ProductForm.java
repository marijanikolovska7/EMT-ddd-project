package mk.ukim.finki.emt.productcatalog.domain.service.forms;

import lombok.Data;
import mk.ukim.finki.emt.sharedkernel.financial.Money;

@Data
public class ProductForm {
    private String productName;
    private Money price;
    private int sales;
}
