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
    public int create(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
