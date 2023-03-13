/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author milton
 */
public class Conexion {

    private Connection con;
    private String url = "jdbc:oracle:thin:@siuadb1_high?TNS_ADMIN=C:/db_wallets/Wallet_siuadb1";
    private String user = "mbarrera";
    private String pass = "Deodorize.jump.legible1";

    public Connection conectar() {
        String rutaRelativa = "C:/db_wallets/Wallet_siuadb1/tsnames.ora";
        File archivo = new File(rutaRelativa);
        //System.out.println("Ruta abs: " + archivo.getAbsolutePath());
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
        return con;
    }
}
