import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        //comentario

        do {
            try {
                System.out.println("---- MENU ----");
                System.out.println("1º JUGAR");
                System.out.println("2º REGLAS DEL JUEGO");
                System.out.println("3º INFORMACIÓN");
                System.out.println("4º APARTADO ABIERTO");
                System.out.println("0º SALIR");
                opcion = sc.nextInt();
            }catch (InputMismatchException e){
                System.out.print("Has introducido un valor no válido. Elige otra opción (0-4)")
            }

            if (opcion < 0 || opcion > 4){
                System.out.print("Has introducido una opción no existente. Elige otra opción (0-4)")
            }

            switch (opcion){
                case 0:

                    break;

                case 1:

                    break;

                case 2:

                    break;

                case 3:

                    break;

                case 4:

                    break;
            }

        } while (opcion != 0);
    }

}