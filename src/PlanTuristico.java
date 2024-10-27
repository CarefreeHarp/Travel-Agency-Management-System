import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class PlanTuristico extends AtributosComunes {
    private String descripcion;
    private String destino;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int precio;


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public int getPrecio() {
        return precio;
    }

    public PlanTuristico(int codigo, String nombre, String descripcion, LocalDate fechaInicio, LocalDate fechaFin,
            int precio, String destino) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precio = precio;
        this.destino = destino;
    }
    public PlanTuristico(){
    }

    public static void registro(ArrayList<AtributosComunes> lista) {
        int codigo = 0;
        int precio = 0;
        LocalDate fechaInicio = LocalDate.now();
        LocalDate fechaFin = LocalDate.now();
        boolean valido = false;
        Scanner cin = new Scanner(System.in);
        PlanTuristico a =new PlanTuristico();
        System.out.println("Digite el nombre del plan turistico");
        a.setNombre(cin.nextLine());
        System.out.println("Digite el codigo identificador del plan turistico");
        while (valido == false) {
            try {
                a.setCodigo(Integer.parseInt(cin.nextLine()), a, lista);
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Solo puede digitar numeros, intente de nuevo");
            } catch (ExcepcionCodigoRepetido e){
                System.out.println("Error, ya existe un plan turistico con ese codigo, intente de nuevo");
            }
        }
        valido = false;
        System.out.println("Escriba la descripcion del plan turistico");
        a.setDescripcion(cin.nextLine());
        System.out.println("Escriba el destino del plan turistico");
        a.setDestino(cin.nextLine());
        System.out.println("Escriba la fecha de inicio del plan turistico (yyyy-mm-dd)");
        while (valido == false) {
            try {
                a.setFechaInicio(LocalDate.parse(cin.nextLine()));
                valido = true;
            } catch (DateTimeParseException e) {
                System.out.println("La fecha no cumple el formato adecuado (yyy-mm-dd), vuelva a intentar");
            }
        }
        valido = false;
        System.out.println("Escriba la fecha de fin del plan turistico (yyyy-mm-dd)");
        while (valido == false) {
            try {
                a.setFechaFin(LocalDate.parse(cin.nextLine()));
                valido = true;
            } catch (DateTimeParseException e) {
                System.out.println("La fecha no cumple el formato adecuado (yyy-mm-dd), vuelva a intentar");
            }
        }
        valido = false;
        System.out.println("Digite el precio del plan");
        while (valido == false) {
            try {
                a.setPrecio(Integer.parseInt(cin.nextLine()));
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Error, solo puede digitar numeros, intente de nuevo");
            }
        }
        lista.add(a);
    }

    public static void buscar(ArrayList<AtributosComunes> lista) {
        Scanner cin = new Scanner(System.in);
        int k = 0;
        System.out.println("Escriba el nombre del plan turistico que desea buscar");
        String nombre = cin.nextLine();
        System.out.println("Resultados de busqueda:");
        for(AtributosComunes p : lista){
            if(p instanceof PlanTuristico){
                PlanTuristico a = (PlanTuristico) p;
                if(a.getNombre().contains(nombre)){
                    System.out.println(k+1+". Nombre: "+a.getNombre()+"\n   Codigo: "+a.getCodigo()+"\n   Descripcion: "+a.getDescripcion()+"\n   Destino: "+a.getDestino()+"\n   Fecha de inicio: "+a.getFechaInicio()+"\n   Fecha de fin: "+a.getFechaFin()+"\n   Precio: $"+a.getPrecio());
                    k++;
                }
            }
        }
    }
    public static void eliminar(ArrayList<AtributosComunes> lista) {
        ArrayList<PlanTuristico> listaPlanes = new ArrayList<>();
        Scanner cin = new Scanner(System.in);
        boolean encontrado = false;
        boolean valido = false;
        boolean seguro = true;
        int codigoBuscado = 0;
        int op = 0;
        int k = 0;
        int tam = 0;
        int posk = 0;
        boolean opcion = false;
        String eleccion;
        System.out.println("Escriba el nombre del plan turistico que desea buscar");
        String nombre = cin.nextLine();
        System.out.println("Resultados de busqueda:");
        for(AtributosComunes p : lista){
            if(p instanceof PlanTuristico){
                PlanTuristico a = (PlanTuristico) p;
                if(a.getNombre().contains(nombre)){
                    listaPlanes.add(a);
                    encontrado = true;
                    System.out.println(k+1+". Nombre: "+a.getNombre()+"\n   Codigo: "+a.getCodigo()+"\n   Descripcion: "+a.getDescripcion()+"\n   Destino: "+a.getDestino()+"\n   Fecha de inicio: "+a.getFechaInicio()+"\n   Fecha de fin: "+a.getFechaFin()+"\n   Precio: $"+a.getPrecio());
                    k++;
                    tam++;
                }
            }
        }
        k = 0;
        if(encontrado == true){
            System.out.println("Cual de los planes desea eliminar? Digite el numero del resultado de busqueda: ");
            while(valido == false){
                try{
                    op = Integer.parseInt(cin.nextLine());
                    if(op>tam){
                        throw new ExcepcionIndicePorFueraDelLimite("Esa opcion no existe");
                    }
                    valido = true;
                }catch(NumberFormatException e){
                    System.out.println("Error, debe ingresar un número, trate de nuevo");
                    valido = false;
                }catch(ExcepcionIndicePorFueraDelLimite e){
                    System.out.println("Desea cancelar la operacion? Si/No ");
                    if(cin.nextLine().toLowerCase().equals("si")){
                        valido = true;
                        seguro = false;
                    }
                }
            }
            if(seguro==true){
                for(PlanTuristico p : listaPlanes){
                    if(k == op-1){
                        codigoBuscado = p.getCodigo();
                    }
                    k++;
                }
                System.out.println("Estas seguro? Si/No ");
                while (opcion == false) {
                    eleccion = cin.nextLine();
                    eleccion = eleccion.toLowerCase();
                    if(eleccion.equals("si")){
                        opcion = true;
                    }else if(eleccion.equals("no")){
                        opcion = true;
                        seguro = false;
                    }else{
                        System.out.println("Opcion invalida, vuelva a intentar");
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
    public static void modificar(ArrayList<AtributosComunes> lista){
        int tam = 0;
        int posEncontrada = 0;
        int k = 0;
        int op = 0;
        int op2 = 0;
        boolean valido = false;
        boolean encontrado = false;
        boolean seguro = true;
        boolean otraVez = true;
        ArrayList<PlanTuristico> listaClientes = new ArrayList<>();
        System.out.println("Digite el nombre del plan turistico que desea modificar");
        Scanner cin = new Scanner(System.in);
        String nombre = cin.nextLine();
        System.out.println("Resultados de Busqueda: ");
        for(AtributosComunes p : lista){
            if(p instanceof PlanTuristico){
                PlanTuristico a = (PlanTuristico)p; 
                if(a.getNombre().contains(nombre)){
                    listaClientes.add(a);
                    tam++;
                    encontrado = true;
                    System.out.println(k+1+". Nombre: "+a.getNombre()+"\n   Codigo: " + a.getCodigo()+"\n   Descripcion: " + a.getDescripcion()+"\n   Destino: " + a.getDestino() + "\n   Fecha de inicio: " + a.getFechaInicio() + "\n   Fecha de fin: " + a.getFechaFin()+"\n   Precio: " + a.getPrecio());    
                    k++;
                }
            } 
        }
        k = 0;
        if(encontrado == true){
            System.out.println("Cual de los planes desea modificar? Digite el numero del resultado de busqueda: ");
            while(valido == false){
                try{
                    op = Integer.parseInt(cin.nextLine());
                    if(op>tam){
                        throw new ExcepcionIndicePorFueraDelLimite("Esa opcion no existe");
                    }
                    valido = true;
                }catch(NumberFormatException e){
                    System.out.println("Error, debe ingresar un número, trate de nuevo");
                    valido = false;
                }catch(ExcepcionIndicePorFueraDelLimite e){
                    System.out.println("Desea cancelar la operacion? Si/No ");
                    if(cin.nextLine().toLowerCase().equals("si")){
                        valido = true;
                        seguro = false;
                    }
                }
            }
            if(seguro==true){
                PlanTuristico a = listaClientes.get(op-1);
                for(AtributosComunes p : lista){
                    if(a.getCodigo()==p.getCodigo()){
                        posEncontrada = k;
                    }
                    k++;
                }
                while(otraVez == true){
                    System.out.println("Que desea cambiar del plan seleccionado? Digite el numero de opcion\n1. Nombre\n2. Codigo\n3. Descripcion\n4. Destino\n5. Fecha de inicio\n6. Fecha de Fin\n7. Precio\n8. Salir");
                    valido=false;
                    while(valido==false){
                        try {
                            op2 = Integer.parseInt(cin.nextLine());
                            valido = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Error, solo puede digitar numeros validos, intente de nuevo");
                        }
                    }
                    valido=false;
                    switch (op2) {
                        case 1:
                            System.out.println("Escriba el nuevo nombre");
                            a.setNombre(cin.nextLine());
                            break;
                        case 2:
                            System.out.println("Escriba el nuevo codigo");
                            while(valido==false){
                                try {
                                    a.setCodigo(Integer.parseInt(cin.nextLine()),a,lista);
                                    valido = true;
                                } catch (NumberFormatException e) {
                                    System.out.println("Error, debe digitar solo numeros validos, intente de nuevo");
                                } catch (ExcepcionCodigoRepetido e){
                                    System.out.println("Error, el codigo digitado ya lo tiene otro plan turistico, intente de nuevo");
                                }
                            }
                            break;
                        case 3:
                            System.out.println("Escriba la nueva descripcion");
                            a.setNombre(cin.nextLine());
                            break;
                        case 4:
                            System.out.println("Escriba el nuevo destino");
                            a.setNombre(cin.nextLine());
                            break;
                        case 5:
                            System.out.println("Escriba la nueva fecha de inicio (yyyy-mm-dd)");
                            while(valido==false){
                                try {
                                    a.setFechaInicio(LocalDate.parse(cin.nextLine()));
                                    valido = true;
                                } catch (DateTimeParseException e) {
                                    System.out.println("Error, la fecha no sigue el formato indicado, intente de nuevo");
                                }
                            }
                            break;
                        case 6:
                            System.out.println("Escriba la nueva fecha de fin (yyyy-mm-dd)");
                            while(valido==false){
                                try {
                                    a.setFechaFin(LocalDate.parse(cin.nextLine()));
                                    valido = true;
                                } catch (DateTimeParseException e) {
                                    System.out.println("Error, la fecha no sigue el formato indicado, intente de nuevo");
                                }
                            }
                            break;
                        case 7:
                            System.out.println("Escriba el nuevo precio");
                            while(valido==false){
                                try {
                                    a.setPrecio(Integer.parseInt(cin.nextLine()));
                                    valido = true;
                                } catch (NumberFormatException e) {
                                    System.out.println("Error, debe digitar solo numeros validos, intente de nuevo");
                                }
                            }
                            break;
                        default:
                            otraVez=false;
                            break;
                    }
                }
                lista.set(posEncontrada,a);
            }
        }
    }
}
