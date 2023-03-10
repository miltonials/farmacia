/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Kevin Salazar
 */
public class EmpleadoDAO implements Validar{
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r = 0;
    @Override
    public int validar(Empleado emp) {
        String sql = "select * from empleado where nombre=? and contrasena=?";
        try{
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, emp.getNombre());
            ps.setString(2, emp.getPssw());
            rs = ps.executeQuery();
            while(rs.next()){
                r++;
                emp.setNombre(rs.getString("nombre"));
                emp.setPssw(rs.getString("contrasena"));
            }
            if(r==1){
                return 1;
                
            }else{
                return 0;
            }
        }catch(Exception e){
            return 0;
        }
    }
    
}
