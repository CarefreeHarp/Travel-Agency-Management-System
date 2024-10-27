import java.util.ArrayList;
public class AtributosComunes {
    protected int codigo;
    protected String nombre;
    public void setCodigo(int codigo,AtributosComunes a, ArrayList<AtributosComunes> lista) throws ExcepcionCodigoRepetido{
        for(AtributosComunes p : lista){
            if(p.getClass()==a.getClass()){
                if(codigo==p.getCodigo()){
                    throw new ExcepcionCodigoRepetido("Codigo invalido");
                }
            }
        }
        this.codigo = codigo;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public int getCodigo() {
        return codigo;
    }
    public String getNombre() {
        return nombre;
    }
}
