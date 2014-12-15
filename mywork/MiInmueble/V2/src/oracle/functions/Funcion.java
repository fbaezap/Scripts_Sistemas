package oracle.functions;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import javax.xml.bind.DatatypeConverter;


public class Funcion {
    Connection conexion;
    
    public Funcion() {
        try{
            String driverName = "oracle.jdbc.OracleDriver";
            Class.forName(driverName);
            String cadenaConexion= "jdbc:oracle:thin:@127.0.0.1:1521:xe";
            String usuario= "fbaeza";
            String password = "fbaeza";
            conexion = DriverManager.getConnection(cadenaConexion, usuario, password);        
        }catch(Exception e){
            e.printStackTrace();    
        }
    }
    
    public Connection getConexion(){
        return conexion;
    }
    
    public List<List<String>> findArticulos() throws SQLException{
        List<CampoValor> campovalor;
        campovalor = new ArrayList<CampoValor>();
        return findArticulos(campovalor);
    }
    
    public List<List<String>> findArticulos(String c, String v) throws SQLException {
        List<CampoValor> campovalor = new ArrayList<CampoValor>();
        campovalor.add(new CampoValor(c,v));
        return findArticulos(campovalor);
    }
    
    public List<List<String>> findArticulos(String c, String o, String v) throws SQLException {
        List<CampoValor> campovalor = new ArrayList<CampoValor>();
        campovalor.add(new CampoValor(c,o,v));
        return findArticulos(campovalor);
    }
    
    public List<List<String>> findArticulos(List<CampoValor> campovalor) throws SQLException{
        List<String> mostrar = new ArrayList<String>();
        mostrar.add("titulo");
        mostrar.add("tipo");
        mostrar.add("calificacion");
        return findArticulos(campovalor,mostrar);
    }
    public List<List<String>> findArticulos(List<CampoValor> campovalor, List<String> mostrar) throws SQLException{
        return findArticulos(campovalor,mostrar,false);
    }
    public List<List<String>> findArticulos(List<CampoValor> campovalor, List<String> mostrar,boolean sindetalle) throws SQLException{        
        List<List<String>> salidaf = new ArrayList<List<String>>();
        List<String> salida;
        String query="select * from Articulo"; 
        if(!campovalor.isEmpty()){
            query+=" where ";
            for(CampoValor c : campovalor){
                query+=c.getCampo()+" "+c.getOperacion()+" "+c.getValor()+" and ";
            }
            query+="1=1";
        }
        PreparedStatement st = conexion.prepareStatement(query);        
        ResultSet rs = st.executeQuery();
                                
        String query2="select * from Fotos where articulo_codigo = ?"; 
        PreparedStatement st2 = conexion.prepareStatement(query2);
        ResultSet rs2;
             
        while (rs.next()) {
            salida = new ArrayList<String>();            
            st2.setInt(1, rs.getInt("codigo"));
            rs2 = st2.executeQuery();
            String temp = "<a href=\"detalle.jsp?artId="+rs.getInt("codigo")+"\">";
            if(rs2.next()){                   
                temp+=(muestraImagen(rs2.getInt("photos_imageid")));
            }else{
                temp+=("<img src= \"Recursos/Sin_imagen_disponible.jpg\" width = \"200\" id=\"imagen0\" />");
            }
            temp+="</a>";
        
            salida.add(temp);
            for(String m : mostrar){
                salida.add(rs.getString(m));
            }
            if (!sindetalle){
                salida.add("<a href=\"detalle.jsp?artId="+rs.getInt("codigo")+"\">Ver Detalle</a>");
            }  
            salidaf.add(salida);
        }
         
        return salidaf;
    }

