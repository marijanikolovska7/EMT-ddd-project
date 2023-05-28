package mk.ukim.finki.emt.productcatalog.domain.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.productcatalog.domain.valueobjects.ProductId;
import mk.ukim.finki.emt.productcatalog.domain.exceptions.ProductNotFound;
import mk.ukim.finki.emt.productcatalog.domain.models.Product;
import mk.ukim.finki.emt.productcatalog.domain.repository.ProductRepository;
import mk.ukim.finki.emt.productcatalog.domain.service.ProductService;
import mk.ukim.finki.emt.productcatalog.domain.service.forms.ProductForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    @Override
    public Product findById(ProductId id) {
        return productRepository.findById(id).orElseThrow(ProductNotFound::new);
    }

    @Override
    public Product createProduct(ProductForm form) {
        Product p = Product.build(form.getProductName(), form.getPrice(),form.getSales());
        productRepository.save(p);
        return p;
    }

    @Override
    public Product orderItemCreated(ProductId productId, int quantity) {
        Product p = productRepository.findById(productId).orElseThrow(ProductNotFound::new);
        p.addSales(quantity);
        productRepository.saveAndFlush(p);
        return p;
    }

    @Override
    public Product orderItemRemoved(ProductId productId, int quantity) {
        Product p = productRepository.findById(productId).orElseThrow(ProductNotFound::new);
        p.removeSales(quantity);
        productRepository.saveAndFlush(p);
        return p;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
