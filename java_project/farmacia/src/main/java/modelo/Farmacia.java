package modelo;

import dao.FarmaciaDAO;
import java.util.ArrayList;

/**
 *
 * @author milto
 */
public class Farmacia {
    private FarmaciaDAO farmaciaDao;
    
    private ArrayList<Empleado> empleados;//tiene cargo
    private ArrayList<Venta> ventas;//tiene detalle ventas
    private ArrayList<CargoEmpleado> cargosEmpleados;
    private ArrayList<Producto> productos;//tiene tipoProducto
    private ArrayList<TipoProducto> tiposProductos;
    private ArrayList<Cliente> clientes;
    private ArrayList<Farmaceutica> farmaceuticas;
    private Empleado empleadoActual;
    private Vistas vistas;
    
    public Farmacia (Empleado empleado){
        empleadoActual = empleado;
        this.empleados = farmaciaDao.cargarEmpleados();
        this.ventas = farmaciaDao.cargarVentas();
        this.cargosEmpleados = farmaciaDao.cargarCargosEmpleados();
        this.productos = farmaciaDao.cargarProductos();
        this.tiposProductos = farmaciaDao.cargarTiposProductos();
        this.clientes = farmaciaDao.cargarClientes();
        this.farmaceuticas = farmaciaDao.cargarFarmaceuticas();
        this.vistas = new Vistas();
    }
}
