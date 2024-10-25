import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        int opcion = 0;
        Cliente hola = new Cliente();

        ArrayList<AtributosComunes> lista = new ArrayList();
        Scanner cin = new Scanner(System.in);
        
        while(true){   
            System.out.println("Digite la opcion");
        opcion = Integer.parseInt(cin.nextLine());
        switch (opcion) {
            case 1:
                hola.registro(lista);
                break;
            case 2:
                hola.buscar(lista);
                break;
            case 3:
                hola.eliminar(lista);
                break;
            default:
                break;
        }
    }
    }
}
