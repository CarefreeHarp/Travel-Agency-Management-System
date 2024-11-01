
/*
Integrantes: Guillermo Aponte, Daniel Ramirez
Grupo: 4
Taller: Proyecto Java
Fecha: 10//11/2024
*/
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int opcion = 0;
        boolean valido = false;
        ArrayList<AtributosComunes> lista = new ArrayList<>();
        ArrayList<Reserva> listaReservas = new ArrayList<>();
        MetodosDeArchivos.cargar(lista,listaReservas);
        Scanner cin = new Scanner(System.in);
        while (opcion != 8) {
            System.out.println(
                    "Digite la opcion \n1. Gestionar Clientes \n2. Gestionar Planes Turisticos \n3. Gestionar Vuelos\n4. Gestionar aerolineas\n5. Gestionar hoteles\n6. Gestionar Reservas\n7. Hacer el informe de reservas en el archivo de texto\n8. Salir");
            while (valido == false) {
                try {
                    opcion = Integer.parseInt(cin.nextLine());
                    valido = true;
                } catch (NumberFormatException e) {
                    System.out.println("Error, solo puede digitar numeros, intente de nuevo");
                }
            }
            valido = false;
            switch (opcion) {
                case 1:
                    System.out.println(
                            "Digite la opcion \n1. Registrar clientes \n2. Buscar clientes \n3. Modificar cliente \n4. Eliminar clientes");
                    while (valido == false) {
                        try {
                            opcion = Integer.parseInt(cin.nextLine());
                            valido = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Error, solo puede digitar numeros, intente de nuevo");
                        }
                    }
                    valido = false;
                    switch (opcion) {
                        case 1:
                            Cliente.registro(lista);
                            break;
                        case 2:
                            Cliente.buscar(lista);
                            break;
                        case 3:
                            Cliente.modificar(lista);
                            break;
                        case 4:
                            Cliente.eliminar(lista,listaReservas);
                            break;
                        default:
                            break;
                    }
                    MetodosDeArchivos.guardar(lista,listaReservas);
                    break;
                case 2:
                    System.out.println(
                            "Digite la opcion \n1. Registrar planes turisticos \n2. Buscar planes turisticos \n3. Modificar planes turisticos \n4. Eliminar planes turisticos");
                    while (valido == false) {
                        try {
                            opcion = Integer.parseInt(cin.nextLine());
                            valido = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Error, solo puede digitar numeros, intente de nuevo");
                        }
                    }
                    valido = false;
                    switch (opcion) {
                        case 1:
                            PlanTuristico.registro(lista);
                            break;
                        case 2:
                            PlanTuristico.buscar(lista);
                            break;
                        case 3:
                            PlanTuristico.modificar(lista);
                            break;
                        case 4:
                            PlanTuristico.eliminar(lista,listaReservas);
                            break;
                        default:
                            break;
                    }
                    MetodosDeArchivos.guardar(lista,listaReservas);
                    break;
                case 3:
                    System.out.println(
                            "Digite la opcion \n1. Registrar vuelos \n2. Buscar vuelos \n3. Modificar vuelos \n4. Eliminar vuelos");
                    while (valido == false) {
                        try {
                            opcion = Integer.parseInt(cin.nextLine());
                            valido = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Error, solo puede digitar numeros, intente de nuevo");
                        }
                    }
                    valido = false;
                    switch (opcion) {
                        case 1:
                            Vuelo.registro(lista);
                            break;
                        case 2:
                            Vuelo.buscar(lista);
                            break;
                        case 3:
                            Vuelo.modificar(lista);
                            break;
                        case 4:
                            Vuelo.eliminar(lista,listaReservas);
                            break;
                        default:
                            break;
                    }
                    MetodosDeArchivos.guardar(lista,listaReservas);
                    break;
                case 4:
                    System.out.println(
                            "Digite la opcion \n1. Registrar aerolineas \n2. Buscar aerolineas \n3. Modificar aerolineas \n4. Eliminar aerolineas");
                    while (valido == false) {
                        try {
                            opcion = Integer.parseInt(cin.nextLine());
                            valido = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Error, solo puede digitar numeros, intente de nuevo");
                        }
                    }
                    valido = false;
                    switch (opcion) {
                        case 1:
                            Aerolinea.registro(lista);
                            break;
                        case 2:
                            Aerolinea.buscar(lista);
                            break;
                        case 3:
                            Aerolinea.modificar(lista);
                            break;
                        case 4:
                            Aerolinea.eliminar(lista,listaReservas);
                            break;
                        default:
                            break;
                    }
                    MetodosDeArchivos.guardar(lista,listaReservas);
                    break;
                case 5:
                    System.out.println(
                            "Digite la opcion \n1. Registrar hoteles \n2. Buscar hoteles \n3. Modificar hoteles \n4. Eliminar hoteles");
                    while (valido == false) {
                        try {
                            opcion = Integer.parseInt(cin.nextLine());
                            valido = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Error, solo puede digitar numeros, intente de nuevo");
                        }
                    }
                    valido = false;
                    switch (opcion) {
                        case 1:
                            Hotel.registro(lista);
                            break;
                        case 2:
                            Hotel.buscar(lista);
                            break;
                        case 3:
                            Hotel.modificar(lista);
                            break;
                        case 4:
                            Hotel.eliminar(lista,listaReservas);
                            break;
                        default:
                            break;
                    }
                    MetodosDeArchivos.guardar(lista,listaReservas);
                    break;
                case 6:
                    System.out.println("Digite la opcion \n1. Creacion\n2. Modificacion\n3. Cancelacion");
                    while (valido == false) {
                        try {
                            opcion = Integer.parseInt(cin.nextLine());
                            valido = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Error, solo puede digitar numeros, intente de nuevo");
                        }
                    }
                    valido = false;
                    switch (opcion) {
                        case 1:
                            Reserva.creacion(lista, listaReservas);
                            break;
                        case 2:
                            Reserva.modificacion(lista, listaReservas);
                            break;
                        case 3:
                            Reserva.cancelacion(lista, listaReservas);
                            break;
                        default:
                            break;
                    }
                    MetodosDeArchivos.guardar(lista,listaReservas);
                    break;
                case 7:
                    MetodosDeArchivos.informeReservas(listaReservas);
                    break;
                default:
                    opcion = 8;
                    break;
            }
        }
        cin.close();
   }
}                                                                                              
