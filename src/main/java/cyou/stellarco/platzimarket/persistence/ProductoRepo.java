package cyou.stellarco.platzimarket.persistence;

import cyou.stellarco.platzimarket.domain.Product;
import cyou.stellarco.platzimarket.domain.repository.IProductRepo;
import cyou.stellarco.platzimarket.persistence.crud.IProductoRepo;
import cyou.stellarco.platzimarket.persistence.entity.Producto;
import cyou.stellarco.platzimarket.persistence.mapper.IProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepo implements IProductRepo {

    @Autowired
    private IProductoRepo iProductoRepo;
    @Autowired
    private IProductMapper iProductMapper;

    @Override
    public List<Product> getAll() {
        List<Producto> productos = (List<Producto>) iProductoRepo.findAll();
        return iProductMapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = iProductoRepo.findByIdCategoria(categoryId);
        return Optional.of(iProductMapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos =  iProductoRepo.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods -> iProductMapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        Optional<Producto> producto =  iProductoRepo.findById(productId);
        return producto.map(prod -> iProductMapper.toProduct(prod));
    }

    @Override
    public void deleteById(int productId) {
        iProductoRepo.deleteById(productId);
    }

    @Override
    public Product save(Product product) {
        Producto producto = iProductoRepo.save(iProductMapper.toProducto(product));
        return iProductMapper.toProduct(producto);
    }
}
