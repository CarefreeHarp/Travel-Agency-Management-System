import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        int opcion = 0;
        Cliente hola = new Cliente();
        ArrayList<AtributosComunes> lista = new ArrayList();
        Scanner cin = new Scanner(System.in);
        while(true){   
            System.out.println("Digite la opcion \n1. Gestionar Clientes \n2. Gestionar Planes Turisticos");
            opcion = Integer.parseInt(cin.nextLine());
        switch (opcion) {
            case 1:
                System.out.println("Digite la opcion \n1. Registrar clientes \n2. Buscar clientes \n3. Modificar cliente \n4. Eliminar clientes");
                opcion = Integer.parseInt(cin.nextLine());
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
                        Cliente.eliminar(lista);
                        break;
                    default:
                        break;
                }
                break;
            case 2:
                System.out.println("Digite la opcion \n1. Registrar planes turisticos \n2. Buscar planes turisticos \n3. Modificar planes turisticos \n4. Eliminar planes turisticos");
                opcion = Integer.parseInt(cin.nextLine());
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
                        PlanTuristico.eliminar(lista);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }
    }
}
