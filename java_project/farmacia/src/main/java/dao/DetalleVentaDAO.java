package dao;

import java.util.ArrayList;
import interfaces.CRUD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.CargoEmpleado;
import modelo.Conexion;
import modelo.DetalleVenta;
import modelo.Producto;

/**
 *
 * @author milto
 */
public class DetalleVentaDAO implements CRUD {

    private Conexion conexion = new Conexion();
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public int create(Object detalleVenta) {
        DetalleVenta miDetalleVenta = (DetalleVenta) detalleVenta;
        String sql = "SELECT farmacia_insertar.insertar_detalle_venta(?,?,?) FROM DUAL";
        int respuesta = 0;
        try {
            conexion.conectar();
            preparedStatement = conexion.prepararSql(sql);
            preparedStatement.setInt(1, miDetalleVenta.getVenta().getId());
            preparedStatement.setInt(2, miDetalleVenta.getProducto().getId());
            preparedStatement.setInt(3, miDetalleVenta.getCantidad());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                respuesta = resultSet.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error al crear detalle de venta: " + e.getMessage());
        }
        return respuesta;
    }

    @Override
    public Object read(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(Object detalleVenta) {
        int respuesta = 0;
        DetalleVenta miDetalleVenta = (DetalleVenta) detalleVenta;
        String sql = "SELECT farmacia_modificar.modificar_detalle_venta(?,?,?,?) from dual";

        try {
            conexion.conectar();
            preparedStatement = conexion.prepararSql(sql);
            preparedStatement.setInt(1, miDetalleVenta.getVenta().getId());
            preparedStatement.setInt(2, miDetalleVenta.getProducto().getId());
            preparedStatement.setInt(3, miDetalleVenta.getCantidad());
            preparedStatement.setDouble(4, miDetalleVenta.getPrecioUnitario());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                respuesta = resultSet.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar detalle de venta: " + e.getMessage());
        }

        return respuesta;
    }

    @Override
    public int delete(Object detalleVenta) {
        int respuesta = 0;
        DetalleVenta miDetalleVenta = (DetalleVenta) detalleVenta;
        String sql = "CALL farmacia_eliminar.eliminar_detalle_venta(?, ?)";

        try {
            conexion.conectar();
            preparedStatement = conexion.prepararSql(sql);
            preparedStatement.setInt(1, miDetalleVenta.getVenta().getId());
            preparedStatement.setInt(2, miDetalleVenta.getProducto().getId());
            preparedStatement.executeQuery();
            
        } catch (Exception e) {
            System.out.println("Error al eliminar detalle de venta: " + e.getMessage());
        }

        return respuesta;
    }
}
