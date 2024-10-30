import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MetodosDeArchivos {

    public static void guardar(ArrayList<AtributosComunes> lista, ArrayList<Reserva> listaReservas) {
        Path path = Paths.get("");
        File directorio = new File(path.toAbsolutePath().toString() + File.separator + "Archivos");
        if (!directorio.exists()) {
            if (!directorio.mkdir()) {
                System.out.println("Error al crear la carpeta de archivos");
                System.exit(1);
            }
        }
        File archivoBinario = new File(
                path.toAbsolutePath().toString() + File.separator + "Archivos" + File.separator + "TodasLasClases.dat");
        if (!archivoBinario.exists()) {
            try {
                archivoBinario.createNewFile();
            } catch (IOException e) {
                System.out.println("Error al crear el archivo binario");
                System.exit(1);
            }
        }
        try {
            FileOutputStream archivoBin = new FileOutputStream(path.toAbsolutePath().toString() + File.separator
                    + "Archivos" + File.separator + "TodasLasClases.dat");
            if (archivoBinario.length() == 0) {
                ObjectOutputStream escritorArchivo = new ObjectOutputStream(archivoBin);
                for (AtributosComunes p : lista) {
                    escritorArchivo.writeObject(p);
                }
                escritorArchivo.close();
            } else {
                AppObjectOutputStream escritorArchivo = new AppObjectOutputStream(archivoBin);
                for (AtributosComunes p : lista) {
                    escritorArchivo.writeObject(p);
                }
                escritorArchivo.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error, el archivo binario no fue encontrado");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Error, hubo un problema escribiendo en el archivo binario");
            System.exit(1);
        }
        File archivoBinarioReserva = new File(
                path.toAbsolutePath().toString() + File.separator + "Archivos" + File.separator + "ClaseReserva.dat");
        if (!archivoBinarioReserva.exists()) {
            try {
                archivoBinarioReserva.createNewFile();
            } catch (IOException e) {
                System.out.println("Error al crear el archivo binario");
                System.exit(1);
            }
        }
        try {
            FileOutputStream archivoBin = new FileOutputStream(path.toAbsolutePath().toString() + File.separator
                    + "Archivos" + File.separator + "ClaseReserva.dat");
            if (archivoBinarioReserva.length() == 0) {
                ObjectOutputStream escritorArchivo = new ObjectOutputStream(archivoBin);
                for (Reserva p : listaReservas) {
                    escritorArchivo.writeObject(p);
                }
                escritorArchivo.close();
            } else {
                AppObjectOutputStream escritorArchivo = new AppObjectOutputStream(archivoBin);
                for (Reserva p : listaReservas) {
                    escritorArchivo.writeObject(p);
                }
                escritorArchivo.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error, el archivo binario no fue encontrado");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Error, hubo un problema escribiendo en el archivo binario");
            System.exit(1);
        }
    }

    public static void cargar(ArrayList<AtributosComunes> lista, ArrayList<Reserva> listaReservas) {
        Path path = Paths.get("");
        File directorio = new File(path.toAbsolutePath().toString() + File.separator + "Archivos");
        if (!directorio.exists()) {
            if (!directorio.mkdir()) {
                System.out.println("Error al crear la carpeta de archivos");
                System.exit(1);
            }
        }
        File archivoBinario = new File(
                path.toAbsolutePath().toString() + File.separator + "Archivos" + File.separator + "TodasLasClases.dat");
        if (!archivoBinario.exists()) {
            try {
                archivoBinario.createNewFile();
            } catch (IOException e) {
                System.out.println("Error al crear el archivo binario");
                System.exit(1);
            }
        }
        try {
            FileInputStream archivoBin = new FileInputStream(path.toAbsolutePath().toString() + File.separator
                    + "Archivos" + File.separator + "TodasLasClases.dat");
            ObjectInputStream lectorArchivo = new ObjectInputStream(archivoBin);
            AtributosComunes a = (AtributosComunes) lectorArchivo.readObject();
            while (a != null) {
                lista.add(a);
                a = (AtributosComunes) lectorArchivo.readObject();
            }
            lectorArchivo.close();
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
            System.out.println("Hubo un error leyendo el archivo binario");
            System.exit(1);
        }
        File archivoBinarioReserva = new File(
                path.toAbsolutePath().toString() + File.separator + "Archivos" + File.separator + "ClaseReserva.dat");
        if (!archivoBinarioReserva.exists()) {
            try {
                archivoBinarioReserva.createNewFile();
            } catch (IOException e) {
                System.out.println("Error al crear el archivo binario");
                System.exit(1);
            }
        }
        try {
            FileInputStream archivoBin = new FileInputStream(path.toAbsolutePath().toString() + File.separator
                    + "Archivos" + File.separator + "ClaseReserva.dat");
            ObjectInputStream lectorArchivo = new ObjectInputStream(archivoBin);
            Reserva a = (Reserva) lectorArchivo.readObject();
            while (a != null) {
                listaReservas.add(a);
                a = (Reserva) lectorArchivo.readObject();
            }
            lectorArchivo.close();
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
            System.out.println("Hubo un error leyendo el archivo binario");
            System.exit(1);
        }
    }

    public static void informeReservas(ArrayList<Reserva> listaReservas) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        int i = 0;
        Path path = Paths.get("");
        File directorio = new File(path.toAbsolutePath().toString() + File.separator + "Archivos");
        if (!directorio.exists()) {
            if (!directorio.mkdir()) {
                System.out.println("Error al crear la carpeta de archivos");
                System.exit(1);
            }
        }
        File informeReserva = new File(
                path.toAbsolutePath().toString() + File.separator + "Archivos" + File.separator + "informeReserva.txt");
        if (!informeReserva.exists()) {
            try {
                informeReserva.createNewFile();
            } catch (IOException e) {
                System.out.println("Error al crear el archivo de texto");
                System.exit(1);
            }
        }
        try {
            FileWriter outFile = new FileWriter(path.toAbsolutePath().toString() + File.separator + "Archivos"
                    + File.separator + "informeReserva.txt");
            BufferedWriter archivo = new BufferedWriter(outFile);
            for (Reserva p : listaReservas) {
                i++;
                archivo.write(i + ".Nombre del cliente: " + p.getCliente().getNombre());
                        if(!p.getPlanTuristico().getNombre().equals("")){
                            archivo.write("\nNombre del plan: "
                            + p.getPlanTuristico().getNombre() + "\nFecha de inicio: "
                            + p.getPlanTuristico().getFechaInicio().format(dtf2) + "\nFecha de fin: "
                            + p.getPlanTuristico().getFechaFin().format(dtf2));
                        } else {
                            archivo.write("\nNombre del plan: - \nFecha de inicio: - \nFecha de fin: - ");
                        }
                        if(!p.getVuelo().getNombre().equals("")){
                            archivo.write("\nCodigo del vuelo: "
                            + p.getVuelo().getDestino() + "\nOrigen: " + p.getVuelo().getOrigen() + "\nDestino: "
                            + p.getVuelo().getDestino() + "\nFecha y hora del vuelo: "
                            + p.getVuelo().getFechaHora().format(dtf) + "\nPrecio: " + p.getVuelo().getPrecio());
                        } else {
                            archivo.write("\nCodigo del vuelo: -\nOrigen: -\nDestino: -\nFecha y hora del vuelo: -\nPrecio: -");
                        }
                        if(!p.getHotel().getNombre().equals("")){
                            archivo.write("\nNombre del hotel: " + p.getHotel().getNombre() 
                            + "\nUbicacion del hotel: "+ p.getHotel().getUbicacion());
                        } else {
                            archivo.write("\nNombre del hotel: -\nUbicacion del hotel: -");
                        }
                        archivo.write("\n\n\n");
            }
            archivo.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error, el archivo binario no fue encontrado");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Error, hubo un problema escribiendo en el archivo binario");
            System.exit(1);
        }
        System.out.println("Informe escrito!! Revise la carpeta donde esta el src y alli encontrara la carpeta de archivos!!\n\n\n");
    }
}
