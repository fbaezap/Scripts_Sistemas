package oracle.functions;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

public class FuncVendedor {
    public FuncVendedor() {
        super();
    }
    public static List<List<String>> getDatosByRut(String user) throws ClassNotFoundException,
                                              SQLException {
        
        List<List<String>> salidas = new ArrayList<List<String>>();
        List<String> salida = new ArrayList<String>();
        String driverName = "oracle.jdbc.OracleDriver";
        Class.forName(driverName);
        String cadenaConexion= "jdbc:oracle:thin:@127.0.0.1:1521:xe";
        String usuario= "fbaeza";
        String password = "fbaeza";
        Connection conn = DriverManager.getConnection(cadenaConexion, usuario, password);   
        
        String query="select * from vendedor where rut = ? "; 
        PreparedStatement st = conn.prepareStatement(query);
        ResultSet rs;
        st.setString(1, user);
        rs = st.executeQuery();
        
        if(rs.next()){
            String query2; 
            PreparedStatement st2;
            ResultSet rs2;
            st.setString(1, user);
            String tipo = rs.getString("tipo");
            if(tipo.equalsIgnoreCase("Persona")){
                query2="select * from Persona where vendedor_rut = ?"; 
                st2 = conn.prepareStatement(query2);
                st2.setString(1, user);
                rs2 = st2.executeQuery();
                while(rs2.next()){
                    if(rs2.getString("nombre")!=null){
                        salida = new ArrayList<String>();
                        salida.add("Nombre");
                        salida.add(rs2.getString("nombre"));
                        salidas.add(salida);
                    }
                    if(rs2.getString("apellidos")!=null){
                        salida = new ArrayList<String>();
                        salida.add("Apellido");
                        salida.add(rs2.getString("apellidos"));
                        salidas.add(salida);
                    }                    
                    if(rs2.getString("genero")!=null){
                        salida = new ArrayList<String>();
                        salida.add("Genero");
                        salida.add(rs2.getString("genero"));
                        salidas.add(salida);
                    }
                    return salidas;
                }
            }else if(tipo.equalsIgnoreCase("Corredor")){
                query2="select * from Corredor where vendedor_rut = ?"; 
                st2 = conn.prepareStatement(query2);
                st2.setString(1, user);
                rs2 = st2.executeQuery();
                while(rs2.next()){
                    if(rs2.getString("nombre")!=null){
                        salida = new ArrayList<String>();
                        salida.add("Nombre");
                        salida.add(rs2.getString("nombre"));
                        salidas.add(salida);
                    }
                    if(rs2.getString("apellido")!=null){
                        salida = new ArrayList<String>();
                        salida.add("Apellido");
                        salida.add(rs2.getString("apellido"));
                        salidas.add(salida);
                    }                    
                    if(rs2.getString("genero")!=null){
                        salida = new ArrayList<String>();
                        salida.add("Genero");
                        salida.add(rs2.getString("genero"));
                        salidas.add(salida);
                    }
                    return salidas;
                }
            }else{
                query2="select * from Inmobiliaria where vendedor_rut = ?"; 
                st2 = conn.prepareStatement(query2);
                st2.setString(1, user);
                rs2 = st2.executeQuery();
                while(rs2.next()){
                    if(rs2.getString("nombre")!=null){
                        salida = new ArrayList<String>();
                        salida.add("Nombre");
                        salida.add(rs2.getString("nombre"));
                        salidas.add(salida);
                    }else{
                        salida = new ArrayList<String>();
                        salida.add("Nombre");
                        salida.add("");
                        salidas.add(salida);
                    }                            
                    if(rs2.getString("paginaweb")!=null){
                        salida = new ArrayList<String>();
                        salida.add("Pagina Web");
                        salida.add(rs2.getString("paginaweb"));
                        salidas.add(salida);
                    }else{
                        salida = new ArrayList<String>();
                        salida.add("Pagina Web");
                        salida.add("");
                        salidas.add(salida);
                    }                            
                    if(rs2.getString("TIPO_SOCIEDAD")!=null){
                        salida = new ArrayList<String>();
                        salida.add("Tipo de Sociedad");
                        salida.add(rs2.getString("TIPO_SOCIEDAD"));
                        salidas.add(salida);
                    }else{
                        salida = new ArrayList<String>();
                        salida.add("Tipo de Sociedad");
                        salida.add("");
                        salidas.add(salida);
                    }                            
                    if(rs2.getString("DOMICILIO_LEGAL")!=null){
                        salida = new ArrayList<String>();
                        salida.add("Domicilio Legal");
                        salida.add(rs2.getString("DOMICILIO_LEGAL"));
                        salidas.add(salida);
                    }else{
                        salida = new ArrayList<String>();
                        salida.add("Domicilio Legal");
                        salida.add("");
                        salidas.add(salida);
                    }                            
                    
                    if(rs2.getString("DOMICILIO_COMERCIAL")!=null){
                        salida = new ArrayList<String>();
                        salida.add("Domicilio Comercial");
                        salida.add(rs2.getString("DOMICILIO_COMERCIAL"));
                        salidas.add(salida);
                    }else{
                        salida = new ArrayList<String>();
                        salida.add("Domicilio Comercial");
                        salida.add("");
                        salidas.add(salida);
                    }
                    
                    if(rs2.getString("PHOTOS_IMAGEID")!=null){
                        salida = new ArrayList<String>();
                        salida.add("Logo");
                        salida.add(muestraImagen(rs2.getString("PHOTOS_IMAGEID")));
                        salidas.add(salida);
                    }else{
                        salida = new ArrayList<String>();
                        salida.add("Logo");
                        salida.add(muestraSinImagen());
                        salidas.add(salida);                        
                    }
                    return salidas;
                }
            }
            
        }
        return salidas;
    }
    public static String muestraImagen(String imagenid) throws SQLException,
                                                            ClassNotFoundException {
        String driverName = "oracle.jdbc.OracleDriver";
        Class.forName(driverName);
        String cadenaConexion= "jdbc:oracle:thin:@127.0.0.1:1521:xe";
        String usuario= "fbaeza";
        String password = "fbaeza";
        Connection conn = DriverManager.getConnection(cadenaConexion, usuario, password);   
        
        Out out = new Out();
        String query3="select * from Photos where imageid=?"; 
        PreparedStatement st3 = conn.prepareStatement(query3);
        ResultSet rs3;
        
        st3.setInt(1, Integer.parseInt(imagenid));
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
    public static String muestraSinImagen(){
        return "<img src= \"Recursos/Sin_imagen_disponible.jpg\" id=\"imagen0\" width = \"200\" \" />";
    }
}
