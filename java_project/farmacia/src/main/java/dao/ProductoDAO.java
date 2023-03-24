package dao;

import java.util.ArrayList;
import interfaces.CRUD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.CargoEmpleado;
import modelo.Conexion;
import modelo.Producto;

/**
 *
 * @author milto
 */
public class ProductoDAO implements CRUD {

    private Conexion conexion = new Conexion();
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public int create(Object producto) {
        Producto miProducto = (Producto) producto;
        //FUNCTION insertar_producto (p_id_farmaceutica NUMBER, p_id_tipo_producto NUMBER, p_nombre VARCHAR2, p_descripcion VARCHAR2, p_precio NUMBER, p_cantidad_stock NUMBER) RETURN NUMBER;
        String sql = "SELECT farmacia_insertar.insertar_producto(?, ?, ?, ?, ?, ?) FROM DUAL";
        int respuesta = 0;
        try {
            conexion.conectar();
            preparedStatement = conexion.prepararSql(sql);
            preparedStatement.setInt(1, miProducto.getFarmaceutica().getId());
            preparedStatement.setInt(2, miProducto.getTipo().getId());
            preparedStatement.setString(3, miProducto.getNombre());
            preparedStatement.setString(4, miProducto.getDescripcion());
            preparedStatement.setDouble(5, miProducto.getPrecio());
            preparedStatement.setInt(6, miProducto.getCantidadStock());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                respuesta = resultSet.getInt(1);
                sql = "SELECT id_producto FROM PRODUCTO WHERE id_farmaceutica = ? AND id_tipo_producto = ? AND nombre = ? AND descripcion = ? AND precio = ? AND cantidad_stock = ?";
                preparedStatement = conexion.prepararSql(sql);
                preparedStatement.setInt(1, miProducto.getFarmaceutica().getId());
                preparedStatement.setInt(2, miProducto.getTipo().getId());
                preparedStatement.setString(3, miProducto.getNombre());
                preparedStatement.setString(4, miProducto.getDescripcion());
                preparedStatement.setDouble(5, miProducto.getPrecio());
                preparedStatement.setInt(6, miProducto.getCantidadStock());
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    miProducto.setId(resultSet.getInt(1));
                }
            }
        } catch (Exception e) {
            System.out.println("Error al crear producto: " + e.getMessage());
        }
        return respuesta;
    }

    @Override
    public Object read(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(Object producto) {
        int respuesta = 0;
        Producto miProducto = (Producto) producto;
        String sql = "SELECT farmacia_modificar.modificar_producto(?, ?, ?, ?, ?, ?, ?) from dual";
        //p_id_producto IN NUMBER, p_id_farmaceutica NUMBER, p_id_tipo_producto NUMBER, p_nombre VARCHAR2, p_descripcion VARCHAR2, p_precio NUMBER, p_cantidad_stock NUMBER

        try {
            conexion.conectar();
            preparedStatement = conexion.prepararSql(sql);
            preparedStatement.setInt(1, miProducto.getId());
            preparedStatement.setInt(2, miProducto.getFarmaceutica().getId());
            preparedStatement.setInt(3, miProducto.getTipo().getId());
            preparedStatement.setString(4, miProducto.getNombre());
            preparedStatement.setString(5, miProducto.getDescripcion());
            preparedStatement.setDouble(6, miProducto.getPrecio());
            preparedStatement.setInt(7, miProducto.getCantidadStock());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                respuesta = resultSet.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
        }

        return respuesta;
    }

    @Override
    public int delete(Object producto) {
        int respuesta = 0;
        Producto miProducto = (Producto) producto;
        String sql = "CALL farmacia_eliminar.eliminar_producto(?)";

        try {
            conexion.conectar();
            preparedStatement = conexion.prepararSql(sql);
            preparedStatement.setInt(1, miProducto.getId());
            preparedStatement.executeQuery();
            
        } catch (Exception e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        }

        return respuesta;
    }
}
