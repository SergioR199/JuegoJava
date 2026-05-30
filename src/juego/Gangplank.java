package juego;

/**
 * Clase que representa a juego.Gangplank.
 *
 */
public class Gangplank extends Personajes {

    /**
     * Constructor de juego.Gangplank con sus estadísticas iniciales.
     *
     * @param identificador Identificador del personaje.
     */
    public Gangplank(String identificador) {
        super("juego.Gangplank", identificador, 210, 45);
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

        System.out.println(nombre + " mejora su curación!");
        return curacionMejorada;
    }
}