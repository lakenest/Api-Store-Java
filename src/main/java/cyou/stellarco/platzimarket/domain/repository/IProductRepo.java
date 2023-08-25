package cyou.stellarco.platzimarket.domain.repository;
import cyou.stellarco.platzimarket.domain.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


public interface IProductRepo {

    List<Product> getAll();
    Optional<List<Product>> getByCategory(int categoryId);
    Optional<List<Product>> getScarseProducts(int quantity);
    Optional<Product> getProduct(int productId);
    void deleteById (int productId);

    Product save (Product product);
}
