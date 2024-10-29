import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Hotel extends AtributosComunes {
    private String ubicacion="";
    private int categoria = 0;
    private int precioNoche = 0;

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setCategoria(int categoria) throws ExcepcionMasDeCincoEstrellas{
        if(categoria > 5 || categoria < 1){
            throw new ExcepcionMasDeCincoEstrellas("El valor debe estar entre 1 y 5");
        }
        this.categoria = categoria;
    }

    public void setPrecioNoche(int precioNoche) {
        this.precioNoche = precioNoche;
    }

    public int getCategoria() {
        return categoria;
    }

    public int getPrecioNoche() {
        return precioNoche;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public Hotel(String nombre, int codigo, String ubicacion, int categoria, int precioNoche) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.ubicacion = ubicacion;
        this.categoria = categoria;
        this.precioNoche = precioNoche;
    }

    public Hotel() {
    }

    public static void registro(ArrayList<AtributosComunes> lista) {
        Scanner cin = new Scanner(System.in);
        Hotel a = new Hotel();
        System.out.println("Digite el nombre del hotel");
        a.setNombre(cin.nextLine());
        boolean valido = false;
        System.out.println("Digite el codigo de identificacion del hotel:");
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
        System.out.println("Digite la ubicacion");
        a.setUbicacion(cin.nextLine());
        System.out.println("Digite el numero de estrellas");
        while (valido == false) {
            try {
                a.setCategoria(Integer.parseInt(cin.nextLine()));
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Error, solo puede digitar numeros, intente de nuevo");
                valido = false;
            }catch(ExcepcionMasDeCincoEstrellas e){
                System.out.println("El valor debe estar entre 1 y 5");
            }
        }
        valido = false;
        System.out.println("Digite el precio por noche");
        while (valido == false) {
            try {
                a.setPrecioNoche(Integer.parseInt(cin.nextLine()));
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
        System.out.println("Digite el nombre del hotel");
        Scanner cin = new Scanner(System.in);
        nombre = cin.nextLine();
        System.out.println("Hoteles encontrados con nombre: " + nombre);
        for (AtributosComunes p : lista) {
            if (p instanceof Hotel) {
                Hotel a = (Hotel) p;
                if (a.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                    System.out.println(k + 1 + ". Nombre: " + a.getNombre() + "\n   Codigo: " + a.getCodigo()
                            + "\n   Ubicacion: " + a.getUbicacion() + "\n   Categoria: " + a.getCategoria()
                            + "\n   Precio por noche:" + a.getPrecioNoche());
                    k++;
                }
            }
        }
    }

    public static void eliminar(ArrayList<AtributosComunes> lista) {
        ArrayList<Hotel> listaHoteles = new ArrayList();
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
        System.out.println("Digite el nombre del hotel que desea eliminar");
        Scanner cin = new Scanner(System.in);
        nombre = cin.nextLine();
        System.out.println("Resultados de Busqueda: ");
        for (AtributosComunes p : lista) {
            if (p instanceof Hotel) {
                Hotel a = (Hotel) p;
                if (a.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                    listaHoteles.add(a);
                    tam++;
                    encontrado = true;
                    System.out.println(k + 1 + ". Nombre: " + a.getNombre() + "\n   Codigo: " + a.getCodigo()
                            + "\n   Ubicacion: " + a.getUbicacion() + "\n   Categoria: " + a.getCategoria()
                            + "\n   Precio por noche:" + a.getPrecioNoche());
                    k++;
                }
            }
        }
        k = 0;
        if (encontrado == true) {
            while (valido == false) {
                System.out.println("Cual de los hoteles desea eliminar? Digite el numero del resultado de busqueda: ");
                try {
                    op = Integer.parseInt(cin.nextLine());
                    if (op > tam|| op<1) {
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
                for (Hotel p : listaHoteles) {
                    if (k == op - 1) {
                        codigoBuscado = p.getCodigo();
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
                        if (p.getCodigo() == codigoBuscado) {
                            posk = k;
                        }
                        k++;
                    }
                    lista.remove(posk);
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
        ArrayList<Hotel> listaHoteles = new ArrayList<>();
        System.out.println("Digite el nombre del hotel que desea modificar");
        Scanner cin = new Scanner(System.in);
        String nombre = cin.nextLine();
        System.out.println("Resultados de Busqueda: ");
        for (AtributosComunes p : lista) {
            if (p instanceof Hotel) {
                Hotel a = (Hotel) p;
                if (a.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                    listaHoteles.add(a);
                    tam++;
                    encontrado = true;
                    System.out.println(k + 1 + ". Nombre: " + a.getNombre() + "\n   Codigo: " + a.getCodigo()
                            + "\n   Ubicacion: " + a.getUbicacion() + "\n   Categoria: " + a.getCategoria()
                            + "\n   Precio por noche:" + a.getPrecioNoche());
                    k++;
                }
            }
        }
        k = 0;
        if (encontrado == true) {
            while (valido == false) {
                System.out.println("Cual de los hoteles desea modificar? Digite el numero del resultado de busqueda: ");
                try {
                    op = Integer.parseInt(cin.nextLine());
                    if (op > tam|| op<1) {
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
                Hotel a = listaHoteles.get(op - 1);
                for (AtributosComunes p : lista) {
                    if (a.getCodigo() == p.getCodigo()) {
                        posEncontrada = k;
                    }
                    k++;
                }
                while (otraVez == true) {
                    System.out.println(
                            "Que desea cambiar del hotel seleccionado? Digite el numero de opcion\n1. Nombre\n2. Codigo\n3. Ubicacion\n4. Categoria\n5. Precio por noche\n6. Salir");
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
                        System.out.println("Escriba la nueva ubicacion");
                        a.setUbicacion(cin.nextLine());
                            break;
                        case 4:
                            System.out.println("Escriba la nueva cantidad de estrellas");
                            while (valido == false) {
                                try {
                                    a.setCategoria(Integer.parseInt(cin.nextLine()));
                                    valido = true;
                                } catch (DateTimeParseException e) {
                                    System.out
                                            .println("Error, la fecha no sigue el formato indicado, intente de nuevo");
                                }catch(ExcepcionMasDeCincoEstrellas e){
                                    System.out.println("El valor debe estar entre 1 y 5");
                                }
                            }
                            break;
                        case 5:
                        System.out.println("Escriba el nuevo precio");
                        while (valido == false) {
                            try {
                                a.setPrecioNoche(Integer.parseInt(cin.nextLine()));
                                valido = true;
                            } catch (DateTimeParseException e) {
                                System.out
                                        .println("Solo puede ingresar numeros");
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
    public boolean estaVacio(){
        return nombre.equals("");
    }
}
