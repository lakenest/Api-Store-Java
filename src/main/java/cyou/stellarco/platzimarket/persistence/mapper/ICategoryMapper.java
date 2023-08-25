package cyou.stellarco.platzimarket.persistence.mapper;


import cyou.stellarco.platzimarket.domain.Category;
import cyou.stellarco.platzimarket.persistence.entity.Categoria;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ICategoryMapper {


    @Mapping(source ="idCategoria", target = "categoryId")
    @Mapping(source ="descripcion", target = "category")
    @Mapping(source ="estado", target = "active")
    Category toCategory(Categoria categoria);

    @InheritInverseConfiguration
    @Mapping(target = "productos", ignore = true)
    Categoria toCategoria(Category category);
}
