package oracle.functions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class Lugares {
    
    public Lugares() {
        super();
        
    }
    public static List<String[]> getRegiones(){
        List<String[]> regiones = new ArrayList<String[]>();
        try{
            Connection conn = ConectarBD.getConnection();
                
            String query="select * from region"; 
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs;        
            rs = st.executeQuery();  
            String[] region;
            region = new String[2];
            region[0] = "0";
            region[1] = "Todas";
            regiones.add(region);
            
            while(rs.next()){
                region = new String[2];
                region[0] = rs.getString("codigo");
                region[1] = rs.getString("nombre");
                regiones.add(region);
            }
            conn.close();
            return regiones;
        }catch(Exception e){
            return regiones;
        }
    }
    public static List<String[]> getProvincias(String cod_region){
        List<String[]> provincias = new ArrayList<String[]>();
        try{
            Connection conn = ConectarBD.getConnection();
                
            String query="select * from provincia where region_codigo = ?"; 
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, cod_region);
            if(cod_region.equalsIgnoreCase("0")){
                query = "select * from provincia"; 
                st = conn.prepareStatement(query);
            }
            
            ResultSet rs;        
            rs = st.executeQuery();  
            String[] provincia;
            provincia = new String[2];
            provincia[0] = "0";
            provincia[1] = "Todas";
            provincias.add(provincia);
            while(rs.next()){
                provincia = new String[2];
                provincia[0] = rs.getString("codigo");
                provincia[1] = rs.getString("nombre");
                provincias.add(provincia);
            }
            conn.close();
            return provincias;
        }catch(Exception e){
            return provincias;
        }
    }
    public static List<String[]> getComunas(String cod_provincia,String cod_region){
        List<String[]> comunas = new ArrayList<String[]>();
        try{
            Connection conn = ConectarBD.getConnection();
            
            String query;
            PreparedStatement st;
            
            if(cod_provincia.equalsIgnoreCase("0") && cod_region.equalsIgnoreCase("0")){
                query = "select codigo, nombre from comuna";
                st = conn.prepareStatement(query);                
            }else if(cod_provincia.equalsIgnoreCase("0")){
                query = "select co.codigo codigo, co.nombre nombre " +
                    "from comuna co, provincia pr " +
                    "where pr.codigo=co.provincia_codigo(+) and pr.region_codigo= ? " +
                    "order by codigo asc";
                st = conn.prepareStatement(query);
                st.setString(1, cod_region);
            }else{
                query = "select co.codigo codigo, co.nombre nombre " +
                    "from comuna co " +
                    "where co.provincia_codigo=? " +
                    "order by codigo asc";
                st = conn.prepareStatement(query);
                st.setString(1, cod_provincia);
            }

            ResultSet rs;        
            rs = st.executeQuery();  
            String[] comuna;
            comuna = new String[2];
            comuna[0] = "0";
            comuna[1] = "Todas";
            comunas.add(comuna);
            while(rs.next()){
                comuna = new String[2];
                comuna[0] = rs.getString("codigo");
                comuna[1] = rs.getString("nombre");
                comunas.add(comuna);
            }
            conn.close();
            return comunas;
        }catch(Exception e){
            e.printStackTrace();
            return comunas;
        }
    }
    public static String getEstructuras(String est){
        String[][] estructuras = new String[4][2];
        estructuras[0][0] = "0";
        estructuras[0][1] = "Todas";
        estructuras[1][0] = "Casa";
        estructuras[1][1] = "Casa";
        estructuras[2][0] = "Departamento";
        estructuras[2][1] = "Departamento";
        estructuras[3][0] = "Terreno";
        estructuras[3][1] = "Terreno";
        
        String salida = "";
        for(int i=0;i<estructuras.length;i++){
            salida+="<option ";
            if(estructuras[i][0].equalsIgnoreCase(est)){
                salida+="selected=\"selected\" ";
            }
            salida+="value=\""+estructuras[i][0]+"\">"+estructuras[i][1]+"</option>";
        }
        return salida;
    }
    public static String getTipos(String est){
        String[][] estructuras = new String[3][2];
        estructuras[0][0] = "0";
        estructuras[0][1] = "Todos";
        estructuras[1][0] = "Venta";
        estructuras[1][1] = "Venta";
        estructuras[2][0] = "Arriendo";
        estructuras[2][1] = "Arriendo";
        
        String salida = "";
        for(int i=0;i<estructuras.length;i++){
            salida+="<option ";
            if(estructuras[i][0].equalsIgnoreCase(est)){
                salida+="selected=\"selected\" ";
            }
            salida+="value=\""+estructuras[i][0]+"\">"+estructuras[i][1]+"</option>";
        }
        return salida;
    }
    public static String[] getObjetos( String com, String pro, String reg,String ultimocambiado){
        String[] retorno = new String[3];
        if(ultimocambiado.equalsIgnoreCase("R")){
            com="0";
            pro="0";
        }else if(ultimocambiado.equalsIgnoreCase("P")){
            com="0";
            reg = getRegion(com,pro);            
        }else{
            pro = getProvincia(com);
            reg = getRegion(com,pro);
        }
        
        List<List<String[]>> salidas = getAllbyAll(com,pro,reg);
        
        List<String[]> regiones = salidas.get(0);
        List<String[]> provincias = salidas.get(1);
        List<String[]> comunas = salidas.get(2);
        
        retorno[0]="";
        String text = "";
        for(String[] sal:regiones){
            text+="<option ";
            if(sal[0].equalsIgnoreCase(reg)){
                text+="selected=\"selected\" ";
            }
            text+="value=\""+sal[0]+"\">"+sal[1]+"</option>";
            retorno[0]+=text;
            text = "";
        }
        text = "";
        retorno[1]="";
        for(String[] sal:provincias){
            text+="<option ";
            if(sal[0].equalsIgnoreCase(pro)){
                text+="selected=\"selected\" ";
            }
            text+="value=\""+sal[0]+"\">"+sal[1]+"</option>";
            retorno[1]+=text;
            text = "";
        }
        text = "";
        retorno[2]="";
        for(String[] sal:comunas){
            text+="<option ";
            if(sal[0].equalsIgnoreCase(com)){
                text+="selected=\"selected\" ";
            }
            text+="value=\""+sal[0]+"\">"+sal[1]+"</option>";
            retorno[2]+=text;
            text = "";
        }
        return retorno;
    }
    public static String getRegion(String com, String pro){
       
        if(com.equalsIgnoreCase("0") && pro.equalsIgnoreCase("0")){
            return "0";  
        }else if(pro.equalsIgnoreCase("0")){
            return com.substring(0, 2);
        }else{
            return pro.substring(0, 2);
        }            
          
    }
    public static String getProvincia(String com){
       
        System.out.println("EL VALOR QUE SE PASA A GET PROVINCIA ES: "+com);
        if(com.equalsIgnoreCase("0")){
            return "0";
        }else{
            return com.substring(0, 3);
        }            
          
    }
    public static List<List<String[]>> getAllbyAll(String com,String pro,String reg){
        List<List<String[]>> salida = new ArrayList<List<String[]>>();

        List<String[]> regiones = getRegiones();
        List<String[]> provincias = getProvincias(reg);
        List<String[]> comunas = getComunas(pro,reg);
        salida.add(regiones);
        salida.add(provincias);
        salida.add(comunas);
        return salida;
    }
    public static void main(String args[]){
        String[] objetos = Lugares.getObjetos("0","103","0","P");
        System.out.println(objetos[0]);
        System.out.println(objetos[1]);
        System.out.println(objetos[2]);
        
        System.out.println(getEstructuras("0"));
    }
    public static String getNombre(String tabla, String codigo) throws ClassNotFoundException,
                                                            SQLException {
        
        Connection conn = ConectarBD.getConnection();
                
            String query="select * from "+tabla+" where codigo = ?"; 
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, codigo);            
            
            ResultSet rs;        
            rs = st.executeQuery();  
            String nombre = "Sin nombre";
            if(rs.next()){
                nombre = rs.getString("nombre");
                
            }
            conn.close();
            return nombre;
        
    }
}
