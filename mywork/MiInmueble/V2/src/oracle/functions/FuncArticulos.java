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

public class FuncArticulos {
    public FuncArticulos() {
        super();
    }
    public static boolean esMiArticulo(String user,String artiId) throws ClassNotFoundException,
                                                             SQLException {
        Connection conn = ConectarBD.getConnection();
        
        String query="select * from articulo where vendedor_rut = ? and codigo = ?"; 
        PreparedStatement st = conn.prepareStatement(query);
        ResultSet rs;
        st.setString(1, user);
        st.setString(2, artiId);
        rs = st.executeQuery();
        
        if(rs.next()){
            return true;
        }else{
            return false;
        }
    }
    public static void editaArticulo(List<String> campo, List<String> valor, String artiId) throws ClassNotFoundException,
                                                           SQLException {
        Connection conn = ConectarBD.getConnection();
        String query="update articulo set ";
        for(int i =1;i<valor.size();i++){
            query+=campo.get(i)+"='"+valor.get(i)+"', ";
        }
        query+=campo.get(0)+"='"+valor.get(0)+"' where codigo="+artiId;
        
        System.out.println(query);
        
        PreparedStatement st = conn.prepareStatement(query);
         st.executeUpdate();
        
    }
    public static List<List<String>> findArticulos(List<CampoValor> campovalor, List<String> mostrar,boolean sindetalle) throws SQLException,
                                                                              ClassNotFoundException {        
        Connection conn = ConectarBD.getConnection();
        
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
        System.out.println(query);
        PreparedStatement st = conn.prepareStatement(query);        
        ResultSet rs = st.executeQuery();
                                
        String query2="select * from Fotos where articulo_codigo = ?"; 
        PreparedStatement st2 = conn.prepareStatement(query2);
        ResultSet rs2;
             
        while (rs.next()) {
            salida = new ArrayList<String>();            
            st2.setInt(1, rs.getInt("codigo"));
            rs2 = st2.executeQuery();
            String temp = "<a href=\"detalle.jsp?artId="+rs.getInt("codigo")+"\">";
            if(rs2.next()){                   
                temp+=(muestraImagen(rs2.getString("photos_imageid")));
            }else{
                temp+=(muestraSinImagen());
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
    public static String muestraImagen(String imagenid) throws SQLException,
                                                            ClassNotFoundException {
        Connection conn = ConectarBD.getConnection();
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
