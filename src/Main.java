import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        //comentario

        do {
            try {
                System.out.println("---- MENU ----");
                System.out.println("1º JUGAR");
                System.out.println("2º REGLAS DEL JUEGO");
                System.out.println("3º INFORMACIÓN GENERAL");
                System.out.println("4º INFORMACIÓN DE PERSONAJES");
                System.out.println("0º SALIR");
                opcion = sc.nextInt();
            }catch (InputMismatchException e){
                System.out.print("Has introducido un valor no válido. Elige otra opción (0-4)");
            }

            if (opcion < 0 || opcion > 4){
                System.out.print("Has introducido una opción no existente. Elige otra opción (0-4)");
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
                    infoPersonajes(sc);
                    break;
            }

        } while (opcion != 0);
    }

    // Función para mostrar la información de los personajes
    public static void infoPersonajes (Scanner sc){
        int personaje = 0;

        do {
            try {
                System.out.println("---- PERSONAJES ----");
                System.out.println("1-  Jinx ");
                System.out.println("2-  Jhin");
                System.out.println("3-  Gangplank");
                System.out.println("4-  Rumble");
                System.out.println("5-  Heimerdinger");
                System.out.println("6-  Kog'Maw");
                System.out.println("7-  Aurelion Sol");
                System.out.println("8-  Sylas");
                System.out.println("9-  Tristana");
                System.out.println("10- Xerath");
                System.out.println("11- Ziggs");
                System.out.println("12- Corki");
                System.out.println("0-  Salir");
                System.out.println("Escoge el número del personaje: ");
                personaje = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Has introducido un valor no válido. Elige otra opción (0-12)");
            }

            if (personaje < 0 || personaje > 12) {
                System.out.print("Has introducido una opción no existente. Elige otra opción (0-12)");
            }


            switch (personaje) {
                case 1:
                    System.out.println("");
                    break;

                case 2:
                    System.out.println("");
                    break;

                case 3:
                    System.out.println("");
                    break;

                case 4:
                    System.out.println("");
                    break;

                case 5:
                    System.out.println("");
                    break;

                case 6:
                    System.out.println("");
                    break;

                case 7:
                    System.out.println("");
                    break;

                case 8:
                    System.out.println("");
                    break;

                case 9:
                    System.out.println("");
                    break;

                case 10:
                    System.out.println("");
                    break;

                case 11:
                    System.out.println("");
                    break;

                case 12:
                    System.out.println("");
                    break;
            }
        }while (personaje != 0);
    }

}