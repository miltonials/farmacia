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

        // JDBC driver name and database URL
        final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
        final String tnsPath = "src/main/java/Wallet_siuadb1";
        final String DB_URL = "jdbc:oracle:thin:@siuadb1_high?TNS_ADMIN=src/main/java/Wallet_siuadb1";

        // Database credentials
        final String USER = "mbarrera";
        final String PASS = "Deodorize.jump.legible1";

        Connection conn = null;
        
        Properties info = new Properties();     
        info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, USER);
        info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, PASS);          
        info.put(OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH, "20");    

        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            OracleDataSource ods = new OracleDataSource();
            ods.setURL(DB_URL);    
            ods.setConnectionProperties(info);
            
            OracleConnection connection = (OracleConnection) ods.getConnection();
            
            DatabaseMetaData dbmd = connection.getMetaData();       
            System.out.println("Driver Name: " + dbmd.getDriverName());
            System.out.println("Driver Version: " + dbmd.getDriverVersion());
            // Print some connection properties
            System.out.println("Default Row Prefetch Value is: " + 
               connection.getDefaultRowPrefetch());
            System.out.println("Database Username is: " + connection.getUserName());
            System.out.println();
            
            // Do something with the connection...
            System.out.println("CONECTADO!!!?");
            System.out.println("");
            // Close the connection
            conn.close();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // Finally block to close resources
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}