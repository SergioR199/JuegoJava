/**
 * Clase que representa a Aurelion Sol.
 *
 */
class AurelionSol extends SerMagico {

    /**
     * Constructor de Aurelion Sol con sus estadísticas iniciales.
     *
     */
    public AurelionSol() {
        super("Aurelion Sol", 220, 45);
    }

    @Override

    /**
     * Método que aumenta permanentemente el daño del personaje al inicio de cada turno global.
     *
     * @param turnoGlobal Número de turno global actual.
     * */
    public void alInicioTurnoGlobal(int turnoGlobal) {
        bonusDanio += 5;
        System.out.println(nombre + " gana +5 daño permanente!");
    }
}
