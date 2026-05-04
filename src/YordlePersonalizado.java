/**
 * Clase que representa a un Yordle personalizado.
 *
 */
class YordlePersonalizado extends Personajes {

    /**
     * Constructor de un Yordle personalizado con el nombre y estadísticas indicadas.
     *
     * @param nombre       Nombre del personaje.
     * @param vidas        Vidas iniciales.
     * @param misilesRonda Misiles iniciales por ronda.
     *
     */
    public YordlePersonalizado(String nombre, int vidas, int misilesRonda) {
        super(nombre, vidas, misilesRonda);
    }

    @Override

    /**
     * Getter de la raza del personaje.
     *
     * @return String "YORDLE".
     * */
    public String getRaza() { return "YORDLE"; }
}
