package modelo;

import java.util.ArrayList;

/**
 *
 * @author milto
 */
public class DetalleVenta {
    private Venta venta;
    private Producto producto;
    private int cantidad;
    private double precioUnitario;

    public DetalleVenta(Venta venta, Producto producto, int cantidad, double precioUnitario) {
        this.venta = venta;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public Venta getVenta() {
        return venta;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String toString () {
        String texto = "";
        texto += "Venta: " + venta.getId() + "\n";
        texto += "Producto: " + producto.getNombre() + "\n";
        texto += "Cantidad: " + cantidad + "\n";
        texto += "Precio unitario: " + precioUnitario + "\n";
        return texto;
    }    
}
