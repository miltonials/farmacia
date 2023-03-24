package dao;

import interfaces.CRUD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Conexion;
import modelo.TipoProducto;

/**
 *
 * @author Kevin Salazar
 */
public class TipoProductoDAO implements CRUD{

    private Conexion conexion = new Conexion();
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public int create(Object tipo) {
        TipoProducto miTipo = (TipoProducto) tipo;
        String sql = "SELECT farmacia_insertar.insertar_tipo_producto (?) FROM DUAL";
        int respuesta = 0;
        try {
            conexion.conectar();
            preparedStatement = conexion.prepararSql(sql);
            preparedStatement.setString(1, miTipo.getNombre());
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                respuesta = resultSet.getInt(1);
                sql = "SELECT id_tipo_producto FROM TIPO_PRODUCTO WHERE nombre = ?";
                preparedStatement = conexion.prepararSql(sql);
                preparedStatement.setString(1, miTipo.getNombre());
                resultSet = preparedStatement.executeQuery();
                
                if (resultSet.next()) {
                    miTipo.setId(resultSet.getInt(1));
                }
            }
        } catch (Exception e) {
            System.out.println("Error al crear tipo producto: ");
            e.printStackTrace();
        }
        return respuesta;
    }

    @Override
    public Object read(int Id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(Object tipoProducto) {
        int respuesta = 0;
        TipoProducto miTipo = (TipoProducto) tipoProducto;
        String sql = "SELECT farmacia_modificar.modificar_tipo_producto(?, ?) from dual";
        
        try {
            conexion.conectar();
            preparedStatement = conexion.prepararSql(sql);
            preparedStatement.setInt(1, miTipo.getId());
            preparedStatement.setString(2, miTipo.getNombre());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                respuesta = resultSet.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar tipo producto: " + e.getMessage());
        }

        return respuesta;
    }

    @Override
    public int delete(Object tipoProducto) {
        int respuesta = 0;
        TipoProducto miTipo = (TipoProducto) tipoProducto;
        String sql = "CALL farmacia_eliminar.eliminar_tipo_producto(?)";

        try {
            conexion.conectar();
            preparedStatement = conexion.prepararSql(sql);
            preparedStatement.setInt(1, miTipo.getId());
            preparedStatement.executeQuery();
            
        } catch (Exception e) {
            System.out.println("Error al eliminar tipo producto: " + e.getMessage());
        }

        return respuesta;
    }
}
