package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilitaria.Archivos;

/**
 *
 * @author milton
 */
public class Conexion {
    private Connection con;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private String url;
    private String user;
    private String pass;

    public Connection conectar() {
        String[] credenciales = Archivos.leer("C:/db_wallets/credenciales.rsa").split(";");
        url = credenciales[0];
        user = credenciales[1];
        pass = credenciales[2];
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Open a connection
            System.out.println("Conectando a la base de datos...");
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Conectado a la base de dats oracle");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error al conectar la base de datos");
        }
        this.con = con;
        return con;
    }
    
    public void desconectar() {
        try {
            con.close();
            url = "";
            user = "";
            pass = "";
            System.out.println("Desconectado de la base de datos.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public PreparedStatement prepararSql(String sql) {
        try {
            return con.prepareStatement(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    public ResultSet ejecutarSql (PreparedStatement sqlPreparado) {
        try {
            return sqlPreparado.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
