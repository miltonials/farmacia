/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author milto
 */
public class EmpleadoDAO implements Validar {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public int validar(Empleado pEmpleado) {
        String sql = "SELECT * FROM empleado WHERE nombre = ? AND clave = ?";
        int resultado = 0;

        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1,  pEmpleado.getNombre());
            ps.setString(2, pEmpleado.getCargo());
            rs = ps.executeQuery();

            while (rs.next()) {
                resultado +=  1;
                pEmpleado.setId(rs.getInt("id_empleado"));
                pEmpleado.setNombre(rs.getString("nombre"));
                pEmpleado.setCargo(rs.getString("cargo"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultado;
    }
    
    public void cerrarConexion() {
        try {
            this.con.close();
            System.out.println("Desconectado de la base de datos");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
