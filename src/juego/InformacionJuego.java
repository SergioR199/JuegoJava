package juego;

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
     * @return Reglas del juego.
     */
    public static String getReglas() {
        return "1. La partida se juega con un mínimo de 3 equipos.\n" +
                "2. Cada equipo comienza con las vidas y los misiles por ronda determinados por su personaje.\n" +
                "3. Cada personaje tiene estadísticas base propias e identificador propio.\n" +
                "4. Los YORDLES tienen un 25% de probabilidad de esquivar ataques.\n" +
                "5. Los HUMANOS hacen 1,25x de daño a los YORDLES.\n" +
                "6. Los SERES MAGICOS hacen 1,75x de daño a HUMANOS y 0,75x a YORDLES.\n" +
                "7. En la primera ronda solo se puede atacar.\n" +
                "8. El daño se aplica inmediatamente al realizarse el ataque.\n" +
                "9. A partir de la ronda 2 puedes atacar o defenderte.\n" +
                "10. Primero eliges cuántos misiles usas y luego a quién atacas.\n" +
                "11. Al final de cada ronda se muestra un resumen.\n" +
                "12. El último equipo en pie gana la partida.\n";
    }

    /**
     * Método que muestra la información general del juego.
     *
     * @return Información general.
     */
    public static String getInformacionGeneral() {
        return "Versión: 2.1\n" +
                "Contacto: losdelfondo@gmail.com\n" +
                "Autores: Adrián Vera, Agustín Olivera, Ángel Brito, Mauro Barros, Sergio Rial\n";
    }

    /**
     * Método que muestra la lista de personajes disponibles.
     *
     * @return Lista de personajes.
     */
    public static String getListaPersonajes() {
        String texto = "\n---- PERSONAJES ----\n";

        for (int i = 0; i < PERSONAJES.length; i++) {
            texto += (i + 1) + "- " + PERSONAJES[i] + "\n";
        }

        texto += "13- Personalizado\n";
        texto += "14- Aleatorio\n";

        return texto;
    }

    /**
     * Método que devuelve la información de un personaje concreto según su número.
     *
     * @param nombre Nombre del personaje.
     * @return Información del personaje o mensaje de error.
     */
    public static String getInfoPersonaje(String nombre) {
        switch (nombre) {
            case "Jinx":
                return "Jinx - HUMANO | Vidas: 180 | Misiles: 55\n" +
                        "Supermegacohete Requetemortal!: al matar gana +10 misiles permanentes.\n";
            case "Jhin":
                return "Jhin - HUMANO | Vidas: 170 | Misiles: 60\n" +
                        "Uno, Dos, Tres... CUATRO!: cada 4 ataques hace daño crítico x2.\n";
            case "Gangplank":
                return "Gangplank - HUMANO | Vidas: 210 | Misiles: 45\n" +
                        "Vitamina C: su curación es un 50% mayor.\n";
            case "Rumble":
                return "Rumble - YORDLE | Vidas: 160 | Misiles: 50\n" +
                        "Mechero: aplica quemadura de 5 al enemigo.\n";
            case "Heimerdinger":
                return "Heimerdinger - YORDLE | Vidas: 150 | Misiles: 55\n" +
                        "Eureka!: cada 2 rondas coloca una torreta que hace 10 de daño global.\n";
            case "Kog'Maw":
                return "Kog'Maw - SER MAGICO | Vidas: 170 | Misiles: 60\n" +
                        "Avanzamos sin descanso!: al morir hace 20 de daño al atacante.\n";
            case "Aurelion Sol":
                return "Aurelion Sol - SER MAGICO | Vidas: 220 | Misiles: 45\n" +
                        "Experiencia Galáctica: gana +5 de daño permanente cada turno.\n";
            case "Sylas":
                return "Sylas - HUMANO | Vidas: 190 | Misiles: 50\n" +
                        "Matarreyes: cada 2 turnos roba 10 de vida y hasta 5 misiles al enemigo con más vida.\n";
            case "Tristana":
                return "Tristana - YORDLE | Vidas: 155 | Misiles: 60\n" +
                        "Sanguinaria: al matar se cura 15 de vida.\n";
            case "Xerath":
                return "Xerath - SER MAGICO | Vidas: 160 | Misiles: 70\n" +
                        "Reloj de arena de Zhonya: si baja de 50 de vida, bloquea el siguiente ataque.\n";
            case "Ziggs":
                return "Ziggs - YORDLE | Vidas: 145 | Misiles: 65\n" +
                        "Megabomba incendiaria: hace daño en área.\n";
            case "Corki":
                return "Corki - YORDLE | Vidas: 165 | Misiles: 55\n" +
                        "Bombas fuera!: cada 4 turnos lanza un misil que hace 75 daño y 10 en área.\n";
            case "Personalizado":
                return "Personalizado\n" +
                        "Puedes crear un personaje propio y elegir su raza.\n";
            case "Aleatorio":
                return "Aleatorio\n" +
                        "Se elige automáticamente un personaje disponible.\n";
            default:
                return "Personaje no válido.";
        }
    }
}