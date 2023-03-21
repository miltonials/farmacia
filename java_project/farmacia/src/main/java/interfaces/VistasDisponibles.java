package interfaces;

import java.util.ArrayList;
import modelo.vistas.*;

/**
 * Interfaz que contiene los métodos para cargar las vistas de la farmacia.
 * @author Milton Barrera
 */
public interface VistasDisponibles {
    public ArrayList<MontoTotalVendidoPorMes> cargarMontoTotalVendidoPorMes();
    public ArrayList<ProductosMasVendidos> cargarProductosMasVendidos();
    public ArrayList<ProductosMasVendidosPorMes> cargarProductosMasVendidosPorMes();
    public ArrayList<VentasPorEmpleadoPorMes> cargarVentasPorEmpleadoPorMes();
}
