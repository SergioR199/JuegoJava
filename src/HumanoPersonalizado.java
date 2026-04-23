/**
 * Clase que representa a un Humano personalizado.
 *
 */
class HumanoPersonalizado extends Humano {

    /**
     * Constructor de un humano personalizado con el nombre y estadísticas indicadas.
     *
     * @param nombre       Nombre del personaje.
     * @param vidas        Vidas iniciales.
     * @param misilesRonda Misiles iniciales por ronda.
     *
     */
    public HumanoPersonalizado(String nombre, int vidas, int misilesRonda) {
        super(nombre, vidas, misilesRonda);
    }
}
