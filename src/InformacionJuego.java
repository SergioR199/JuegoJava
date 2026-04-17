import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase encargada de mostrar toda la información relacionada con el juego.
 * */
public class InformacionJuego {
    private static final String[] PERSONAJES = {
            "Jinx", "Jhin", "Gangplank", "Sylas",
            "Rumble", "Heimerdinger", "Tristana",
            "Corki", "Ziggs", "Xerath", "Kog'Maw", "Aurelion Sol"
    };

    /**
     * Método que muestra las reglas del juego.
     * */
    public static void mostrarReglas() {
        System.out.println("\n--- REGLAS DEL JUEGO ---");
        System.out.println("1. La partida se juega con entre 3 y 5 equipos.");
        System.out.println("2. Cada equipo comienza con 200 vidas y 50 misiles por ronda.");
        System.out.println("3. En la primera ronda solo se puede atacar.");
        System.out.println("4. El daño se aplica al final de la ronda, después de que todos jueguen.");
        System.out.println("5. A partir de la ronda 2 puedes atacar o defenderte.");
        System.out.println("6. Primero eliges a quién atacas y luego cuántos misiles usas.");
        System.out.println("7. Al final de cada ronda se muestra un resumen.");
        System.out.println("8. El último equipo en pie gana la partida.\n");
    }

    /**
     * Método que muestra la información general del juego.
     * */
    public static void mostrarInformacionGeneral() {
        System.out.println("\n--- INFORMACIÓN GENERAL ---");
        System.out.println("Versión: 1.0");
        System.out.println("Contacto: losdelfondo@gmail.com");
        System.out.println("Autores: Adrián Vera, Agustín Olivera, Ángel Brito, Mauro Barros, Sergio Rial\n");
    }

    /**
     * Método que muestra la lista de personajes disponibles.
     * */
    public static void mostrarPersonajes() {
        System.out.println("\n---- PERSONAJES ----");
        for (int i = 0; i < PERSONAJES.length; i++) {
            System.out.println((i + 1) + "- " + PERSONAJES[i]);
        }
    }

    /**
     * Método que permite consultar la información del personaje que se solicite.
     *
     * @param sc Scanner que se utiliza para leer el número que introduce el usuario
     * */
    public static void mostrarInfoPersonajes(Scanner sc) {
        int personaje;

        do {
            mostrarPersonajes();
            System.out.println("0- Salir");
            personaje = leerEnteroRango(sc, 0, PERSONAJES.length, "Escoge el número del personaje: ");

            switch (personaje) {
                case 1:
                    System.out.println("Jinx - Explosiva y caótica tiradora de apoyo a distancia. \n");
                    break;
                case 2:
                    System.out.println("Jhin - Artista asesino con cuatro disparos explosivos.");
                    break;
                case 3:
                    System.out.println("Gangplank - Pirata francotirador con mucho poder de área.");
                    break;
                case 4:
                    System.out.println("Sylas - Mago rebelde que roba habilidades enemigas.");
                    break;
                case 5:
                    System.out.println("Rumble - Yordle mecánico con un dron de combate eléctrico.");
                    break;
                case 6:
                    System.out.println("Heimerdinger - Yordle científico prototipo que usa campos y torretas.");
                    break;
                case 7:
                    System.out.println("Tristana - Yordle artillera con salto y explosión.");
                    break;
                case 8:
                    System.out.println("Corki - Piloto de combate con misiles y bombas.");
                    break;
                case 9:
                    System.out.println("Ziggs - Yordle ingeniero explosivo especializado en zonas.");
                    break;
                case 10:
                    System.out.println("Xerath - Mago de largo alcance con poder masivo.");
                    break;
                case 11:
                    System.out.println("Kog'Maw - Devorador de estrellas con gran alcance.");
                    break;
                case 12:
                    System.out.println("Aurelion Sol - Dragón cósmico que controla el espacio.");
                    break;
                case 0:
                    System.out.println("Saliendo de información de personajes...");
                    break;
            }

            System.out.println();

        } while (personaje != 0);
    }

    /**
     * Método que devuelve el nombre del personaje según su índice.
     *
     * @param indice Índice del personaje en el array
     * @return Nombre del personaje
     * */
    public static String getPersonaje(int indice) {
        return PERSONAJES[indice];
    }

    /**
     * Método que devuelve el número de personajes disponibles.
     *
     * @return Cantidad de personajes
     * */
    public static int getTotalPersonajes() {
        return PERSONAJES.length;
    }

    /**
     * Método que lee el número entero que introduce el usuario dentro del rango definido,
     * teniendo que repetir hasta que se introduzca un valor válido.
     *
     * @param sc Scanner que se utiliza para leer el número que introduce el usuario
     * @param min Valor mínimo
     * @param max Valor máximo
     * @param mensaje Mensaje que se le muestra al usuario
     * @return Número entero válido
     * */
    private static int leerEnteroRango(Scanner sc, int min, int max, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                int valor = sc.nextInt();
                sc.nextLine();

                if (valor < min || valor > max) {
                    System.out.println("Elige un número entre " + min + " y " + max + ".");
                } else {
                    return valor;
                }

            } catch (InputMismatchException e) {
                System.out.println("Valor no válido. Introduce un número entero.");
                sc.nextLine();
            }
        }
    }
}