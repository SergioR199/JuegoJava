import java.util.*;

class Equipo {
    String nombre;
    int vidas;
    int misilesPorRonda;

    public String getNombre() {
        return nombre;
    }

    public int getVidas() {
        return vidas;
    }

    public int getMisilesPorRonda() {
        return misilesPorRonda;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        // array de 5 equipos
        Equipo[] equipos = new Equipo[5];

        do {
            try {
                System.out.println("---- MENU ----");
                System.out.println("1º JUGAR");
                System.out.println("2º REGLAS DEL JUEGO");
                System.out.println("3º INFORMACIÓN GENERAL");
                System.out.println("4º INFORMACIÓN DE PERSONAJES");
                System.out.println("0º SALIR");
                opcion = sc.nextInt();

                // Consumir salto de línea después de nextInt
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Has introducido un valor no válido. Elige otra opción (0-4)");
                sc.nextLine(); // limpiar buffer
            }

            if (opcion < 0 || opcion > 4) {
                System.out.println("Has introducido una opción no existente. Elige otra opción (0-4)");
            }

            switch (opcion) {
                case 0:
                    System.out.println("Saliendo del juego");
                    break;

                case 1:
                    System.out.println("Creando equipos");
                    crearEquipos(equipos, sc);
                    inicializarVidas(equipos);
                    inicializarMisiles(equipos);
                    mostrarEquipos(equipos);
                    break;

                case 2:
                    mostrarReglas();
                    break;

                case 3:
                    mostrarInformacion();
                    break;

                case 4:
                    infoPersonajes(sc);
                    break;
            }

        } while (opcion != 0);
        sc.close();
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
        int misilesIniciales = 50;
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

    // Función para mostrar la información de los personajes
    public static void infoPersonajes(Scanner sc) {
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
                System.out.print("Escoge el número del personaje: ");
                personaje = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Has introducido un valor no válido. Elige otra opción (0-12)");
                sc.nextLine(); // limpiar buffer
            }

            if (personaje < 0 || personaje > 12) {
                System.out.println("Has introducido una opción no existente. Elige otra opción (0-12)");
            }

            switch (personaje) {
                case 1:
                    System.out.println("Jinx - Explosiva y caótica tiradora de apoyo a distancia.");
                    break;
                case 2:
                    System.out.println("Jhin - Artista asesino que dispara en cuatro rondas.");
                    break;
                case 3:
                    System.out.println("Gangplank - Pirata francotirador con mucho poder de área.");
                    break;
                case 4:
                    System.out.println("Rumble - Mecánico con un dron de combate eléctrico.");
                    break;
                case 5:
                    System.out.println("Heimerdinger - Científico prototipo que usa campos y torretas.");
                    break;
                case 6:
                    System.out.println("Kog'Maw - Devorador de estrellas con gran alcance.");
                    break;
                case 7:
                    System.out.println("Aurelion Sol - Dragón cósmico que controla el espacio.");
                    break;
                case 8:
                    System.out.println("Sylas - Mago rebelde que roba habilidades enemigas.");
                    break;
                case 9:
                    System.out.println("Tristana - Goblin artillera con salto y explosión.");
                    break;
                case 10:
                    System.out.println("Xerath - Mago de largo alcance con poder masivo.");
                    break;
                case 11:
                    System.out.println("Ziggs - Ingeniero explosivo especializado en zonas.");
                    break;
                case 12:
                    System.out.println("Corki - Piloto de combate con misiles y bombas.");
                    break;
            }
        } while (personaje != 0);
    }
}
