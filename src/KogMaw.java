/**
 * Clase que representa a Kog'Maw.
 *
 */
public class KogMaw extends Personajes {

    /**
     * Constructor de Kog'Maw con sus estadísticas iniciales.
     *
     * @param identificador Identificador del personaje.
     */
    public KogMaw(String identificador) {
        super("Kog'Maw", identificador, 170, 60);
    }

    /**
     * Método que inflige daño al atacante cuando muere Kog'Maw.
     *
     * @param atacante Personaje que ha matado a Kog'Maw.
     *
     */
    public void alMorir(Personajes atacante) {
        atacante.vidas -= 20;
        if (atacante.vidas < 0) {
            atacante.vidas = 0;
        }
        System.out.println(nombre + " explota y hace 20 de daño!");
    }

    /**
     * Getter de la raza del personaje.
     *
     * @return String "SER MAGICO".
     * */
    @Override
    public String getRaza() {
        return "SER MAGICO";
    }
}