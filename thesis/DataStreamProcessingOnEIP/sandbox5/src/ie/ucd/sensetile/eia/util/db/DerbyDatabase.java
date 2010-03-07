package ie.ucd.sensetile.eia.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DerbyDatabase {
    private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private static String protocol = "jdbc:derby:"; 
    
    static {
    	loadDriver();
    }
    
    private DatabaseConfig config = null;
    
    public DerbyDatabase(DatabaseConfig cfg){
    	this.config = cfg;
    }
    
    public Connection getNewConnection() {
    	Properties props = new Properties(); // connection properties
    	try {
    		Connection c = DriverManager.getConnection(protocol + config.getDbName() + ";create=true", props);
    		return c;
    	} catch (SQLException sqle) {
    		sqle.printStackTrace();
    		return null;
    	}
    }
    
    private static void loadDriver() {
        try {
            Class.forName(driver).newInstance();
            System.out.println("Loaded the appropriate driver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println("\nUnable to load the JDBC driver " + driver);
            System.err.println("Please check your CLASSPATH.");
            cnfe.printStackTrace(System.err);
        } catch (InstantiationException ie) {
            System.err.println(
                        "\nUnable to instantiate the JDBC driver " + driver);
            ie.printStackTrace(System.err);
        } catch (IllegalAccessException iae) {
            System.err.println(
                        "\nNot allowed to access the JDBC driver " + driver);
            iae.printStackTrace(System.err);
        }
    }
}
