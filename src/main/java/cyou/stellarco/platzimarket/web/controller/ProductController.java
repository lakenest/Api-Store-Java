package cyou.stellarco.platzimarket.web.controller;

import cyou.stellarco.platzimarket.domain.Product;
import cyou.stellarco.platzimarket.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public List<Product> getAll() {
        return productService.getAll();
    }

    @PostMapping
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable("id") int productId) {
        return productService.deleteById(productId);
    }


    @GetMapping("/category/{id}")
    public Optional<List<Product>> getProductByCategory(@PathVariable("id") int categoryId) {
        return productService.getByCategory(categoryId);
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable("id") int productId) {
        return productService.getProduct(productId);
    }

}
