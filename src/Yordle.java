/**
 * Clase que representa a un personaje de tipo Yordle.
 *
 */
abstract class Yordle extends Personajes {

    /**
     * Constructor de un personaje Yordle con sus estadísticas iniciales.
     *
     * @param nombre       Nombre del personaje.
     * @param vidas        Vidas iniciales.
     * @param misilesRonda Misiles iniciales por ronda.
     *
     */
    public Yordle(String nombre, int vidas, int misilesRonda) {
        super(nombre, vidas, misilesRonda);
    }

    @Override

    /**
     * Getter del tipo del personaje.
     *
     * @return String "YORDLE".
     * */
    public String getTipo() {
        return "YORDLE";
    }
}
