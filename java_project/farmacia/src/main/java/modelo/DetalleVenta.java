package modelo;

import java.util.ArrayList;

/**
 *
 * @author milto
 */
public class DetalleVenta {
    private int id;
    private ArrayList<Producto> productos;
    private int cantidad;
    private double precioUnitario;

    public DetalleVenta(int id, ArrayList<Producto> productos, int cantidad, double precioUnitario) {
        this.id = id;
        this.productos = productos;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
    
    
}
