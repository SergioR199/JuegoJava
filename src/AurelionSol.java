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
     * @param turnoGlobal Número de turno global actual.
     * */
    @Override
    public void alInicioTurnoGlobal(int turnoGlobal) {
        bonusDanio += 5;
        System.out.println(nombre + " gana +5 daño permanente!");
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