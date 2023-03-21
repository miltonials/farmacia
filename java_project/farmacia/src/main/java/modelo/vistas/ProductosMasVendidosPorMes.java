package modelo.vistas;

/**
 *
 * @author milto
 */
public class ProductosMasVendidosPorMes {
    private String mes;
    private String nombreProducto;
    private int cantidadVendida;
    private double montoTotalVendido;

    public ProductosMasVendidosPorMes(String mes, String nombreProducto, int cantidadVendida, double montoTotalVendido) {
        this.mes = mes;
        this.nombreProducto = nombreProducto;
        this.cantidadVendida = cantidadVendida;
        this.montoTotalVendido = montoTotalVendido;
    }

    public String getMes() {
        return mes;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public double getMontoTotalVendido() {
        return montoTotalVendido;
    }
    
    
}
