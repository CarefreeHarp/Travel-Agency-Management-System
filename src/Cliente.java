import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;
public class Cliente extends AtributosComunes{
    private LocalDate fechaNacimiento;
    private long informacionContacto;

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setInformacionContacto(long informacionContacto) {
        this.informacionContacto = informacionContacto;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public long getInformacionContacto() {
        return informacionContacto;
    }

    public Cliente(String nombre, int codigo, LocalDate fechaNacimiento, long informacionContacto) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.fechaNacimiento = fechaNacimiento;
        this.informacionContacto = informacionContacto;
    }

    public Cliente() {
    }

    public static void registro(ArrayList<AtributosComunes> lista) {
        Scanner cin = new Scanner(System.in);
        Cliente a = new Cliente();
        System.out.println("Digite el nombre del cliente");
        a.setNombre(cin.nextLine());
        boolean valido = false;
        System.out.println("Digite el codigo de identificacion del cliente:");
        while (valido == false) {
            try {
                a.setCodigo(Integer.parseInt(cin.nextLine()), a, lista);
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Error, solo puede digitar numeros, intente de nuevo");
                valido = false;
            } catch (ExcepcionCodigoRepetido e) {
                System.out.println("Error, el codigo digitado ya lo tiene otro cliente");
            }
        }
        valido = false;
        System.out.println("Digite la fecha de nacimiento(yyyy-mm-dd)");
        while (valido == false) {
            try {
                a.setFechaNacimiento(LocalDate.parse(cin.nextLine()));
                valido = true;
            } catch (DateTimeParseException e) {
                System.out.println("La fecha no cumple el formato adecuado (yyy-mm-dd) , vuelva a intentar");
                valido = false;
            }
        }
        valido = false;
        System.out.println("Digite el numero de contacto");
        while (valido == false) {
            try {
                a.setInformacionContacto(Long.parseLong(cin.nextLine()));
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Error, solo puede digitar numeros, intente de nuevo");
                valido = false;
            }
        }
        valido = false;
        lista.add(a);
    }

    public static void buscar(ArrayList<AtributosComunes> lista) {
        String nombre;
        int k = 0;
        System.out.println("Digite el nombre del cliente");
        Scanner cin = new Scanner(System.in);
        nombre = cin.nextLine();
        System.out.println("Resultados de busqueda:");
        for (AtributosComunes p : lista) {
            if (p instanceof Cliente) {
                Cliente a = (Cliente) p;
                if (a.getNombre().contains(nombre)) {
                    System.out.println(k + 1 + ". Nombre: " + a.getNombre() + "\n   Codigo: " + a.getCodigo()
                            + "\n   Fecha de nacimiento: " + a.getFechaNacimiento() + "\tEdad: "
                            + ChronoUnit.YEARS.between(a.getFechaNacimiento(), LocalDate.now())
                            + "\n   Informacion contacto: " + a.getInformacionContacto() + "\n");
                    k++;
                }
            }
        }
    }