    public List<ArticuloDetalle> findArticulosDetalle(List<CampoValor> campovalor, List<String> mostrar,boolean sindetalle) throws SQLException{
        List<ArticuloDetalle> salidaf = new ArrayList<ArticuloDetalle>();
        ArticuloDetalle salida;
        String query="select * from Articulo"; 
        if(!campovalor.isEmpty()){
            query+=" where ";
            for(CampoValor c : campovalor){
                query+=c.getCampo()+" "+c.getOperacion()+" "+c.getValor()+" and ";
            }
            query+="1=1";
        }
        PreparedStatement st = conexion.prepareStatement(query);        
        ResultSet rs = st.executeQuery();
                                
        String query2="select * from Fotos where articulo_codigo = ?"; 
        PreparedStatement st2 = conexion.prepareStatement(query2);
        ResultSet rs2;
        
        String query3="select * from comentario where articulo_codigo = ?"; 
        PreparedStatement st3 = conexion.prepareStatement(query3);
        ResultSet rs3;            
        
        while (rs.next()) {
            salida = new ArticuloDetalle();
            st2.setInt(1, rs.getInt("codigo"));
            rs2 = st2.executeQuery();
            if(rs2.next()){                   
                salida.addImagen(muestraImagenDetalle(rs2.getInt("photos_imageid")));
                while(rs2.next()){       
                    salida.addImagen(muestraImagenDetalle(rs2.getInt("photos_imageid")));
                }
            }else{
                salida.addImagen("<img src= \"Recursos/Sin_imagen_disponible.jpg\" id=\"imagen0\" width = \"200\" onclick = \"cambiaGrande('Recursos/Sin_imagen_disponible.jpg')\" />");
            }
            for(String m : mostrar){
                salida.addTexto(rs.getString(m));
            }
            if (!sindetalle){
                salida.addTexto("<a href=\"detalle.jsp?artId="+rs.getInt("codigo")+"\">Ver Detalle</a>");
            }  
            
            System.out.println("Inicio Comentarios");
            System.out.println(rs.getInt("codigo"));
            st3.setInt(1, rs.getInt("codigo"));
            rs3 = st3.executeQuery();
            List<String> coment;
            while(rs3.next()){               
                coment = new ArrayList<String>();
                System.out.println(rs3.getString("vendedor_rut"));
                coment.add(findNombre(rs3.getString("vendedor_rut")));
                coment.add(rs3.getString("mensaje"));                
                
                java.util.Calendar cal = Calendar.getInstance();
                cal.setTimeZone(TimeZone.getTimeZone("UTC"));
                java.sql.Timestamp ts = rs3.getTimestamp("fecha",cal);                                
                coment.add(haceCuanto(ts));
                
                salida.addComentario(coment);
            }
            salidaf.add(salida);
        }
         
        return salidaf;
    }

    public String muestraImagen(int imagenid) throws SQLException {
        Out out = new Out();
        String query3="select * from Photos where imageid=?"; 
        PreparedStatement st3 = conexion.prepareStatement(query3);
        ResultSet rs3;
        
        st3.setInt(1, imagenid);
        rs3 = st3.executeQuery();          
        if(rs3.next()){
            out.print(rs3.getInt("imageid"));
            Blob bodyOut = (rs3.getBlob("image"));
            int length = (int) bodyOut.length();
            byte[] body = bodyOut.getBytes(1, length);
            String imageBase64 = DatatypeConverter.printBase64Binary(body);
            String imageSrc = "data:image/"+rs3.getString("type")+";base64,"+imageBase64;
            out.print("<img src= \""+ imageSrc +"\" id=\"imagen"+rs3.getInt("imageid")+"\" width = \"200\"/>");
        }
        return out.salidaFinal();
    }
    
    public String muestraImagenDetalle(int imagenid) throws SQLException {
        Out out = new Out();
        String query3="select * from Photos where imageid=?"; 
        PreparedStatement st3 = conexion.prepareStatement(query3);
        ResultSet rs3;
        
        st3.setInt(1, imagenid);
        rs3 = st3.executeQuery();          
        if(rs3.next()){
            out.print(rs3.getInt("imageid"));
            Blob bodyOut = (rs3.getBlob("image"));
            int length = (int) bodyOut.length();
            byte[] body = bodyOut.getBytes(1, length);
            String imageBase64 = DatatypeConverter.printBase64Binary(body);
            String imageSrc = "data:image/"+rs3.getString("type")+";base64,"+imageBase64;
            out.print("<img src= \""+ imageSrc +"\" id=\"imagen"+rs3.getInt("imageid")+"\" width = \"200\" onclick = \"cambiaGrande('imagen"+imageSrc+"')\" />");
        }
        return out.salidaFinal();
    }
    
