package juego;

/**
 * Clase que representa a juego.Rumble.
 *
 */
public class Rumble extends Personajes {

    /**
     * Constructor de juego.Rumble con sus estadísticas iniciales.
     *
     * @param identificador Identificador del personaje.
     */
    public Rumble(String identificador) {
        super("juego.Rumble", identificador, 160, 50);
    }

    /**
     * Método que aplica quemadura al objetivo atacado tras atacar.
     *
     * @param equipos Array con todos los personajes de la partida.
     * @param indiceObjetivo Índice del objetivo atacado.
     *
     */
    public void alAtacar(Personajes[] equipos, int indiceObjetivo) {
        if (indiceObjetivo >= 0 && indiceObjetivo < equipos.length &&
                equipos[indiceObjetivo].getVidas() > 0) {

            equipos[indiceObjetivo].quemadura = 5;
            System.out.println(equipos[indiceObjetivo].getNombre() +
                    " está quemándose! (-5 de vida al inicio de cada turno)");
        }
    }
}