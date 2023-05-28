package mk.ukim.finki.emt.productcatalog.config;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.productcatalog.domain.models.Product;
import mk.ukim.finki.emt.productcatalog.domain.repository.ProductRepository;
import mk.ukim.finki.emt.sharedkernel.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.financial.Money;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
@AllArgsConstructor
public class DataInitializer {
    private final ProductRepository productRepository;

    @PostConstruct
    public void initData(){
        Product p1 = Product.build("Shoes", Money.valueOf(Currency.EUR,55),2);
        Product p2 = Product.build("T-shirt", Money.valueOf(Currency.EUR,25),5);
        if(productRepository.findAll().isEmpty()){
            productRepository.saveAll(Arrays.asList(p1,p2));
        }
    }

}
