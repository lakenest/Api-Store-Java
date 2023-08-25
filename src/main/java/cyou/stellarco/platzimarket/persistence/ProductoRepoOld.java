package cyou.stellarco.platzimarket.persistence;

import cyou.stellarco.platzimarket.persistence.crud.IProductoRepo;
import cyou.stellarco.platzimarket.persistence.entity.Producto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepoOld {
    private IProductoRepo iProductoRepo;

    public List<Producto> getAll(){
        return (List<Producto>) iProductoRepo.findAll();
    }

    public List<Producto> getAllProductQM(int idCategoria){
        return iProductoRepo.findByIdCategoria(idCategoria);
    }

    public List<Producto> getAllProductQN(int idCategoria){
        return iProductoRepo.selectAllProductsForCategory(idCategoria);
    }

    public Optional<List<Producto>> getEscasos(int cantidad){
        return iProductoRepo.findByCantidadStockLessThanAndEstado(cantidad, true);
    }

    public Optional<Producto> getProducto(int idProducto){
        return iProductoRepo.findById(idProducto);
    }

    public Producto saveProducto(Producto producto){
        return iProductoRepo.save(producto);
    }

    public void deleteProducto(int idProducto){
        iProductoRepo.deleteById(idProducto);
    }
}
