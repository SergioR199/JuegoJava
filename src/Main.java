import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("---- MENU ----");
            System.out.println("JUGAR => 1");
            System.out.println("REGLAS DEL JUEGO => 2");
            System.out.println("INFORMACION => 3");
            System.out.println("APARTADO ABIERTO => 4");
            System.out.println("SALIR => 0");
            opcion = sc.nextInt();

        } while (opcion != 0);
    }

}