    public static void eliminar(ArrayList<AtributosComunes> lista, ArrayList<Reserva> listaReservas) {
        ArrayList<Cliente> listaClientes = new ArrayList();
        String nombre;
        int codigoBuscado = 0;
        boolean encontrado = false;
        int k = 0;
        int op = 0;
        int posk = 0;
        int tam = 0;
        boolean valido = false;
        boolean seguro = true;
        boolean opcion = false;
        String eleccion = "";
        System.out.println("Digite el nombre del cliente que desea eliminar");
        Scanner cin = new Scanner(System.in);
        nombre = cin.nextLine();
        System.out.println("Resultados de Busqueda: ");
        for (AtributosComunes p : lista) {
            if (p instanceof Cliente) {
                Cliente a = (Cliente) p;
                if (a.getNombre().contains(nombre)) {
                    listaClientes.add(a);
                    tam++;
                    encontrado = true;
                    System.out.println(k + 1 + ". Nombre: " + a.getNombre() + "\n   Codigo: " + a.getCodigo()
                            + "\n   Fecha de nacimiento: " + a.getFechaNacimiento() + "\n   Informacion contacto: "
                            + a.getInformacionContacto() + "\n");
                    k++;
                }
            }
        }
        k = 0;
        if (encontrado == true) {
            while (valido == false) {
                System.out.println("Cual de los clientes desea eliminar? Digite el numero del resultado de busqueda: ");
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
                for (Cliente p : listaClientes) {
                    if (k == op - 1) {
                        codigoBuscado = p.getCodigo();
                    }
                    k++;
                }
                System.out.println("Estas seguro? Si/No ");
                while (opcion == false) {
                    eleccion = cin.nextLine();
                    eleccion = eleccion.toLowerCase();
                    if (eleccion.equals("si")) {
                        opcion = true;
                    } else if (eleccion.equals("no")) {
                        opcion = true;
                        seguro = false;
                    } else {
                        System.out.println("Opcion invalida, vuelva a intentar");
                    }
                }
                if (seguro == true) {
                    k = 0;
                    for (AtributosComunes p : lista) {
                        if(p instanceof Cliente){
                            if (p.getCodigo() == codigoBuscado) {
                                posk = k;
                            }
                        }
                        k++;
                    }
                    if(!Reserva.verificacion(lista.get(posk), lista, listaReservas)){
                        System.out.println("No se pudo borrar, el cliente seleccionado ya que pertenece a una reserva");
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
        int k = 0;
        int posEncontrada = 0;
        int op = 0;
        int op2 = 0;
        boolean valido = false;
        boolean encontrado = false;
        boolean seguro = true;
        boolean otraVez = true;
        ArrayList<Cliente> listaClientes = new ArrayList();
        System.out.println("Digite el nombre del cliente que desea modificar");
        Scanner cin = new Scanner(System.in);
        String nombre = cin.nextLine();
        System.out.println("Resultados de Busqueda: ");
        for (AtributosComunes p : lista) {
            if (p instanceof Cliente) {
                Cliente a = (Cliente) p;
                if (a.getNombre().contains(nombre)) {
                    listaClientes.add(a);
                    tam++;
                    encontrado = true;
                    System.out.println(k + 1 + ". Nombre: " + a.getNombre() + "\n   Codigo: " + a.getCodigo()
                            + "\n   Fecha de nacimiento: " + a.getFechaNacimiento() + "\n   Informacion contacto: "
                            + a.getInformacionContacto() + "\n");
                    k++;
                }
            }
        }
        k = 0;
        if (encontrado == true) {
            while (valido == false) {
                System.out
                        .println("Cual de los clientes desea modificar? Digite el numero del resultado de busqueda: ");
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
                Cliente a = listaClientes.get(op - 1);
                for (AtributosComunes p : lista) {
                    if (a.getCodigo() == p.getCodigo()) {
                        posEncontrada = k;
                    }
                    k++;
                }
                while (otraVez == true) {
                    System.out.println(
                            "Que desea cambiar del cliente seleccionado? Digite el numero de opcion\n1. Nombre\n2. Codigo\n3. Fecha de nacimiento\n4. Informacion de contacto\n5. Salir");
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
                            System.out.println("Escriba el nuevo nombre");
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
                            System.out.println("Escriba la nueva fecha de nacimiento (yyyy-mm-dd)");
                            while (valido == false) {
                                try {
                                    a.setFechaNacimiento(LocalDate.parse(cin.nextLine()));
                                    valido = true;
                                } catch (DateTimeParseException e) {
                                    System.out
                                            .println("Error, la fecha no sigue el formato indicado, intente de nuevo");
                                }
                            }
                            break;
                        case 4:
                            System.out.println("Escriba la nueva informacion de contacto");
                            while (valido == false) {
                                try {
                                    a.setInformacionContacto(Long.parseLong(cin.nextLine()));
                                    valido = true;
                                } catch (NumberFormatException e) {
                                    System.out.println("Error, debe digitar solo numeros validos, intente de nuevo");
                                }
                            }
                            break;
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
