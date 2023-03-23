package dao;

import java.util.ArrayList;
import interfaces.CRUD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Conexion;
import modelo.Farmaceutica;

/**
 *
 * @author milto
 */
public class FarmaceuticaDAO implements CRUD {
    private Conexion conexion = new Conexion();
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public int create(Object farmaceutica) {
       Farmaceutica miFarmaceutica = (Farmaceutica) farmaceutica;
        //FUNCTION insertar_producto (p_id_farmaceutica NUMBER, p_id_tipo_producto NUMBER, p_nombre VARCHAR2, p_descripcion VARCHAR2, p_precio NUMBER, p_cantidad_stock NUMBER) RETURN NUMBER;
        String sql = "SELECT farmacia_insertar.insertar_farmaceutica(?, ?, ?, ?) FROM DUAL";
        int respuesta = 0;
        try {
            conexion.conectar();
            preparedStatement = conexion.prepararSql(sql);
            preparedStatement.setInt(1, miFarmaceutica.getId());
            preparedStatement.setString(2, miFarmaceutica.getNombre());
            preparedStatement.setString(3, miFarmaceutica.getTelefono());
            preparedStatement.setString(4, miFarmaceutica.getCorreoElectronico());
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                respuesta = resultSet.getInt(1);
                sql = "SELECT id_farmaceutica FROM FARMACEUTICA WHERE id_farmaceutica = ? AND nombre = ? AND telefono = ? AND correo_electronico = ?";
                preparedStatement = conexion.prepararSql(sql);
                preparedStatement.setInt(1, miFarmaceutica.getId());
                preparedStatement.setString(2, miFarmaceutica.getNombre());
                preparedStatement.setString(3, miFarmaceutica.getTelefono());
                preparedStatement.setString(4, miFarmaceutica.getCorreoElectronico());
                resultSet = preparedStatement.executeQuery();
                
                if (resultSet.next()) {
                    miFarmaceutica.setId(resultSet.getInt(1));
                }
            }
        } catch (Exception e) {
            System.out.println("Error al crear la Farmaceutica: " + e.getMessage());
        }
        return respuesta;
    }

    @Override
    public int read(Object objecto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
