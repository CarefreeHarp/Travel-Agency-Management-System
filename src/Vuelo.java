import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
public class Vuelo extends AtributosComunes{

    private String origen = "";
    private String destino = "";
    private LocalDateTime fechaHora;
    private int precio = 0;

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public int getPrecio() {
        return precio;
    }

    public Vuelo(String nombre, int codigo, LocalDateTime fechaHora, String origen, String destino, int precio) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.fechaHora = fechaHora;
        this.precio = precio;
        this.origen = origen;
        this.destino = destino;
    }

    public Vuelo() {
    }

    public static void registro(ArrayList<AtributosComunes> lista) {
        Scanner cin = new Scanner(System.in);
        Vuelo a = new Vuelo();
        boolean encontrado = false;
        boolean valido = false;
        boolean seguro = true;
        int k = 0;
        int op = 0;
        int tam = 0;
        ArrayList<Aerolinea> listaAerolinea = new ArrayList<>();
        System.out.println("Desea asignar a este vuelo una aerolinea existente? Si/No");
        if(cin.nextLine().toLowerCase().contains("si")){
            System.out.println("Las aerolineas registradas en el sistema son:");
            for (AtributosComunes p : lista) {
                if (p instanceof Aerolinea) {
                    Aerolinea b = (Aerolinea) p;
                    tam++;
                    System.out.println(k+1+". Nombre: "+b.getNombre()+"\n   Codigo: " + b.getCodigo());
                    k++;
                    listaAerolinea.add(b);
                    encontrado = true;
                }
            }
            if (encontrado == true) {
                while (valido == false) {
                    System.out.println("Digite el numero de opcion de aerolinea que desea asignar al vuelo");
                    try {
                        op = Integer.parseInt(cin.nextLine());
                        if (op > tam || op < 1) {
                            throw new ExcepcionIndicePorFueraDelLimite("Esa opcion no existe");
                        }
                        valido = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Error, debe ingresar un número, trate de nuevo");
                        valido = false;
                    } catch (ExcepcionIndicePorFueraDelLimite e) {
                        System.out.println("Desea cancelar la operacion? Si/No ");
                        if (cin.nextLine().toLowerCase().equals("si")) {
                            valido = true;
                            seguro = false;
                        }
                    }
                }
                if (seguro == true) {
                    a.setNombre(listaAerolinea.get(op - 1).getNombre());
                }
            } else {
                System.out.println("No hay aerolineas registradas en el sistema!!!");
                System.out.println("Creacion de una aerolinea nueva: ");
                Aerolinea b = Aerolinea.registro(lista);
                a.setNombre(b.getNombre());
            }
        } else {
            System.out.println("Creacion de una aerolinea nueva: ");
            Aerolinea b = Aerolinea.registro(lista);
            a.setNombre(b.getNombre());
        }
        valido = false;
        System.out.println("Digite el codigo del vuelo:");
        while (valido == false) {
            try {
                a.setCodigo(Integer.parseInt(cin.nextLine()), a, lista);
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Error, solo puede digitar numeros, intente de nuevo");
                valido = false;
            } catch (ExcepcionCodigoRepetido e) {
                System.out.println("Error, el codigo digitado ya lo tiene otro vuelo");
            }
        }
        valido = false;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd/HH:mm");
        System.out.println("Digite la fecha y hora de su vuelo(yyyy-mm-dd/hh:mm)");
        while (valido == false) {
            try {
                a.setFechaHora(LocalDateTime.parse(cin.nextLine(), dtf));
                valido = true;
            } catch (DateTimeParseException e) {
                System.out.println("La fecha no cumple el formato adecuado (yyyy-mm-dd/hh:mm) , vuelva a intentar");
                valido = false;
            }
        }
        valido = false;
        System.out.println("Digite el precio del vuelo");
        while (valido == false) {
            try {
                a.setPrecio(Integer.parseInt(cin.nextLine()));
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Error, solo puede digitar numeros, intente de nuevo");
                valido = false;
            }
        }
        valido = false;
        System.out.println("Digite el origen del vuelo");
        a.setOrigen(cin.nextLine());
        System.out.println("Digite el destino del vuelo");
        a.setDestino(cin.nextLine());
        lista.add(a);
    }

    public static void buscar(ArrayList<AtributosComunes> lista) {
        int codigo = 0;
        int k = 0;
        boolean valido = false;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd/HH:mm");
        System.out.println("Digite el codigo del vuelo");
        Scanner cin = new Scanner(System.in);
        while (valido == false) {
            try {
                codigo = Integer.parseInt(cin.nextLine());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Error, solo puede digitar numeros, intente de nuevo");
                valido = false;
            }
        }
        System.out.println("Vuelos encontrados con codigo: " + codigo);
        for (AtributosComunes p : lista) {
            if (p instanceof Vuelo) {
                Vuelo a = (Vuelo) p;
                if (a.getCodigo() == codigo) {
                    k++;
                    System.out.println(
                            k + 1 + ". Aerolinea: " + a.getNombre() + "\n   Codigo: " + a.getCodigo() + "\n   Precio: "
                                    + a.getPrecio() + "\n   Fecha y hora del vuelo: " + a.getFechaHora().format(dtf)
                                    + "\n   Origen: " + a.getOrigen() + "\n   Destino: " + a.getDestino());
                }
            }
        }
    }

    public static void eliminar(ArrayList<AtributosComunes> lista, ArrayList<Reserva> listaReservas) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd/HH:mm");
        ArrayList<Vuelo> listaVuelos = new ArrayList();
        int codigo = 0;
        String aerolineaBuscada = "";
        boolean encontrado = false;
        int k = 0;
        int op = 0;
        int posk = 0;
        int tam = 0;
        boolean valido = false;
        boolean valido1 = false;
        boolean seguro = true;
        boolean opcion = false;
        String eleccion = "";
        System.out.println("Digite el codigo del vuelo que desea eliminar");
        Scanner cin = new Scanner(System.in);
        while (valido1 == false) {
            try {
                codigo = Integer.parseInt(cin.nextLine());
                valido1 = true;
            } catch (NumberFormatException e) {
                System.out.println("Error, solo puede digitar numeros, intente de nuevo");
                valido1 = false;
            }
        }
        valido1 = false;
        System.out.println("Resultados de Busqueda: ");
        for (AtributosComunes p : lista) {
            if (p instanceof Vuelo) {
                Vuelo a = (Vuelo) p;
                if (a.getCodigo() == codigo) {
                    listaVuelos.add(a);
                    tam++;
                    encontrado = true;
                    System.out.println(
                            k + 1 + ". Aerolinea: " + a.getNombre() + "\n   Codigo: " + a.getCodigo() + "\n   Precio: "
                                    + a.getPrecio() + "\n   Fecha y hora del vuelo: " + a.getFechaHora().format(dtf)
                                    + "\n   Origen: " + a.getOrigen() + "\n  Destino: " + a.getDestino());
                    k++;
                }
            }
        }
        k = 0;
        if (encontrado == true) {
            while (valido == false) {
                System.out.println("Cual de los vuelos desea eliminar? Digite el numero del resultado de busqueda: ");
                try {
                    op = Integer.parseInt(cin.nextLine());
                    if (op > tam || op < 1) {
                        throw new ExcepcionIndicePorFueraDelLimite("Esa opcion no existe");
                    }
                    valido = true;
                } catch (NumberFormatException e) {
                    System.out.println("Error, debe ingresar un número, trate de nuevo");
                    valido = false;
                } catch (ExcepcionIndicePorFueraDelLimite e) {
                    System.out.println("Desea cancelar la operacion? Si/No ");
                    if (cin.nextLine().toLowerCase().contains("si")) {
                        valido = true;
                        seguro = false;
                    }
                }
            }
            if (seguro == true) {
                for (Vuelo p : listaVuelos) {
                    if (k == op - 1) {
                        aerolineaBuscada = p.getNombre();
                    }
                    k++;
                }
                System.out.println("Estas seguro? Si/No ");
                while (opcion == false) {
                    eleccion = cin.nextLine();
                    eleccion = eleccion.toLowerCase();
                    if (eleccion.contains("si")) {
                        opcion = true;
                    } else if (eleccion.contains("no")) {
                        opcion = true;
                        seguro = false;
                    } else {
                        System.out.println("Opcion invalida, vuelva a intentar");
                    }
                }
                if (seguro == true) {
                    k = 0;
                    for (AtributosComunes p : lista) {
                        if(p instanceof Vuelo){
                            if (p.getNombre().equals(aerolineaBuscada)) {
                                posk = k;
                            }
                        }
                        k++;
                    }
                    if(!Reserva.verificacion(lista.get(posk), lista, listaReservas)){
                        System.out.println("No se pudo borrar, el vuelo seleccionado ya que pertenece a una reserva");
                    } else {
                        lista.remove(posk);
                    }
                } else {
                    System.out.println("operacion anulada :D");
                }
            }
        }
    }

    public static void modificar(ArrayList<AtributosComunes> lista) {
        int tam = 0;
        int codigo = 0;
        int k = 0;
        int posEncontrada = 0;
        int op = 0;
        int op2 = 0;
        boolean valido = false;
        boolean valido1 = false;
        boolean encontrado = false;
        boolean seguro = true;
        boolean otraVez = true;
        String auxStr = "";
        LocalDateTime auxFecha = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd/HH:mm");
        ArrayList<Vuelo> listaVuelos = new ArrayList();
        Scanner cin = new Scanner(System.in);
        System.out.println("Digite el codigo del vuelo que desea modificar");
        while (valido1 == false) {
            try {
                codigo = Integer.parseInt(cin.nextLine());
                valido1 = true;
            } catch (NumberFormatException e) {
                System.out.println("Error, solo puede digitar numeros, intente de nuevo");
                valido1 = false;
            }
        }
        valido1 = false;
        System.out.println("Resultados de Busqueda: ");
        for (AtributosComunes p : lista) {
            if (p instanceof Vuelo) {
                Vuelo a = (Vuelo) p;
                if (a.getCodigo() == codigo) {
                    listaVuelos.add(a);
                    tam++;
                    encontrado = true;
                    System.out.println(
                            k + 1 + ". Aerolinea: " + a.getNombre() + "\n   Codigo: " + a.getCodigo() + "\n   Precio: "
                                    + a.getPrecio() + "\n   Fecha y hora del vuelo: " + a.getFechaHora().format(dtf)
                                    + "\n   Origen: " + a.getOrigen() + "\n  Destino: " + a.getDestino());
                    k++;
                }
            }
        }
        k = 0;
        if (encontrado == true) {
            while (valido == false) {
                System.out.println("Cual de los vuelos desea modificar? Digite el numero del resultado de busqueda: ");
                try {
                    op = Integer.parseInt(cin.nextLine());
                    if (op > tam || op < 1) {
                        throw new ExcepcionIndicePorFueraDelLimite("Esa opcion no existe");
                    }
                    valido = true;
                } catch (NumberFormatException e) {
                    System.out.println("Error, debe ingresar un número, trate de nuevo");
                    valido = false;
                } catch (ExcepcionIndicePorFueraDelLimite e) {
                    System.out.println("Desea cancelar la operacion? Si/No ");
                    if (cin.nextLine().toLowerCase().contains("si")) {
                        valido = true;
                        seguro = false;
                    }
                }
            }
            if (seguro == true) {
                Vuelo a = listaVuelos.get(op - 1);
                for (AtributosComunes p : lista) {
                    if (a.getNombre().toLowerCase().contains(p.getNombre().toLowerCase())) {
                        posEncontrada = k;
                    }
                    k++;
                }
                while (otraVez == true) {
                    System.out.println(
                            "Que desea cambiar del vuelo seleccionado? Digite el numero de opcion\n1. Aerolinea\n2. Codigo\n3. Fecha y hora\n4. Precio\n5. Origen\n6. Destino\n7. Salir");
                    valido = false;
                    while (valido == false) {
                        try {
                            op2 = Integer.parseInt(cin.nextLine());
                            valido = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Error, solo puede digitar numeros validos, intente de nuevo");
                        }
                    }
                    valido = false;
                    switch (op2) {
                        case 1:
                            System.out.println("Escriba la nueva aerolinea");
                            a.setNombre(cin.nextLine());
                            break;
                        case 2:
                            System.out.println("Escriba el nuevo codigo");
                            while (valido == false) {
                                try {
                                    a.setCodigo(Integer.parseInt(cin.nextLine()), a, lista);
                                    valido = true;
                                } catch (NumberFormatException e) {
                                    System.out.println("Error, debe digitar solo numeros validos, intente de nuevo");
                                } catch (ExcepcionCodigoRepetido e) {
                                    System.out.println(
                                            "Error, el codigo digitado ya lo tiene otro cliente, intente de nuevo");
                                }
                            }
                            break;
                        case 3:
                            System.out.println("Escriba la nueva fecha y hora(yyyy-mm-dd/hh:mm)");
                            while (valido == false) {
                                try {
                                    a.setFechaHora(LocalDateTime.parse(cin.nextLine(), dtf));
                                    valido = true;
                                } catch (DateTimeParseException e) {
                                    System.out
                                            .println("Error, la fecha no sigue el formato indicado, intente de nuevo");
                                }
                            }
                            break;
                        case 4:
                            System.out.println("Escriba el nuevo precio");
                            a.setPrecio(Integer.parseInt(cin.nextLine()));
                            break;
                        case 5:
                            System.out.println("Escriba el nuevo origen");
                            a.setOrigen(cin.nextLine());
                        case 6:
                            System.out.println("Escriba el nuevo destino");
                            a.setDestino(cin.nextLine());
                        default:
                            otraVez = false;
                            break;
                    }
                }
                lista.set(posEncontrada, a);
            }
        }
    }

    public boolean estaVacio() {
        return nombre.equals("");
    }
}
