package modelo;

import dao.FarmaciaDAO;
import java.util.ArrayList;

/**
 *
 * @author milto
 */
public class Farmacia {

    private static FarmaciaDAO farmaciaDao = new FarmaciaDAO();

    private ArrayList<Empleado> empleados;//tiene cargo
    private ArrayList<Venta> ventas;//tiene detalle ventas
    private ArrayList<CargoEmpleado> cargosEmpleados;
    private ArrayList<Producto> productos;//tiene tipoProducto
    private ArrayList<TipoProducto> tiposProductos;
    private ArrayList<Cliente> clientes;
    private ArrayList<Farmaceutica> farmaceuticas;
    private Empleado empleadoActual;
    private ArrayList<MontoTotalVendidoPorMes> montosTotalesVendidosPorMes;
    private ArrayList<VentasPorEmpleadoPorMes> ventasPorEmpleadoPorMes;
    private ArrayList<ProductosMasVendidos> productosMasVendidos;

    public ArrayList<ProductosMasVendidos> getProductosMasVendidos() {
        return productosMasVendidos;
    }

    public void setProductosMasVendidos(ArrayList<ProductosMasVendidos> productosMasVendidos) {
        this.productosMasVendidos = productosMasVendidos;
    }

    public ArrayList<ProductosMasVendidosPorMes> getProductosMasVendidosPorMes() {
        return productosMasVendidosPorMes;
    }

    public void setProductosMasVendidosPorMes(ArrayList<ProductosMasVendidosPorMes> productosMasVendidosPorMes) {
        this.productosMasVendidosPorMes = productosMasVendidosPorMes;
    }
    private ArrayList<ProductosMasVendidosPorMes> productosMasVendidosPorMes;
    private Vistas vistas;

    public Farmacia(Empleado empleado) {
        empleadoActual = empleado;
        this.farmaceuticas = farmaciaDao.cargarFarmaceuticas();
        this.tiposProductos = farmaciaDao.cargarTiposProductos();
        this.productos = farmaciaDao.cargarProductos(this);
        this.cargosEmpleados = farmaciaDao.cargarCargosEmpleados();
        this.empleados = farmaciaDao.cargarEmpleados();
        this.clientes = farmaciaDao.cargarClientes();
        this.montosTotalesVendidosPorMes = farmaciaDao.cargarMontosTotalesVendidosPorMes();
        this.ventasPorEmpleadoPorMes = farmaciaDao.cargarVentasPorEmpleadoPorMes();
        this.productosMasVendidos = farmaciaDao.cargarProductosMasVendidos();
        this.productosMasVendidosPorMes = farmaciaDao.cargarProductosMasVendidosPorMes();
        this.ventas = farmaciaDao.cargarVentas(this);

    }

    public ArrayList<VentasPorEmpleadoPorMes> getVentasPorEmpleadoPorMes() {
        return ventasPorEmpleadoPorMes;
    }

    public ArrayList<MontoTotalVendidoPorMes> getMontosTotalesVendidosPorMes() {
        return montosTotalesVendidosPorMes;
    }

    public FarmaciaDAO getFarmaciaDao() {
        return farmaciaDao;
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    public ArrayList<Venta> getVentas() {
        return ventas;
    }

    public ArrayList<CargoEmpleado> getCargosEmpleados() {
        return cargosEmpleados;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public ArrayList<TipoProducto> getTiposProductos() {
        return tiposProductos;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<Farmaceutica> getFarmaceuticas() {
        return farmaceuticas;
    }

    public Empleado getEmpleadoActual() {
        return empleadoActual;
    }

    public Vistas getVistas() {
        return vistas;
    }

    /**
     * Método que permite buscar una farmaceutica por su id.
     *
     * @param id : id de la farmaceutica
     * @return Farmaceutica : objeto que representa la farmaceutica.
     */
    public Farmaceutica buscarFarmaceuticaPorId(int id) {
        for (Farmaceutica farmaceutica : this.farmaceuticas) {
            if (farmaceutica.getId() == id) {
                return farmaceutica;
            }
        }
        return null;
    }
    public Cliente buscarClientePorId(int id) {
        for (Cliente cliente : this.clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }
    

    public Farmaceutica buscarFarmaceuticaPorNombre(String nombre) {
        for (Farmaceutica farmaceutica : this.farmaceuticas) {
            if (farmaceutica.getNombre().equals(nombre)) {
                return farmaceutica;
            }
        }
        return null;
    }

    public TipoProducto buscarTipoProductoPorNombre(String nombreTipoProducto) {
        for (TipoProducto tipoProducto : this.tiposProductos) {
            if (tipoProducto.getNombre().equals(nombreTipoProducto)) {
                return tipoProducto;
            }
        }
        return null;
    }

    public TipoProducto buscarTipoProductoPorId(int id) {
        for (TipoProducto tipoProducto : this.tiposProductos) {
            if (tipoProducto.getId() == id) {
                return tipoProducto;
            }
        }
        return null;
    }

    public Producto buscarProductoPorId(int id) {
        for (Producto producto : this.productos) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        return null;
    }

    public CargoEmpleado buscarCargoEmpleadoPorId(int id) {
        for (CargoEmpleado cargoEmpleado : this.cargosEmpleados) {
            if (cargoEmpleado.getId() == id) {
                return cargoEmpleado;
            }
        }
        return null;
    }

    public Empleado buscarEmpleadoPorId(int id) {
        for (Empleado empleado : empleados) {
            if (empleado.getId() == id) {
                return empleado;
            }
        }
        return null;
    }
    
    public Venta buscarVentaPorId(int id) {
        for (Venta venta : ventas) {
            if (venta.getId() == id) {
                return venta;
            }
        }
        return null;
    }
}
