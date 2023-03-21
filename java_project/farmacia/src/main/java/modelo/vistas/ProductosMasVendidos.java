package modelo.vistas;

/**
 *
 * @author milto
 */
public class ProductosMasVendidos {
    private String nombre;
    private int cantidad;
    
    public ProductosMasVendidos(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }
    
    
}
