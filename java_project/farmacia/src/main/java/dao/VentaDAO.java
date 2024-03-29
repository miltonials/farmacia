package dao;

import java.util.ArrayList;
import interfaces.CRUD;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Conexion;
import modelo.Venta;

/**
 *
 * @author Kevin Salazar
 */
public class VentaDAO implements CRUD {
    private Conexion conexion = new Conexion();
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public int create(Object venta) {
        Venta miVenta = (Venta) venta;
        String sql = "SELECT farmacia_insertar.insertar_venta (?, ?, ?, ?) FROM DUAL";
        int respuesta = 0;
        try {
            conexion.conectar();
            preparedStatement = conexion.prepararSql(sql);
            preparedStatement.setDate(1, new java.sql.Date(miVenta.getFecha_emision().getTime()));
            preparedStatement.setInt(2, miVenta.getCliente().getId());
            preparedStatement.setInt(3, miVenta.getEmpleado().getId());
            preparedStatement.setDouble(4, miVenta.getTotal_venta());
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                respuesta = resultSet.getInt(1);
                sql = "SELECT id_venta FROM VENTA WHERE Fecha_emision = ? AND ID_cliente = ? AND ID_Empleado = ? AND Total_venta = ?";
                preparedStatement = conexion.prepararSql(sql);
                preparedStatement.setDate(1, new java.sql.Date(miVenta.getFecha_emision().getTime()));
                preparedStatement.setInt(2, miVenta.getCliente().getId());
                preparedStatement.setInt(3, miVenta.getEmpleado().getId());
                preparedStatement.setDouble(4, miVenta.getTotal_venta());
                resultSet = preparedStatement.executeQuery();
                
                if (resultSet.next()) {
                    miVenta.setId(resultSet.getInt(1));
                }
            }
        } catch (Exception e) {
            System.out.println("Error al crear venta: " + e.getMessage());
        }
        return respuesta;
    }

    @Override
    public Object read(int Id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(Object venta) {
        int respuesta = 0;
        Venta miVenta = (Venta) venta;
        String sql = "SELECT farmacia_modificar.modificar_venta(?, ?, ?, ?) from dual";
        
        try {
            conexion.conectar();
            preparedStatement = conexion.prepararSql(sql);
            preparedStatement.setInt(1, miVenta.getId());
            preparedStatement.setInt(2, miVenta.getCliente().getId());
            preparedStatement.setInt(3, miVenta.getEmpleado().getId());
            preparedStatement.setDate(4, new java.sql.Date(miVenta.getFecha_emision().getTime()));
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                respuesta = resultSet.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar venta: " + e.getMessage());
        }

        return respuesta;
    }

    @Override
    public int delete(Object venta) {
        int respuesta = 0;
        Venta miVenta = (Venta) venta;
        String sql = "CALL farmacia_eliminar.eliminar_venta(?)";

        try {
            conexion.conectar();
            preparedStatement = conexion.prepararSql(sql);
            preparedStatement.setInt(1, miVenta.getId());
            preparedStatement.executeQuery();
            
        } catch (Exception e) {
            System.out.println("Error al eliminar venta: " + e.getMessage());
        }

        return respuesta;
    }
}