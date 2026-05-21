package juego;

/**
 * Clase que representa a un Yordle personalizado.
 *
 */
public class YordlePersonalizado extends Personajes {

    /**
     * Constructor de un Yordle personalizado con el nombre y estadísticas indicadas.
     *
     * @param nombre Nombre del personaje.
     * @param identificador Identificador del personaje.
     * @param vidas Vidas iniciales.
     * @param misilesRonda Misiles iniciales por ronda.
     *
     */
    public YordlePersonalizado(String nombre, String identificador, int vidas, int misilesRonda) {
        super(nombre, identificador, vidas, misilesRonda);
    }
}