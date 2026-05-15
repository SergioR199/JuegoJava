/**
 * Clase que representa a un Ser Mágico personalizado.
 *
 */
public class SerMagicoPersonalizado extends Personajes {

    /**
     * Constructor de un Ser Mágico personalizado con el nombre y estadísticas indicadas.
     *
     * @param nombre Nombre del personaje.
     * @param identificador Identificador del personaje.
     * @param vidas Vidas iniciales.
     * @param misilesRonda Misiles iniciales por ronda.
     *
     */
    public SerMagicoPersonalizado(String nombre, String identificador, int vidas, int misilesRonda) {
        super(nombre, identificador, vidas, misilesRonda);
    }
}