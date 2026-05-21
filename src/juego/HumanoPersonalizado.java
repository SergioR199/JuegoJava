package juego;

/**
 * Clase que representa a un Humano personalizado.
 *
 */
public class HumanoPersonalizado extends Personajes {

    /**
     * Constructor de un humano personalizado con el nombre y estadísticas indicadas.
     *
     * @param nombre Nombre del personaje.
     * @param identificador Identificador del personaje.
     * @param vidas Vidas iniciales.
     * @param misilesRonda Misiles iniciales por ronda.
     *
     */
    public HumanoPersonalizado(String nombre, String identificador, int vidas, int misilesRonda) {
        super(nombre, identificador, vidas, misilesRonda);
    }
}