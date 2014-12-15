package oracle.functions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectarBD {
    public ConectarBD() {
        super();
    }
    public static Connection getConnection() throws ClassNotFoundException,
                                                    SQLException {
        String driverName = "oracle.jdbc.OracleDriver";
        Class.forName(driverName);
        String cadenaConexion= "jdbc:oracle:thin:@127.0.0.1:1521:xe";
        String usuario= "fbaeza";
        String password = "fbaeza";
        return DriverManager.getConnection(cadenaConexion, usuario, password);           
    }
}
