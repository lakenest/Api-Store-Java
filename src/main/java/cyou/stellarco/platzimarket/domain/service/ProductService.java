package cyou.stellarco.platzimarket.domain.service;

import cyou.stellarco.platzimarket.domain.Product;
import cyou.stellarco.platzimarket.domain.repository.IProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private IProductRepo iProductRepo;

    public List<Product> getAll(){
        return iProductRepo.getAll();
    }

    public Optional<Product> getProduct(int productId){
        return iProductRepo.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory(int categoryId){
        return iProductRepo.getByCategory(categoryId);
    }

    public Product save(Product product){
        return iProductRepo.save(product);
    }

    public boolean deleteById(int productId){
        return getProduct(productId).map(prod ->{
            iProductRepo.deleteById(productId);
            return true;
        }).orElse(false);

    }
}
