/**
 * Clase que representa a un personaje de tipo Humano.
 *
 */
abstract class Humano extends Personajes {

    /**
     * Constructor de un personaje Humano con sus estadísticas iniciales.
     *
     * @param nombre       Nombre del personaje.
     * @param vidas        Vidas iniciales.
     * @param misilesRonda Misiles iniciales por ronda.
     *
     */
    public Humano(String nombre, int vidas, int misilesRonda) {
        super(nombre, vidas, misilesRonda);
    }

    @Override

    /**
     * Getter del tipo del personaje.
     *
     * @return String "HUMANO".
     * */
    public String getTipo() {
        return "HUMANO";
    }
}