    public static boolean Chequea(String Usuario, String Clave) throws Exception {
        Connection conn = null;
        ResultSet result = null;
        PreparedStatement statement = null;
        try {            
            String driverName = "oracle.jdbc.OracleDriver";
            Class.forName(driverName);
            String cadenaConexion= "jdbc:oracle:thin:@127.0.0.1:1521:xe";
            String usuario= "fbaeza";
            String password = "fbaeza";
            conn = DriverManager.getConnection(cadenaConexion, usuario, password);   
            statement = conn.prepareStatement("SELECT * FROM vendedor WHERE rut = '"+Usuario+"' and contraseña = '"+Clave+"'");
            result = statement.executeQuery();
            if(result.next()){
                return true;
            }else{
                return false;
            }
        } catch(SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            if(result != null)
                    try {
                        result.close();
                    } catch(SQLException sql) {
                        throw new Exception(sql);
                    }
            if(statement != null)
                    try {
                        statement.close();
                    } catch(SQLException sql) {
                        throw new Exception(sql);
                    }
            if(conn != null)
                try {
                    conn.close();
                } catch(SQLException sql) {
                    throw new Exception(sql);
                }
        }
    }
    
    public static String conectaUsuario(String rut, String ip) throws Exception{
        String driverName = "oracle.jdbc.OracleDriver";
        Class.forName(driverName);
        String cadenaConexion= "jdbc:oracle:thin:@127.0.0.1:1521:xe";
        String usuario= "fbaeza";
        String password = "fbaeza";
        Connection conn = DriverManager.getConnection(cadenaConexion, usuario, password);   
        String query3="select * from conectado where vendedor_rut=?"; 
        PreparedStatement st3 = conn.prepareStatement(query3);
        ResultSet rs3;        
        st3.setString(1, rut);
        rs3 = st3.executeQuery();  
        
        if(rs3.next()){
            conn.close();
            return "Ya esta conectado el usuario";
        }
        String query2="select * from conectado where ip=?"; 
        PreparedStatement st2 = conn.prepareStatement(query2);
        ResultSet rs2;        
        st2.setString(1, ip);
        rs2 = st2.executeQuery();  
        
        if(rs2.next()){
            conn.close();
            return "La ip esta ocupada";
        }
            String query="INSERT INTO conectado (vendedor_rut,ip,horainicio) VALUES (?, ?,?)"; 
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1,rut);
            st.setString(2,ip);
            java.util.Calendar cal = Calendar.getInstance();
            cal.setTimeZone(TimeZone.getTimeZone("UTC"));
            st.setTimestamp(3, getCurrentTimeStamp(),cal);
            // execute insert SQL stetement
            st.executeUpdate();
            conn.close();
            return "Conectado";
        
    }
    
    public void cerrarConexion() throws SQLException{        
        conexion.close();
    }    
    public static String userByIp(String ip) throws ClassNotFoundException,
                                                    SQLException {
        String driverName = "oracle.jdbc.OracleDriver";
        Class.forName(driverName);
        String cadenaConexion= "jdbc:oracle:thin:@127.0.0.1:1521:xe";
        String usuario= "fbaeza";
        String password = "fbaeza";
        Connection conn = DriverManager.getConnection(cadenaConexion, usuario, password);   
        String query3="select * from conectado where ip=?"; 
        PreparedStatement st3 = conn.prepareStatement(query3);
        ResultSet rs3;        
        st3.setString(1, ip);
        rs3 = st3.executeQuery();  
        
        if(rs3.next()){
            String rut = rs3.getString("vendedor_rut");
            conn.close();
            return rut;
        }
        else{
            conn.close();
            return null;
        }
    }
    public static String desconectaUsuario(String rut){
        try{
            String driverName = "oracle.jdbc.OracleDriver";
            Class.forName(driverName);
            String cadenaConexion= "jdbc:oracle:thin:@127.0.0.1:1521:xe";
            String usuario= "fbaeza";
            String password = "fbaeza";
            Connection conn = DriverManager.getConnection(cadenaConexion, usuario, password);   
            
            String query="DELETE FROM conectado WHERE vendedor_rut= ?"; 
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1,rut);
            st.executeUpdate();
            conn.close();
            return "Borrado con Exito";
            
        }catch(Exception e){
            e.printStackTrace();
            return "No se pudo borrar";
        }
    }
    public String findNombre(String rut) throws SQLException{
        String query4="select * from vendedor where rut = ?"; 
        PreparedStatement st4 = conexion.prepareStatement(query4);
        ResultSet rs4;
        st4.setString(1, rut);
        rs4 = st4.executeQuery();
        
        String query5; 
        PreparedStatement st5;
        ResultSet rs5;
        if(rs4.next()){
            System.out.println(rs4.getString("tipo"));
            if (rs4.getString("tipo").equalsIgnoreCase("Persona")){
                query5="select * from persona where vendedor_rut = ?"; 
                st5 = conexion.prepareStatement(query5);
                st5.setString(1,rs4.getString("rut"));
                rs5 = st5.executeQuery();
                if(rs5.next()){
                    return (rs5.getString("nombre")+" "+rs5.getString("apellidos"));
                }else{
                    return "Sin nombre";
                }
            }else if (rs4.getString("tipo").equalsIgnoreCase("Corredor")){
                query5="select * from corredor where vendedor_rut = ?"; 
                st5 = conexion.prepareStatement(query5);
                st5.setString(1,rs4.getString("rut"));
                rs5 = st5.executeQuery();
                if(rs5.next()){
                    return (rs5.getString("nombre")+" "+rs5.getString("apellidos"));
                }else{
                    return "Sin nombre";
                }
            }else if (rs4.getString("tipo").equalsIgnoreCase("Inmobiliaria")){
                query5="select * from inmobiliaria where vendedor_rut = ?"; 
                st5 = conexion.prepareStatement(query5);
                st5.setString(1,rs4.getString("rut"));
                rs5 = st5.executeQuery();
                if(rs5.next()){
                    return (rs5.getString("nombre"));
                }else{
                    return "Sin nombre";
                }
            }else{
                return "Sin nombre";
            }
        }else{
            return "Sin nombre";
        }
        
    }
    
    public String haceCuanto(Timestamp ts){
        Calendar startCalendar = new GregorianCalendar();
        startCalendar.setTimeInMillis(ts.getTime());
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTimeZone(TimeZone.getTimeZone("GMT-2:00"));
        

        int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        if(diffYear>0){
            return "Hace "+diffYear+" año(s)";
        }
        int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
        if(diffMonth>0){
            return "Hace "+diffMonth+" mes(es)";
        }
        long diffDay = (endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis())/(1000*60*60*24);
        if(diffDay>0){
            return "Hace "+diffDay+" dia(s)";
        }
        long diffHour = (endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis())/(1000*60*60);
        if(diffHour>0){
            return "Hace "+diffHour+" hora(s)";
        }
        long diffMinute = (endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis())/(1000*60);
        if(diffMinute>0){
            return "Hace "+diffMinute+" minuto(s)";
        }
        return "Hace pocos segundos";
    }
    
    private static Timestamp getCurrentTimeStamp() {
     
        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());
     
    }
    public class ArticuloDetalle{
        List<String> imagenes;
        List<String> textos;
        List<List<String>> comentarios;
        public ArticuloDetalle(){
            imagenes = new ArrayList<String>();            
            textos = new ArrayList<String>();            
            comentarios = new ArrayList<List<String>>();
        }
        public void addImagen(String i){
            imagenes.add(i);
        }
        public void addTexto(String i){
            textos.add(i);
        }
        public void addComentario(List<String> i){
            comentarios.add(i);
        }
        public List<String> getImagenes(){
            return imagenes;
        }
        public List<String> getTextos(){
            return textos;
        }
        public List<List<String>> getComentarios(){
            return comentarios;
        }
        
    }
    
    
}

