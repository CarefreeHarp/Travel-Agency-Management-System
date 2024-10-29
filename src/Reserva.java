import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reserva {
    private Cliente cliente;
    private PlanTuristico planTuristico;
    private Vuelo vuelo;
    private Hotel hotel;
    private LocalDate fechaReserva;
    private int codigo;

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public void setPlanTuristico(PlanTuristico planTuristico) {
        this.planTuristico = planTuristico;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public PlanTuristico getPlanTuristico() {
        return planTuristico;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public Reserva(Cliente cliente, PlanTuristico planTuristico, Vuelo vuelo, Hotel hotel, LocalDate fechaReserva,
            int codigo) {
        this.cliente = cliente;
        this.planTuristico = planTuristico;
        this.vuelo = vuelo;
        this.hotel = hotel;
        this.fechaReserva = fechaReserva;
        this.codigo = codigo;
    }

    public Reserva() {
    }

    public static void creacion(ArrayList<AtributosComunes> lista, ArrayList<Reserva> listaReservas) {
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        ArrayList<PlanTuristico> listaPlanTuristico = new ArrayList<>();
        ArrayList<Vuelo> listaVuelo = new ArrayList<>();
        ArrayList<Hotel> listaHotel = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd/HH:mm");
        Scanner cin = new Scanner(System.in);
        boolean encontrado = false;
        boolean valido = false;
        boolean seguro = true;
        int op = 0;
        int tam = 0;
        int k = 0;
        int i = 0;
        int codigo;
        Cliente cliente = new Cliente();
        PlanTuristico planTuristico = new PlanTuristico();
        Vuelo vuelo = new Vuelo();
        Hotel hotel = new Hotel();
        LocalDate fechaReserva = LocalDate.now();
        System.out.println("Los clientes registrados en el sistema son:");
        for (AtributosComunes p : lista) {
            if (p instanceof Cliente) {
                Cliente a = (Cliente) p;
                k++;
                tam++;
                System.out.println(k + ". Nombre: " + a.getNombre() + "\n   Codigo: " + a.getCodigo()
                        + "\n   Fecha de nacimiento: " + a.getFechaNacimiento() + "\n   Informacion contacto: "
                        + a.getInformacionContacto() + "\n");
                listaClientes.add(a);
                encontrado = true;
            }
        }
        if (encontrado == true) {
            while (valido == false) {
                System.out.println("Digite el numero de opcion del cliente que desea agregar a la reserva");
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
                cliente = listaClientes.get(op - 1);
            }
        } else {
            System.out
                    .println("No hay clientes registrados en el sistema! Registre almenos uno en el menu principal!\n");
        }
        if (encontrado == true) {
            encontrado = false;
            valido = false;
            seguro = true;
            k = 0;
            tam = 0;
            System.out.println("Desea agregar un plan turistico a la reserva? Si/No");
            if (cin.nextLine().toLowerCase().contains("si")) {
                System.out.println("Los Planes Turisticos registrados en el sistema son:");
                for (AtributosComunes p : lista) {
                    if (p instanceof PlanTuristico) {
                        PlanTuristico a = (PlanTuristico) p;
                        tam++;
                        System.out.println(k + 1 + ". Nombre: " + a.getNombre() + "\n   Codigo: " + a.getCodigo()
                                + "\n   Descripcion: " + a.getDescripcion() + "\n   Destino: " + a.getDestino()
                                + "\n   Fecha de inicio: " + a.getFechaInicio() + "\n   Fecha de fin: "
                                + a.getFechaFin() + "\n   Precio: $" + a.getPrecio() + "\n");
                        k++;
                        listaPlanTuristico.add(a);
                        encontrado = true;
                    }
                }
                if (encontrado == true) {
                    while (valido == false) {
                        System.out.println(
                                "Digite el numero de opcion del plan turistico que desea agregar a la reserva");
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
                        planTuristico = listaPlanTuristico.get(op - 1);
                    }
                } else {
                    System.out.println("No hay planes turisticos registrados en el sistema!\n");
                }
                encontrado = false;
                valido = false;
                seguro = true;
                k = 0;
                tam = 0;
            }
            System.out.println("Desea agregar un vuelo a la reserva? Si/No");
            if (cin.nextLine().toLowerCase().contains("si")) {
                System.out.println("Los vuelos registrados en el sistema son:");
                for (AtributosComunes p : lista) {
                    if (p instanceof Vuelo) {
                        Vuelo a = (Vuelo) p;
                        k++;
                        tam++;
                        System.out.println(
                                k + ". Aerolinea: " + a.getNombre() + "\n   Codigo: " + a.getCodigo() + "\n   Precio: "
                                        + a.getPrecio() + "\n   Fecha y hora del vuelo: " + a.getFechaHora().format(dtf)
                                        + "\n   Origen: " + a.getOrigen() + "\n   Destino: " + a.getDestino());
                        listaVuelo.add(a);
                        encontrado = true;
                    }
                }
                if (encontrado == true) {
                    while (valido == false) {
                        System.out.println("Digite el numero de opcion del vuelo que desea agregar a la reserva");
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
                        vuelo = listaVuelo.get(op - 1);
                    }
                } else {
                    System.out.println("No hay vuelos registrados en el sistema!\n");
                }
                encontrado = false;
                valido = false;
                seguro = true;
                k = 0;
                tam = 0;
            }
            System.out.println("Desea agregar un hotel a la reserva? Si/No");
            if (cin.nextLine().toLowerCase().contains("si")) {
                System.out.println("Los hoteles registrados en el sistema son:");
                for (AtributosComunes p : lista) {
                    if (p instanceof Hotel) {
                        Hotel a = (Hotel) p;
                        k++;
                        tam++;
                        System.out.println(k + ". Nombre: " + a.getNombre() + "\n   Codigo: " + a.getCodigo()
                                + "\n   Ubicacion: " + a.getUbicacion() + "\n   Categoria: " + a.getCategoria()
                                + "\n   Precio por noche:" + a.getPrecioNoche());
                        listaHotel.add(a);
                        encontrado = true;
                    }
                }
                if (encontrado == true) {
                    while (valido == false) {
                        System.out.println("Digite el numero de opcion del hotel que desea agregar a la reserva");
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
                        hotel = listaHotel.get(op - 1);
                    }
                } else {
                    System.out.println("No hay hoteles registrados en el sistema!\n");
                }
                encontrado = false;
                valido = false;
                seguro = true;
                k = 0;
                tam = 0;
            }
            System.out.println("Cual es la fecha de la reserva? (yyyy-mm-dd)");
            while (valido == false) {
                try {
                    fechaReserva = LocalDate.parse(cin.nextLine());
                    valido = true;
                } catch (DateTimeParseException e) {
                    System.out.println("La fecha no cumple el formato adecuado");
                }
            }
            for (Reserva p : listaReservas) {
                i++;
            }
            codigo = i;
            listaReservas.add(new Reserva(cliente, planTuristico, vuelo, hotel, fechaReserva, codigo));
        }
    }

    public static void modificacion(ArrayList<AtributosComunes> lista, ArrayList<Reserva> listaReservas) {
        int tam = 0;
        int k = 0;
        int posEncontrada = 0;
        int op = 0;
        int op2 = 0;
        boolean valido = false;
        boolean encontrado = false;
        boolean seguro = true;
        boolean otraVez = true;
        Cliente cliente = new Cliente();
        ArrayList<Cliente> listaClientes = new ArrayList();
        ArrayList<Reserva> listaReservasAux = new ArrayList<>();
        System.out.println("Digite el nombre del cliente que tiene la reserva que desea modificar");
        Scanner cin = new Scanner(System.in);
        String nombre = cin.nextLine();
        System.out.println("Resultados de Busqueda: ");
        for (Reserva p : listaReservas) {
            if (p.getCliente().getNombre().contains(nombre)) {
                listaReservasAux.add(p);
                tam++;
                encontrado = true;
                System.out.println(k + 1 + ". - Cliente:\n     Nombre: " + p.getCliente().getNombre() +
                        "\n     Codigo: " + p.getCliente().getCodigo() +
                        "\n     Fecha de nacimiento: " + p.getCliente().getFechaNacimiento() +
                        "\n     Informacion de contacto: " + p.getCliente().getInformacionContacto() +
                        "\n   - Plan Turistico:\n     Nombre: " + p.getPlanTuristico().getNombre() +
                        "\n     Codigo: " + p.getPlanTuristico().getCodigo() +
                        "\n     Descripcion: " + p.getPlanTuristico().getDescripcion() +
                        "\n     Destino: " + p.getPlanTuristico().getDestino() +
                        "\n     Fecha de inicio: " + p.getPlanTuristico().getFechaInicio() +
                        "\n     Fecha de fin: " + p.getPlanTuristico().getFechaFin() +
                        "\n     Precio: " + p.getPlanTuristico().getPrecio() +
                        "\n   - Vuelo:\n     Codigo: " + p.getVuelo().getCodigo() +
                        "\n     Aerolinea: " + p.getVuelo().getNombre() +
                        "\n     Origen: " + p.getVuelo().getOrigen() +
                        "\n     Destino: " + p.getVuelo().getDestino() +
                        "\n     Fecha y hora: " + p.getVuelo().getFechaHora() +
                        "\n     Precio: " + p.getVuelo().getPrecio() +
                        "\n   - Hotel:\n     Nombre: " + p.getHotel().getNombre() +
                        "\n     Codigo: " + p.getHotel().getCodigo() +
                        "\n     Ubicacion: " + p.getHotel().getUbicacion() +
                        "\n     Categoria: " + p.getHotel().getCategoria() +
                        " Estrellas\n     Precio por noche: " + p.getHotel().getPrecioNoche() +
                        "\n   - Fecha de Reserva: " + p.getFechaReserva());
                k++;
            }
        }
        k = 0;
        if (encontrado == true) {
            while (valido == false) {
                System.out
                        .println("Cual de las reservas desea modificar? Digite el numero del resultado de busqueda: ");
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
                Reserva a = listaReservasAux.get(op - 1);
                for (AtributosComunes p : lista) {
                    if (a.getCodigo() == p.getCodigo()) {
                        posEncontrada = k;
                    }
                    k++;
                }
                k=0;
                while (otraVez == true) {
                    System.out.println(
                            "Que desea cambiar de la reserva seleccionada? Digite el numero de opcion\n1. El cliente\n2. El plan turistico\n3. El vuelo\n4. El Hotel\n5. Fecha de reserva\n6. Salir");
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
                            for (AtributosComunes p : lista) {
                                if (p instanceof Cliente) {
                                    Cliente b = (Cliente) p;
                                    k++;
                                    tam++;
                                    System.out.println(k + ". Nombre: " + b.getNombre() + "\n   Codigo: " + b.getCodigo()
                                            + "\n   Fecha de nacimiento: " + b.getFechaNacimiento() + "\n   Informacion contacto: "
                                            + b.getInformacionContacto() + "\n");
                                    listaClientes.add(b);
                                    encontrado = true;
                                }
                            }
                            if (encontrado == true) {
                                while (valido == false) {
                                    System.out.println("Digite el numero de opcion del cliente para hacer el cambio en la reserva");
                                    try {
                                        op2 = Integer.parseInt(cin.nextLine());
                                        if (op2 > tam || op2 < 1) {
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
                                    a.setCliente(listaClientes.get(op2 - 1));
                                }
                            }
                            break;
                        case 2:
                            
                            break;
                        case 3:
                            
                            break;
                        case 4:
                            
                            break;
                        default:
                            otraVez = false;
                            break;
                    }
                }
                listaReservas.set(posEncontrada, a);
            }
        } else {
            System.out.println("No se encontraron reservas en el sistema!");
        }

    }

    public static void cancelacion(ArrayList<AtributosComunes> Lista, ArrayList<Reserva> listaReservas) {

    }
}
