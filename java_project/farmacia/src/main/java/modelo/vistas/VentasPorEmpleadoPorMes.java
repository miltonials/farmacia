package modelo.vistas;

/**
 *
 * @author milto
 */
public class VentasPorEmpleadoPorMes {
    private String mes;
    private String nombreEmpleado;
    private String cantidadVentas;
    private String montoTotalVendido;

    public VentasPorEmpleadoPorMes(String mes, String nombreEmpleado, String cantidadVentas, String montoTotalVendido) {
        this.mes = mes;
        this.nombreEmpleado = nombreEmpleado;
        this.cantidadVentas = cantidadVentas;
        this.montoTotalVendido = montoTotalVendido;
    }

    public String getMes() {
        return mes;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public String getCantidadVentas() {
        return cantidadVentas;
    }

    public String getMontoTotalVendido() {
        return montoTotalVendido;
    }
    
    
}
