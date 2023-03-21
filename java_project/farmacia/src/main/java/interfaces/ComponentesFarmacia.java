package interfaces;

import java.util.ArrayList;
import modelo.CargoEmpleado;
import modelo.DetalleVenta;
import modelo.Empleado;
import modelo.Farmaceutica;
import modelo.Producto;
import modelo.TipoProducto;
import modelo.Venta;
import modelo.Cliente;

/**
 * Interfaz que contiene los métodos para cargar los componentes de la farmacia.
 *
 * @author Milton Barrera
 */
public interface ComponentesFarmacia {
    /*
     *  Métodos para cargar los componentes de la farmacia
     */
    public ArrayList<DetalleVenta> cargarDetallesVentas();
    public ArrayList<Venta> cargarVentas();
    public ArrayList<Farmaceutica> cargarFarmaceuticas();
    public ArrayList<TipoProducto> cargarTiposProductos();
    public ArrayList<Producto> cargarProductos();
    public ArrayList<CargoEmpleado> cargarCargosEmpleados();
    public ArrayList<Empleado> cargarEmpleados();
    public ArrayList<Cliente> cargarClientes();
}