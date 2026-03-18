import java.util.*;

class Equipo {
    String nombre;
    int vidas;
    int misilesPorRonda;
}

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        // array de 5 equipos
        Equipo[] equipos = new Equipo[5];

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
                    System.out.println("Saliendo del juego");
                    break;

                case 1:
                    System.out.println("Creando equipos");
                    crearEquipos(equipos,sc);
                    inicializarVidas(equipos);
                    inicializarMisiles(equipos);

                    break;

                case 2:
                    mostrarReglas();
                    break;

                case 3:
                    mostrarInformacion();
                    break;

                case 4:
                    System.out.println("Apartado abierto")
                    break;
            }

        } while (opcion != 0);
    }
    public static void crearEquipos(Equipo[] equipos, Scanner sc) {
        for (int i = 0; i < equipos.length; i++) {
            equipos[i] = new Equipo();
            System.out.print("Nombre del jugador " + (i + 1) + ": ");
            equipos[i].nombre = sc.nextLine();
        }
    }
    // 2º método: inicializar vidas automáticamente
    public static void inicializarVidas(Equipo[] equipos) {
        int vidasIniciales = 200;
        for (int i = 0; i < equipos.length; i++) {
            equipos[i].vidas = vidasIniciales;
        }
    }
    // 3º método: inicializar misiles por ronda automáticamente
    public static void inicializarMisiles(Equipo[] equipos) {
        int misilesIniciales = 50; //
        for (int i = 0; i < equipos.length; i++) {
            equipos[i].misilesPorRonda = misilesIniciales;
        }
    }
    // Método auxiliar para mostrar equipos creados
    public static void mostrarEquipos(Equipo[] equipos) {
        System.out.println("RONDA 1\n");
        for (int i = 0; i < equipos.length; i++) {
            System.out.println(equipos[i].getNombre() + " - " + equipos[i].getVidas() + " vidas");
        }
    }
    // Método para mostrar reglas
    public static void mostrarReglas() {
        System.out.println("\n--- REGLAS DEL JUEGO ---");
        System.out.println("1. Cada equipo comienza con 200 vidas y 50 misiles por ronda.");
        System.out.println("2. Los equipos atacan alternadamente.");
        System.out.println("3. Puedes atacar a otro equipo o defenderte.");
        System.out.println("4. El último equipo en pie gana la partida.");
    }

    // Método para mostrar información
    public static void mostrarInformacion() {
        System.out.println("\n--- INFORMACIÓN ---");
        System.out.println("Versión: 1.0");
        System.out.println("Contacto: coldwar@email.com");
        System.out.println("Autores: Equipo DAM");
    }
}