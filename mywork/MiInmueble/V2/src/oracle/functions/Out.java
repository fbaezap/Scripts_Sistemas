package oracle.functions;

public class Out {
    private String salida;
    public Out() {
        salida = "";
    }
    public void print(String a){
        salida = salida+a;
    }
    public void print(int a){
        salida = salida+a;
    }
    public String salidaFinal(){        
        return salida;
    }
}
