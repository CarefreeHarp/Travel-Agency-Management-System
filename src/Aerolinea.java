import java.util.ArrayList;
import java.util.Scanner;

public class Aerolinea extends AtributosComunes{
    public Aerolinea(String nombre, int codigo){
        this.nombre = nombre;
        this.codigo = codigo;
    }
    public Aerolinea(){
    }
    public static void registro(ArrayList<AtributosComunes> lista){
        Scanner cin = new Scanner(System.in);
        Aerolinea a = new Aerolinea();
        System.out.println("Digite el nombre de la aerolinea");
        a.setNombre(cin.nextLine());
        boolean valido = false;
        System.out.println("Digite el codigo de identificacion de la aerolinea:");
        while (valido == false) {
            try {
                a.setCodigo(Integer.parseInt(cin.nextLine()),a,lista);
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Error, solo puede digitar numeros, intente de nuevo");
                valido = false;
            } catch (ExcepcionCodigoRepetido e){
                System.out.println("Error, el codigo digitado ya lo tiene otra aerolinea");
            }
        }   
       
        valido = false;
        lista.add(a);
    }
    public static void buscar(ArrayList<AtributosComunes> lista){
        String nombre;
        int k=0;
        System.out.println("Digite el nombre de la aerolinea");
        Scanner cin = new Scanner(System.in);
        nombre = cin.nextLine();
        System.out.println("Resultados de Busqueda: ");
        for(AtributosComunes p : lista){
            if(p instanceof Aerolinea){
                Aerolinea a = (Aerolinea)p; 
                if(a.getNombre().toLowerCase().contains(nombre.toLowerCase())){
                    System.out.println(k+1+". Nombre: "+a.getNombre()+"\n   Codigo: " + a.getCodigo());    
                    k++;
                }
            }
        }
    }
    public static void eliminar(ArrayList<AtributosComunes> lista){
        ArrayList<Aerolinea> listaAerolineas = new ArrayList<>();
        String nombre;
        int codigoBuscado = 0;
        boolean encontrado = false;
        int k=0;
        int op = 0;
        int posk = 0;
        int tam = 0;
        boolean valido = false;
        boolean seguro = true;
        boolean opcion = false;
        String eleccion = "";
        System.out.println("Digite el nombre de la aerolinea que desea eliminar");
        Scanner cin = new Scanner(System.in);
        nombre = cin.nextLine();
        System.out.println("Resultados de Busqueda: ");
        for(AtributosComunes p : lista){
            if(p instanceof Aerolinea){
                Aerolinea a = (Aerolinea)p; 
                if(a.getNombre().toLowerCase().contains(nombre.toLowerCase())){
                    listaAerolineas.add(a);
                    tam++;
                    encontrado = true;
                    System.out.println(k+1+". Nombre: "+a.getNombre()+"\n   Codigo: " + a.getCodigo());    
                    k++;
                }
            } 
        }
        k = 0;
        if(encontrado == true){
            while(valido == false){
                System.out.println("Cual de las siguientes aerolineas desea eliminar? Digite el numero del resultado de busqueda: ");
                try{
                    op = Integer.parseInt(cin.nextLine());
                    if(op>tam || op<1){
                        throw new ExcepcionIndicePorFueraDelLimite("Esa opcion no existe");
                    }
                    valido = true;
                }catch(NumberFormatException e){
                    System.out.println("Error, debe ingresar un número, trate de nuevo");
                    valido = false;
                }catch(ExcepcionIndicePorFueraDelLimite e){
                    System.out.println("Desea cancelar la operacion? Si/No ");
                    if(cin.nextLine().toLowerCase().contains("si")){
                        valido = true;
                        seguro = false;
                    }
                }
            }
            if(seguro==true){
                for(Aerolinea p : listaAerolineas){
                    if(k == op-1){
                        codigoBuscado = p.getCodigo();
                    }
                    k++;
                }
                System.out.println("Estas seguro? Si/No ");
                while (opcion == false) {
                    eleccion = cin.nextLine();
                    eleccion = eleccion.toLowerCase();
                    if(eleccion.contains("si")){
                        opcion = true;
                    }else if(eleccion.contains("no")){
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
        int k = 0;
        int posEncontrada = 0;
        int op = 0;
        int op2 = 0;
        boolean valido = false;
        boolean encontrado = false;
        boolean seguro = true;
        boolean otraVez = true;
        ArrayList<Aerolinea> listaAerolineas = new ArrayList<>();
        System.out.println("Digite el nombre de la aerolinea que desea modificar");
        Scanner cin = new Scanner(System.in);
        String nombre = cin.nextLine();
        System.out.println("Resultados de Busqueda: ");
        for(AtributosComunes p : lista){
            if(p instanceof Aerolinea){
                Aerolinea a = (Aerolinea)p; 
                if(a.getNombre().toLowerCase().contains(nombre.toLowerCase())){
                    listaAerolineas.add(a);
                    tam++;
                    encontrado = true;
                    System.out.println(k+1+". Nombre: "+a.getNombre()+"\n   Codigo: " + a.getCodigo());    
                    k++;
                }
            } 
        }
        k = 0;
        if(encontrado == true){
            while(valido == false){
                System.out.println("Cual de las aerolineas desea modificar? Digite el numero del resultado de busqueda: ");
                try{
                    op = Integer.parseInt(cin.nextLine());
                    if(op>tam|| op<1){
                        throw new ExcepcionIndicePorFueraDelLimite("Esa opcion no existe");
                    }
                    valido = true;
                }catch(NumberFormatException e){
                    System.out.println("Error, debe ingresar un número, trate de nuevo");
                    valido = false;
                }catch(ExcepcionIndicePorFueraDelLimite e){
                    System.out.println("Desea cancelar la operacion? Si/No ");
                    if(cin.nextLine().toLowerCase().contains("si")){
                        valido = true;
                        seguro = false;
                    }
                }
            }
            if(seguro==true){
                Aerolinea a = listaAerolineas.get(op-1);
                for(AtributosComunes p : lista){
                    if(a.getCodigo()==p.getCodigo()){
                        posEncontrada = k;
                    }
                    k++;
                }
                while(otraVez == true){
                    System.out.println("Que desea cambiar de la aerolinea seleccionado? Digite el numero de opcion\n1. Nombre\n2. Codigo\n3. Salir");
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
                                    System.out.println("Error, el codigo digitado ya lo tiene otra aerolinea , intente de nuevo");
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
