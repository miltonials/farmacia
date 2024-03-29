/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import modelo.CargoEmpleado;
import modelo.Cliente;
import interfaces.ComponentesFarmacia;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Conexion;
import modelo.DetalleVenta;
import modelo.Empleado;
import modelo.Farmaceutica;
import modelo.Farmacia;
import modelo.Producto;
import modelo.TipoProducto;
import modelo.Venta;
import modelo.MontoTotalVendidoPorMes;
import modelo.VentasPorEmpleadoPorMes;
import modelo.ProductosMasVendidos;
import modelo.ProductosMasVendidosPorMes;

/**
 *
 * @author milto
 */
public class FarmaciaDAO implements ComponentesFarmacia {
    private Conexion conexion;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public FarmaciaDAO() {
        conexion = new Conexion();
        conexion.conectar();
    }

    @Override
    public ArrayList<DetalleVenta> cargarDetalleVenta(Farmacia farmacia, Venta venta) {
        ArrayList<DetalleVenta> detallesVenta = new ArrayList<>();
        String sql = "SELECT * FROM DETALLE_VENTA WHERE id_venta = ?";

        try {
            preparedStatement = conexion.prepararSql(sql);
            preparedStatement.setInt(1, venta.getId());
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                int idProducto = resultSet.getInt("id_producto");
                Producto producto = farmacia.buscarProductoPorId(idProducto);
                int cantidad = resultSet.getInt("cantidad");
                double precio = resultSet.getDouble("precio_unitario");
                DetalleVenta detalleVenta = new DetalleVenta(venta, producto, cantidad, precio);
                detallesVenta.add(detalleVenta);
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar detalles ventas: " + e.getMessage());
        }
        venta.setDetalleVenta(detallesVenta);
        return detallesVenta;
    }

