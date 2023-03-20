package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author milto
 */
public class EmpleadoDAO implements CRUD {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public int validar(Empleado pEmpleado) {
        String sql = "SELECT * FROM empleado WHERE nombre = ? AND clave = ?";
        System.out.println(pEmpleado.getNombre());
        System.out.println(pEmpleado.getClave());
        int resultado = 0;

        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1,  pEmpleado.getNombre());
            ps.setString(2, pEmpleado.getClave());
            
            rs = ps.executeQuery();
            while (rs.next()) {
                
                resultado +=  1;
                pEmpleado.setId(rs.getInt("id_empleado"));
                pEmpleado.setNombre(rs.getString("nombre"));
                pEmpleado.setIdCargo(rs.getString("id_cargo"));
                pEmpleado.setApellido(rs.getString("apellido"));
                pEmpleado.setSalario(rs.getFloat("salario"));
                pEmpleado.setFechaContratacion(rs.getDate("fecha_contratacion"));
                pEmpleado.setCedula(rs.getString("cedula"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultado;
    }
    
    public void cerrarConexion(Empleado pEmpleado) {
        try {
            pEmpleado = null;
            this.con.close();
            System.out.println("Desconectado de la base de datos");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public boolean create(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean read(Object objecto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
