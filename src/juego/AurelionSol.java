package juego;

/**
 * Clase que representa a Aurelion Sol.
 *
 */
public class AurelionSol extends Personajes {

    /**
     * Constructor de Aurelion Sol con sus estadísticas iniciales.
     *
     * @param identificador Identificador del personaje.
     */
    public AurelionSol(String identificador) {
        super("Aurelion Sol", identificador, 220, 45);
    }

    /**
     * Método que aumenta permanentemente el daño del personaje al inicio de cada turno global.
     *
     * @param ronda Número de turno actual.
     * */
    @Override
    public void pasivaRonda(int ronda) {
        super.pasivaRonda(ronda);
        misilesBase += 5;
        Main.agregarMensajePasiva(nombre + " gana +5 daño permanente!");
    }
}