    @Override
    public ArrayList<Venta> cargarVentas(Farmacia miFarmacia) {
        ArrayList<Venta> ventas = new ArrayList<>();
        String sql = "SELECT  * FROM VENTA";

        try {
            preparedStatement = conexion.prepararSql(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_venta");
                Date fecha = resultSet.getDate("fecha_emision");
                int idCliente = resultSet.getInt("id_cliente");
                int idEmpleado = resultSet.getInt("id_empleado");
                double total = resultSet.getDouble("total_venta");
                Cliente elCliente = miFarmacia.buscarClientePorId(idCliente);
                Empleado elEmp = miFarmacia.buscarEmpleadoPorId(idEmpleado);

                Venta venta = new Venta(id, fecha, elCliente, elEmp, total);
                
                ventas.add(venta);
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar ventas: " + e.getMessage());
            e.printStackTrace();
        }
        
        for (Venta ventaAct: ventas) {
            cargarDetalleVenta(miFarmacia, ventaAct);
        }
        
        return ventas;
    }

    public ArrayList<VentasPorEmpleadoPorMes> cargarVentasPorEmpleadoPorMes() {
        ArrayList<VentasPorEmpleadoPorMes> ventasPorEmpleadoPorMes = new ArrayList<>();
        String sql = "select *from ventas_por_empleado_por_mes";

        try {
            preparedStatement = conexion.prepararSql(sql);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                String mes = resultSet.getString("mes");
                String nombre = resultSet.getString("nombre_empleado");
                int cantidad = resultSet.getInt("cantidad_ventas");
                int total = resultSet.getInt("monto_total_vendido");
                VentasPorEmpleadoPorMes ventasPorEmpleadoPorMes1 = new VentasPorEmpleadoPorMes(mes, nombre, cantidad, total);
                ventasPorEmpleadoPorMes.add(ventasPorEmpleadoPorMes1);
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar ventas por empleado por mes: " + e.getMessage());
        }
        return ventasPorEmpleadoPorMes;
    }

    public ArrayList<MontoTotalVendidoPorMes> cargarMontosTotalesVendidosPorMes(){
        ArrayList<MontoTotalVendidoPorMes> montosTotalesVendidosPorMes = new ArrayList<>();
        String sql = "select *from monto_total_vendido_por_mes";

        try {
            preparedStatement = conexion.prepararSql(sql);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                String mes = resultSet.getString("mes");
                int total = resultSet.getInt("monto_total_vendido");
                MontoTotalVendidoPorMes montoTotalVendidoPorMes = new MontoTotalVendidoPorMes(mes, total);
                montosTotalesVendidosPorMes.add(montoTotalVendidoPorMes);
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar montos totales vendidos por mes: " + e.getMessage());
        }
        return montosTotalesVendidosPorMes;
    } 

    public ArrayList<ProductosMasVendidos> cargarProductosMasVendidos(){
        ArrayList<ProductosMasVendidos> productos = new ArrayList<>();
        String sql = "select * from productos_mas_vendidos";

        try {
            preparedStatement = conexion.prepararSql(sql);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                int cantidad = resultSet.getInt("cantidad");
                ProductosMasVendidos p = new ProductosMasVendidos(nombre, cantidad);
                productos.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar productos mas vendidos: " + e.getMessage());
        }
        return productos;
    } 

    public ArrayList<ProductosMasVendidosPorMes> cargarProductosMasVendidosPorMes(){
        ArrayList<ProductosMasVendidosPorMes> productos = new ArrayList<>();
        String sql = "select * from productos_mas_vendidos_por_mes";

        try {
            preparedStatement = conexion.prepararSql(sql);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                String mes = resultSet.getString("mes");
                String nombre_prod = resultSet.getString("nombre_producto");
                int cantidad = resultSet.getInt("cantidad_vendida");
                int total = resultSet.getInt("monto_total_vendido");
                
                ProductosMasVendidosPorMes p = new ProductosMasVendidosPorMes(mes, nombre_prod, cantidad, total);
                productos.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar productos mas vendidos por mes: " + e.getMessage());
        }
        return productos;
    } 

    
    @Override
    public ArrayList<Farmaceutica> cargarFarmaceuticas() {
        ArrayList<Farmaceutica> farmaceuticas = new ArrayList<>();
        String sql = "SELECT * FROM FARMACEUTICA";

        try {
            preparedStatement = conexion.prepararSql(sql);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id_farmaceutica");
                String nombre = resultSet.getString("nombre");
                String telefono = resultSet.getString("telefono");
                String correo = resultSet.getString("correo_electronico");
                Farmaceutica farmaceutica = new Farmaceutica(id, nombre, telefono, correo);
                farmaceuticas.add(farmaceutica);
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar farmaceuticas: " + e.getMessage());
        }
        return farmaceuticas;
    }

    @Override
    public ArrayList<TipoProducto> cargarTiposProductos() {
        ArrayList<TipoProducto> tiposProductos = new ArrayList<>();
        String sql = "SELECT * FROM TIPO_PRODUCTO";

        try {
            preparedStatement = conexion.prepararSql(sql);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id_tipo_producto");
                String nombre = resultSet.getString("nombre");
                TipoProducto tipoProducto = new TipoProducto(id, nombre);
                tiposProductos.add(tipoProducto);
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar tipos de productos: " + e.getMessage());
        }
        return tiposProductos;
    }

    @Override
    public ArrayList<Producto> cargarProductos(Farmacia miFarmacia) {
        ArrayList<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCTO";

        try {
            preparedStatement = conexion.prepararSql(sql);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id_producto");
                Farmaceutica farmaceutica = miFarmacia.buscarFarmaceuticaPorId(resultSet.getInt("id_farmaceutica"));
                TipoProducto tipoProducto = miFarmacia.buscarTipoProductoPorId(resultSet.getInt("id_tipo_producto"));
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                double precio = resultSet.getDouble("precio");
                int cantidadStock = resultSet.getInt("cantidad_stock");
                Producto producto = new Producto(farmaceutica, tipoProducto, nombre, descripcion, precio, cantidadStock);
                producto.setId(id);
                productos.add(producto);
                System.out.println("producto agregado: " + nombre);
            }
            //conexion.desconectar();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return productos;
}

@Override
public ArrayList<CargoEmpleado> cargarCargosEmpleados() {
                ArrayList<CargoEmpleado> cargosEmpleados = new ArrayList<>();
        String sql = "SELECT * FROM CARGO_EMPLEADO";

        try {
            preparedStatement = conexion.prepararSql(sql);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id_cargo");
                String nombre = resultSet.getString("nombre");
                CargoEmpleado cargoEmpleado = new CargoEmpleado(id, nombre);
                cargosEmpleados.add(cargoEmpleado);
                System.out.println("Cargo empleado agregado: " + nombre);
            }
            //conexion.desconectar();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cargosEmpleados;
    }

    @Override
    public ArrayList<Empleado> cargarEmpleados() {
    ArrayList<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM EMPLEADO";

        try {
            preparedStatement = conexion.prepararSql(sql);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id_empleado");
                String cargo = resultSet.getString("id_cargo");
                String cedula = resultSet.getString("cedula");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                double salario = resultSet.getDouble("salario");
                Date fechaContratacion= resultSet.getDate("fecha_contratacion");
                String clave = resultSet.getString("clave");
                
                Empleado emp = new Empleado(id, nombre, cargo, apellido, salario, fechaContratacion, cedula, clave);
                empleados.add(emp);
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar empleados: " + e.getMessage());
        }
        return empleados;
    }


    @Override
public ArrayList<Cliente> cargarClientes() {
    ArrayList<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";

        try {
            preparedStatement = conexion.prepararSql(sql);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id_cliente");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                String telefono = resultSet.getString("telefono");
                String correo = resultSet.getString("correo_electronico");
                Date fecha_nacimiento = resultSet.getDate("fecha_nacimiento");
                String genero = resultSet.getString("genero");

                Cliente cliente = new Cliente(nombre, apellido, telefono, correo, fecha_nacimiento, genero);
                
                cliente.setId(id);
                clientes.add(cliente);
                System.out.println("Cliente agregado: " + nombre);
            }
            //conexion.desconectar();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return clientes;
    }
    
}
