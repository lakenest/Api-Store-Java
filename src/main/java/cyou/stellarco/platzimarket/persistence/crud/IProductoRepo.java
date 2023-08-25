package cyou.stellarco.platzimarket.persistence.crud;

import cyou.stellarco.platzimarket.persistence.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IProductoRepo extends CrudRepository<Producto,Integer> {

    List<Producto> findByIdCategoria(int idCategoria);

    Optional <List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);

    @Query(value = "SELECT * FROM productos WHERE id_categoria = ?", nativeQuery = true)
    List<Producto> selectAllProductsForCategory(int id_categoria);

}
