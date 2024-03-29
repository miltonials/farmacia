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
public class CargoEmpleadoDAO implements CRUD {

    private Conexion conexion = new Conexion();
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public int create(Object cargoEmpleado) {
        CargoEmpleado miCargoEmpleado = (CargoEmpleado) cargoEmpleado;
        String sql = "SELECT farmacia_insertar.insertar_cargo_empleado(?) FROM DUAL";
        int respuesta = 0;
        try {
            conexion.conectar();
            preparedStatement = conexion.prepararSql(sql);
            preparedStatement.setString(1, miCargoEmpleado.getNombre());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                respuesta = resultSet.getInt(1);
                sql = "SELECT id_cargo FROM CARGO_EMPLEADO WHERE nombre = ?";
                preparedStatement = conexion.prepararSql(sql);
                preparedStatement.setString(1, miCargoEmpleado.getNombre());
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    miCargoEmpleado.setId(resultSet.getInt(1));
                }
            }
        } catch (Exception e) {
            System.out.println("Error al crear cargo: " + e.getMessage());
        }
        return respuesta;
    }

    @Override
    public Object read(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(Object cargoEmpleado) {
        int respuesta = 0;
        CargoEmpleado miCargoEmpleado = (CargoEmpleado) cargoEmpleado;
        String sql = "SELECT farmacia_modificar.modificar_cargo_empleado(?,?) from dual";

        try {
            conexion.conectar();
            preparedStatement = conexion.prepararSql(sql);
            preparedStatement.setInt(1, miCargoEmpleado.getId());
            preparedStatement.setString(2, miCargoEmpleado.getNombre());
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
    public int delete(Object cargoEmpleado) {
        int respuesta = 0;
        CargoEmpleado miCargoEmpleado = (CargoEmpleado) cargoEmpleado;
        String sql = "CALL farmacia_eliminar.eliminar_cargo_empleado(?)";

        try {
            conexion.conectar();
            preparedStatement = conexion.prepararSql(sql);
            preparedStatement.setInt(1, miCargoEmpleado.getId());
            preparedStatement.executeQuery();
            
        } catch (Exception e) {
            System.out.println("Error al eliminar cargo de empleados: " + e.getMessage());
        }

        return respuesta;
    }
}
