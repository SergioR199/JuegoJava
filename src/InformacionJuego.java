import java.util.Scanner;

/**
 * Clase encargada de mostrar toda la información relacionada con el juego.
 * */
public class InformacionJuego {
    private static final String[] PERSONAJES = {
            "Jinx", "Jhin", "Gangplank", "Rumble",
            "Heimerdinger", "Kog'Maw", "Aurelion Sol", "Sylas",
            "Tristana", "Xerath", "Ziggs", "Corki"
    };

    /**
     * Método que muestra las reglas del juego.
     *
     */
    public static void mostrarReglas() {
        System.out.println("\n--- REGLAS DEL JUEGO ---\n" +
                "1. La partida se juega con un mínimo de 3 equipos.\n" +
                "2. Cada equipo comienza con las vidas y los misiles por ronda determinados por su personaje.\n" +
                "3. Cada personaje tiene estadísticas base propias.\n" +
                "4. Los YORDLES tienen un 25% de probabilidad de esquivar ataques.\n" +
                "5. Los HUMANOS hacen 1,25x de daño a los YORDLES.\n" +
                "6. Los SERES MAGICOS hacen 1,75x de daño a HUMANOS y 0,75x a YORDLES.\n" +
                "7. En la primera ronda solo se puede atacar.\n" +
                "8. El daño se aplica al final de la ronda, después de que todos jueguen.\n" +
                "9. A partir de la ronda 2 puedes atacar o defenderte.\n" +
                "10. Primero eliges a quién atacas y luego cuántos misiles usas.\n" +
                "11. Al final de cada ronda se muestra un resumen.\n" +
                "12. El último equipo en pie gana la partida.\n");
    }

    /**
     * Método que muestra la información general del juego.
     *
     */
    public static void mostrarInformacionGeneral() {
        System.out.println("\n--- INFORMACIÓN GENERAL ---\n" +
                "Versión: 2.1\n" +
                "Contacto: losdelfondo@gmail.com\n" +
                "Autores: Adrián Vera, Agustín Olivera, Ángel Brito, Mauro Barros, Sergio Rial\n");
    }

    /**
     * Método que muestra la lista de personajes disponibles.
     *
     */
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
     *
     */
    public static void mostrarInfoPersonajes(Scanner sc) {
        int personaje;

        do {
            mostrarPersonajes();
            System.out.println("0- Salir");
            personaje = Main.leerEnteroRango(sc, 0, PERSONAJES.length, "Escoge el número del personaje: ");

            switch (personaje) {
                case 1:
                    System.out.println("Jinx - HUMANO | Vidas: 180 | Misiles: 55\n" +
                            "Supermegacohete Requetemortal!: al matar gana +10 misiles permanentes.\n");
                    break;
                case 2:
                    System.out.println("Jhin - HUMANO | Vidas: 170 | Misiles: 60\n" +
                            "Uno,Dos,Tres...CUATRO!: cada 4 ataques hace daño crítico x2.\n");
                    break;
                case 3:
                    System.out.println("Gangplank - HUMANO | Vidas: 210 | Misiles: 45\n" +
                            "Vitamina C: su curación es un 75% en vez de 50%.\n");
                    break;
                case 4:
                    System.out.println("Rumble - YORDLE | Vidas: 160 | Misiles: 50\n" +
                            "Mechero: aplica quemadura (5 de daño por ronda al enemigo).\n");
                    break;
                case 5:
                    System.out.println("Heimerdinger - YORDLE | Vidas: 150 | Misiles: 55\n" +
                            "Eureka!: cada 2 rondas coloca una torreta que hace 10 de daño global.\n");
                    break;
                case 6:
                    System.out.println("Kog'Maw - SER MAGICO | Vidas: 170 | Misiles: 60\n" +
                            "Avanzamos sin descanso!: al morir hace 20 de daño al atacante.\n");
                    break;
                case 7:
                    System.out.println("Aurelion Sol - SER MAGICO | Vidas: 220 | Misiles: 45\n" +
                            "Experiencia Galactica: gana +5 de daño permanente cada turno.\n");
                    break;
                case 8:
                    System.out.println("Sylas - HUMANO | Vidas: 190 | Misiles: 50\n" +
                            "Matarreyes:Cada 2 turnos, Sylas absorbe poder del enemigo con más vida, robándole 10 de vida y hasta 5 misiles.\n");
                    break;
                case 9:
                    System.out.println("Tristana - YORDLE | Vidas: 155 | Misiles: 60\n" +
                            "Sanguinaria: al matar se cura 15 de vida.\n");
                    break;
                case 10:
                    System.out.println("Xerath - SER MAGICO | Vidas: 160 | Misiles: 70\n" +
                            "Reloj de arena de Zhonya: si baja de 50 de vida, bloquea el siguiente ataque.\n");
                    break;
                case 11:
                    System.out.println("Ziggs - YORDLE | Vidas: 145 | Misiles: 65\n" +
                            "Megabomba incendiaria: Hace daño en area.\n");
                    break;
                case 12:
                    System.out.println("Corki - YORDLE | Vidas: 165 | Misiles: 55\n" +
                            "Bombas fuera!: cada 4 turnos lanza un misil que hace 75 daño y 10 en área.\n");
                    break;
            }

            System.out.println();

        } while (personaje != 0);
    }
}