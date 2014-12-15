package oracle.functions;

public class CampoValor {
    public CampoValor(String c, String v) {
        super();
        campo = c;
        operacion = "=";
        valor = v;
    }
    public CampoValor(String c, String o, String v) {
        super();
        campo = c;
        operacion = o;
        valor = v;
    }
    private String campo;
    private String valor;
    private String operacion;
    
    public String getCampo(){
        return campo;
    }
    public String getValor(){
        return valor;
    }
    public String getOperacion(){
        return operacion;
    }
    public void setOperacion(String o){
        operacion = o;
    }
    public void setCampo(String c){
        campo = c;
    }
    public void setValor(String v){
        valor = v;
    }
}
