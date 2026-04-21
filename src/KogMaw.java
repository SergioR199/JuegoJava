/**
 * Clase que representa a Kog'Maw.
 *
 */
class KogMaw extends SerMagico {

    /**
     * Constructor de Kog'Maw con sus estadísticas iniciales.
     *
     */
    public KogMaw() {
        super("Kog'Maw", 170, 60);
    }

    /**
     * Método que inflige daño al atacante cuando muere Kog'Maw.
     *
     * @param atacante Personaje que ha matado a Kog'Maw.
     *
     */
    public void alMorir(Personajes atacante) {
        atacante.vidas -= 20;
        System.out.println(nombre + " explota y hace 20 de daño!");
    }
}
