package oracle.functions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Timestamp;

import java.util.Calendar;
import java.util.TimeZone;

public class Comentario {
    public Comentario() {
        super();
    }
    public static void addComentario(String artiId, String user, String mensaje) throws ClassNotFoundException,
                                                            SQLException {
        String driverName = "oracle.jdbc.OracleDriver";
        Class.forName(driverName);
        String cadenaConexion= "jdbc:oracle:thin:@127.0.0.1:1521:xe";
        String usuario= "fbaeza";
        String password = "fbaeza";
        Connection conn = DriverManager.getConnection(cadenaConexion, usuario, password);   
        String query3="select * from vendedor where rut=?"; 
        PreparedStatement st3 = conn.prepareStatement(query3);
        ResultSet rs3;        
        st3.setString(1, user);
        rs3 = st3.executeQuery();  
        
        if(!rs3.next()){
            conn.close();
            return ;
        }
        String query2="select * from conectado where vendedor_rut=?"; 
        PreparedStatement st2 = conn.prepareStatement(query2);
        ResultSet rs2;        
        st2.setString(1, user);
        rs2 = st2.executeQuery();  
        
        if(!rs2.next()){
            conn.close();
            return ;
        }
            String query="INSERT INTO comentario (vendedor_rut,articulo_codigo,mensaje,fecha) VALUES (?,?, ?,?)"; 
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1,user);
            st.setString(2,artiId);
            if(mensaje.length()>360){
                mensaje = mensaje.substring(0, 360);
            }
            st.setString(3,mensaje);
            java.util.Calendar cal = Calendar.getInstance();
            cal.setTimeZone(TimeZone.getTimeZone("GMT-2:00"));
            st.setTimestamp(4, getCurrentTimeStamp(),cal);
            // execute insert SQL stetement
            st.executeUpdate();
            conn.close();
            return ;
    }
    private static Timestamp getCurrentTimeStamp() {
     
        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());
     
    }
}
