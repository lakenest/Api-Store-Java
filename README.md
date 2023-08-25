# Api-Store-Java
#Cadena de conexion Mysql
spring.jpa.hibernate.ddl-auto=update
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql=true
#Nombre de la bd minuscula
spring.datasource.url=jdbc:mysql://127.0.0.1:3308/namebd?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false
spring.datasource.username=username
spring.datasource.password=password
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
#Path o Contexto Api
server.servlet.context-path=/platzi/api

#Crear Clase de Tabla de la db (nombre en singular) usar anotaciones en orden.
@Entity, @Table, @Column, @Id, @Strategy.

#Clase de Tabla con clave compuesta se crea una clase solo con anotacion @Embeddable 'nameTablePK' y se referencia como
llave primaria con @EmbeddedId

#Crear relaciones entre tablas de muchos a muchos o de muchos a uno; crear una variable del tipo
relaciona y usar las anotaciones @ManyToOne, @ManyToMany y propiedades insertable y updatable false.
@ManyToOne
@JoinColumn(name = "id_categoria", insertable = false, updatable = false)

#Se hace lo opuesto en la otra tabla con @OneToMany e indicar el nombre de la variable que los relaciona.
@OneToMany(mappedBy = "categoria")

***ManyToOne con objeto y #OneToMany con Lista de Objeto.***

#Crear Interface que extienda CrudRepository con parametro Clase y ClavePrimaria de la Clase.
#Crear Clase implemente Interface Repositorio,
    private IProductoRepo iProductoRepo; - Intanciar la clase.

#En interface repositorio creamos metodo para consultar la bd por query methods o sql nativas.
    **QueryMethos***
    List<Producto> findByIdCategoria(Integer idCategoria);

    ***QueryNativo***
    @Query(value = "SELECT * FROM producto WHERE id_categoria = ?", nativeQuery = true)
    List<Producto> selectAllProductsForCategory(Integer id_categoria);

#En clase repositorio creamos metodos cuyo retorno son los metodos de la interface.
    public List<Producto> getAllProductQM(Integer idCategoria){
        return iProductoRepo.findByIdCategoria(idCategoria);
    }
#Añadir anotacion @Repository en la clase repositorio porque es la que interactua con la base de datos directamente.

#Mapper para convertir una clase Producto en Product y orientar la api al dominio sin exponer la db.
buscar en google añadir en pom bloque properties, dependencies, plugins.
añadir plugin mapstruct support for idea

#en domain creamos clases traducidas Category Product sin llave primaria puede tener menos campos que la entity para no mostar
la estructura de la bd.

#en repository creamos ProductRepository se nombran los metodos con referencia a Product; ya no extiende de crudRepository.

#crear package mapper, interface CategoryMapper con anotacion: @Mapper(componentModel = "spring")
***crear mapper para traducir objetos de entity a domain***

    @Mappings({
            @Mapping(source ="idCategoria", target = "categoryId"),
            @Mapping(source ="descripcion", target = "category"),
            @Mapping(source ="estado", target = "active"),
    })
    Category toCategory(Categoria categoria);

    @InheritInverseConfiguration
    @Mapping(target = "productos", ignore = true)
    Categoria toCategoria(Category category);

***cuanto vamos a pasar una clase completa añadir***
    Cabecera: @Mapper(componentModel = "spring", uses = {ICategoryMapper.class})
    Cuerpo: @Mapping(source = "categoria" , target = "category")

#implementar interface IProductRepo en Clase ProductoRepo, se mantiene la instancia a IProductoRepo.

    private IProductoRepo iProductoRepo;
    private IProductMapper iProductMapper;

    @Override
    public List<Product> getAll() {
        List<Producto> productos = (List<Producto>) iProductoRepo.findAll();
        return iProductMapper.toProducts(productos);
    }
#Inyeccion de dependencias se añade la anotacion @Autowired antes de la instancia.
    @Autowired
    private IProductoRepo iProductoRepo;
    @Autowired
    private IProductMapper iProductMapper;
***Debes estar seguro que es un componente de Spring***

#Crear package Service, similar a los casos de uso. Inicia con anotacion @Service y se inyecta el IProductRepo
    @Autowired
    private IProductRepo iProductRepo;

    public List<Product> getAll(){
        return iProductRepo.getAll();
    }
***Optional es lista iterable, en lo posible usar Optional<Product>.map(item -> funcion(item))***

#Exponer la Api crear clase controller con anotacion @RestController @RequestMapping("/api/products")
@GetMapping @PostMapping @GetMapping
@RestController
@RequestMapping("/api/product")
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
    }
