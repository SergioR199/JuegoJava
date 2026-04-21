/**
 * Clase que representa a un personaje de tipo SerMagico.
 *
 */
abstract class SerMagico extends Personajes {

    /**
     * Constructor de un personaje de tipo SerMagico con sus estadísticas iniciales.
     *
     * @param nombre       Nombre del personaje.
     * @param vidas        Vidas iniciales.
     * @param misilesRonda Misiles iniciales por ronda.
     *
     */
    public SerMagico(String nombre, int vidas, int misilesRonda) {
        super(nombre, vidas, misilesRonda);
    }

    @Override

    /**
     * Getter del tipo del personaje.
     *
     * @return String "SER MAGICO".
     * */
    public String getTipo() {
        return "SER MAGICO";
    }
}
