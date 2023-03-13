package modelo;



import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.datasource.impl.OracleDataSource;

public class OracleJDBCExample {

    public static void main(String[] args) {
        Conexion x = new Conexion();
        x.conectar();
        System.out.println("");
    }
}