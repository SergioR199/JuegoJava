/**
 * Clase que representa a un Ser Mágico personalizado.
 *
 */
class SerMagicoPersonalizado extends SerMagico {

    /**
     * Contructor de un Ser Mágico personalizado con el nombre y estadísticas indicadas.
     *
     * @param nombre       Nombre del personaje.
     * @param vidas        Vidas iniciales.
     * @param misilesRonda Misiles iniciales por ronda.
     *
     */
    public SerMagicoPersonalizado(String nombre, int vidas, int misilesRonda) {
        super(nombre, vidas, misilesRonda);
    }
}
