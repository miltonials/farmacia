package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import interfaces.CRUD;
import java.util.ArrayList;
import modelo.Conexion;
import modelo.Empleado;

/**
 *
 * @author milto
 */
public class EmpleadoDAO implements CRUD {

    private Conexion conexion = new Conexion();
    private ResultSet rs;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public int validar(Empleado pEmpleado) {
        String sql = "SELECT * FROM empleado WHERE nombre = ? AND clave = ?";
        int resultado = 0;
        
        try {
            conexion.conectar();
            PreparedStatement ps = conexion.prepararSql(sql);
            ps.setString(1, pEmpleado.getNombre());
            ps.setString(2, pEmpleado.getClave());
            rs = conexion.ejecutarSql(ps);
            
            while (rs.next()) {
                resultado += 1;
                pEmpleado.setId(rs.getInt("id_empleado"));
                pEmpleado.setNombre(rs.getString("nombre"));
                pEmpleado.setIdCargo(rs.getString("id_cargo"));
                pEmpleado.setApellido(rs.getString("apellido"));
                pEmpleado.setSalario(rs.getFloat("salario"));
                pEmpleado.setFechaContratacion(rs.getDate("fecha_contratacion"));
                pEmpleado.setCedula(rs.getString("cedula"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return resultado;
    }

    public void cerrarConexion(Empleado pEmpleado) {
        pEmpleado = null;
        conexion.desconectar();
        System.out.println("Desconectado de la base de datos");
    }

    @Override
    public int create(Object emp) {
        Empleado miEmp = (Empleado) emp;
        
        String sql = "SELECT farmacia_insertar.insertar_empleado (?, ?, ?, ?, ?, ?, ?) FROM DUAL";
        int respuesta = 0;
        try {
            conexion.conectar();
            preparedStatement = conexion.prepararSql(sql);
            preparedStatement.setString(1, miEmp.getCedula());
            preparedStatement.setInt(2, Integer.parseInt(miEmp.getIdCargo())); 
            preparedStatement.setString(3, miEmp.getNombre()); 
            preparedStatement.setString(4, miEmp.getApellido()); 
            preparedStatement.setDouble(5, miEmp.getSalario()); 
            preparedStatement.setDate(6, new java.sql.Date(miEmp.getFechaContratacion().getTime())); 
            preparedStatement.setString(7, miEmp.getClave()); 
            
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                respuesta = resultSet.getInt(1);
                sql = "SELECT id_empleado FROM EMPLEADO WHERE cedula = ?";
                preparedStatement = conexion.prepararSql(sql);
                preparedStatement.setString(1, miEmp.getCedula());
                preparedStatement.setInt(2, Integer.parseInt(miEmp.getIdCargo())); 
                preparedStatement.setString(3, miEmp.getNombre()); 
                preparedStatement.setString(4, miEmp.getApellido()); 
                preparedStatement.setDouble(5, miEmp.getSalario()); 
                preparedStatement.setDate(6, new java.sql.Date(miEmp.getFechaContratacion().getTime())); 
                preparedStatement.setString(7, miEmp.getClave());
                
                resultSet = preparedStatement.executeQuery();
                                
                if (resultSet.next()) {
                    miEmp.setId(resultSet.getInt(1));
                }
            }
        } catch (Exception e) {
            System.out.println("Error al crear empleado: " + e.getMessage());
        }
        return respuesta;
    }


    @Override
    public Object read(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(Object empleado) {
        int respuesta = 0;
        Empleado miEmpleado = (Empleado) empleado;
        String sql = "SELECT farmacia_modificar.modificar_empleado(?, ?, ?, ?, ?, ?, ?) from dual";
        //p_id_empleado IN NUMBER, p_id_farmaceutica NUMBER, p_id_tipo_empleado NUMBER, p_nombre VARCHAR2, p_descripcion VARCHAR2, p_precio NUMBER, p_cantidad_stock NUMBER

        try {
            conexion.conectar();
            preparedStatement = conexion.prepararSql(sql);
            preparedStatement.setInt(1, miEmpleado.getId());
            preparedStatement.setString(2, miEmpleado.getCedula());
            preparedStatement.setInt(3, Integer.parseInt(miEmpleado.getIdCargo())); 
            preparedStatement.setString(4, miEmpleado.getNombre()); 
            preparedStatement.setString(5, miEmpleado.getApellido()); 
            preparedStatement.setDate(6, new java.sql.Date(miEmpleado.getFechaContratacion().getTime())); 
            preparedStatement.setDouble(7, miEmpleado.getSalario()); 
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                respuesta = resultSet.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar empleado: " + e.getMessage());
        }

        return respuesta;
    }

    @Override
    public int delete(Object empleado) {
        int respuesta = 0;
        Empleado miEmpleado = (Empleado) empleado;
        String sql = "CALL farmacia_eliminar.eliminar_empleado(?)";

        try {
            conexion.conectar();
            preparedStatement = conexion.prepararSql(sql);
            preparedStatement.setInt(1, miEmpleado.getId());
            preparedStatement.executeQuery();
            
        } catch (Exception e) {
            System.out.println("Error al eliminar empleado: " + e.getMessage());
        }

        return respuesta;
    }
}
