package juego;

/**
 * Clase que representa a Gangplank.
 *
 */
public class Gangplank extends Personajes {

    /**
     * Constructor de Gangplank con sus estadísticas iniciales.
     *
     * @param identificador Identificador del personaje.
     */
    public Gangplank(String identificador) {
        super("Gangplank", identificador, 210, 45);
    }

    /**
     * Método que mejora la curación aplicada al defenderse.
     *
     * @param curacionBase Curación calculada inicialmente.
     * @return Curación aumentada un 50%.
     *
     */
    public int pasivaCuracion(int curacionBase) {
        int curacionMejorada = (int) (curacionBase * 1.5);

        Main.agregarMensajePasiva(nombre + " mejora su curación!");
        return curacionMejorada;
    }
}