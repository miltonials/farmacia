/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import utilitaria.Archivos;

/**
 *
 * @author milton
 */
public class Conexion {

    private Connection con;
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
        return con;
    }
}
