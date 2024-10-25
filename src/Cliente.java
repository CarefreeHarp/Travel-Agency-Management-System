import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Cliente extends AtributosComunes implements MetodosComunes {
    private LocalDate fechaNacimiento;
    private int informacionContacto;
    public void setFechaNacimiento(LocalDate fechaNacimiento){
        this.fechaNacimiento = fechaNacimiento;
    }
    public void setInformacionContacto(int informacionContacto){
        this.informacionContacto = informacionContacto;
    }
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    public int getInformacionContacto() {
        return informacionContacto;
    }
    public Cliente(String nombre, int codigo, LocalDate fechaNacimiento, int informacionContacto){
        this.nombre = nombre;
        this.codigo = codigo;
        this.fechaNacimiento = fechaNacimiento;
        this.informacionContacto = informacionContacto;
    }
    public Cliente(){
    }
    public void registro(ArrayList<AtributosComunes> lista){
        Scanner cin = new Scanner(System.in);
        System.out.println("Digite el nombre del cliente");
        nombre = cin.nextLine();
        boolean valido = false;
        System.out.println("Digite el codigo del cliente:");
        while (valido == false) {
            try {
                codigo = Integer.parseInt(cin.nextLine());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Error, solo puede digitar numeros, intente de nuevo");
                valido = false;
            }
        }
        valido = false;
        System.out.println("Digite la fecha de nacimiento(yyyy-mm-dd)");
        while(valido == false){
            try{
                fechaNacimiento = LocalDate.parse(cin.nextLine());
                valido = true;
            }catch(DateTimeParseException e){
                System.out.println("La fecha no cumple el formato adecuado, vuelva a intentar");
                valido = false;
            }
        }
        valido = false;
        System.out.println("Digite el numero de contacto");
        while (valido == false) {
            try {
                informacionContacto = Integer.parseInt(cin.nextLine());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Error, solo puede digitar numeros, intente de nuevo");
                valido = false;
            }
        }
        valido = false;
        lista.add(new Cliente(nombre, codigo, fechaNacimiento, informacionContacto));
    }
    public void buscar(ArrayList<AtributosComunes> lista){
        String nombre;
        System.out.println("Digite el nombre del cliente");
        Scanner cin = new Scanner(System.in);
        nombre = cin.nextLine();
        System.out.println("Clientes encontrados con nombre: " + nombre);
        for(AtributosComunes p : lista){
            if(p instanceof Cliente){
                Cliente a = (Cliente)p; 
                if(a.getNombre().equals(nombre)){
                    System.out.println("Nombre: "+a.getNombre()+"\nCodigo: " + a.getCodigo()+"\nFecha de nacimiento: " + a.getFechaNacimiento()+"\nInformacion contacto: " + a.getInformacionContacto());    
                }
            }
        }
    }
    public void eliminar(ArrayList<AtributosComunes> lista){
        ArrayList<Cliente> listaClientes = new ArrayList();
        String nombre;
        int codigoBuscado = 0;
        boolean encontrado = false;
        int k=0;
        int op = 0;
        int posk = 0;
        boolean valido = false;
        boolean seguro = false;
        boolean opcion = false;
        String eleccion = "";
        System.out.println("Digite el nombre del cliente que desea eliminar");
        Scanner cin = new Scanner(System.in);
        nombre = cin.nextLine();
        System.out.println("Clientes encontrados con: " + nombre);
        for(AtributosComunes p : lista){
            if(p instanceof Cliente){
                Cliente a = (Cliente)p; 
                if(a.getNombre().equals(nombre)){
                    listaClientes.add(a);
                    encontrado = true;
                    System.out.println(k+1+". Nombre: "+a.getNombre()+"\n   Codigo: " + a.getCodigo()+"\n   Fecha de nacimiento: " + a.getFechaNacimiento()+"\n   Informacion contacto: " + a.getInformacionContacto());    
                    k++;
                }
            }
            
        }
        k = 0;
        if(encontrado == true){
            System.out.println("Cuál de los anteriores desea eliminar? Digite su numero: ");
            while(valido == false){
                try{
                    op = Integer.parseInt(cin.nextLine());
                    valido = true;
                }catch(NumberFormatException e){
                    System.out.println("Error, debe ingresar un número, trate de nuevo");
                    valido = false;
                }
            }
            valido = false;
            for(Cliente p : listaClientes){
                if(k == op-1){
                    codigoBuscado = p.getCodigo();
                }
                k++;
            }
            System.out.println("Estas seguro?si-no");
            while (opcion == false) {
                eleccion = cin.nextLine();
                eleccion = eleccion.toLowerCase();
                if(eleccion.equals("si")){
                    opcion = true;
                    seguro = true;
                }else if(eleccion.equals("no")){
                    opcion = true;
                }else{
                    System.out.println("Vuelva a intentar");
                }
            }
            if(seguro == true){
                k = 0;
                for(AtributosComunes p : lista){
                    if(p.getCodigo() == codigoBuscado){
                        posk = k;
                    } 
                    k++;
                } 
                lista.remove(posk);
            }else{
                System.out.println("operacion anulada :D");
            }
        }
    }
}
