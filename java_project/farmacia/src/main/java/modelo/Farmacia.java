package modelo;

import java.util.ArrayList;

/**
 *
 * @author milto
 */
public class Farmacia {
    private ArrayList<Empleado> empleados;//tiene cargo
    private ArrayList<Venta> ventas;//tiene detalle ventas
    private ArrayList<CargoEmpleado> cargosEmpleados;
    private ArrayList<Producto> productos;//tiene tipoProducto
    private ArrayList<TipoProducto> tiposProductos;
    private ArrayList<Cliente> clientes;
    private ArrayList<Farmaceutica> farmaceuticas;
    private Vistas vistas;
}
