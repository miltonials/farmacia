package modelo;

/**
 *
 * @author milto
 */
public class Producto {
    private int id;
    private Farmaceutica farmaceutica;
    private TipoProducto tipo;
    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidadStock;

    public Producto(Farmaceutica farmaceutica, TipoProducto tipo, String nombre, String descripcion, double precio, int cantidadStock) {
        this.farmaceutica = farmaceutica;
        this.tipo = tipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidadStock = cantidadStock;
    }
    
    public int getId() {
        return id;
    }

    public Farmaceutica getFarmaceutica() {
        return farmaceutica;
    }

    public TipoProducto getTipo() {
        return tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFarmaceutica(Farmaceutica farmaceutica) {
        this.farmaceutica = farmaceutica;
    }

    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }
    
    
}
