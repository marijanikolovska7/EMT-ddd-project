package mk.ukim.finki.emt.productcatalog.domain.repository;

import mk.ukim.finki.emt.productcatalog.domain.valueobjects.ProductId;
import mk.ukim.finki.emt.productcatalog.domain.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, ProductId> {
}
