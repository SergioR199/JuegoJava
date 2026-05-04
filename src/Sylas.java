/**
 * Clase que representa a Sylas.
 *
 */
class Sylas extends Personajes {

    private Personajes pasivaRobada = null;
    private int contadorTurnos = 0;

    /**
     * Constructor de Sylas con sus estadísticas iniciales.
     *
     */
    public Sylas() {
        super("Sylas", 190, 50);
    }

    /**
     * Método que roba la pasiva de otro personaje.
     *
     * @param objetivo Personaje del que se roba la pasiva.
     *
     */
    public void robarPasiva(Personajes objetivo) {
        this.pasivaRobada = objetivo;
        System.out.println(nombre + " ha robado la pasiva de " + objetivo.getNombre());
    }

    @Override

    /**
     * Método que ejecuta la pasiva robada cada dos turnos globales.
     *
     * @param turnoGlobal Número de turno global actual.
     * */
    public void alInicioTurnoGlobal(int turnoGlobal) {
        if (pasivaRobada == null) {
            return;
        }

        contadorTurnos++;

        if (contadorTurnos == 2) {
            contadorTurnos = 0;
            System.out.println(nombre + " activa la pasiva robada!");

            if (pasivaRobada instanceof Jinx) {
                ((Jinx) pasivaRobada).alMatar(this);
            }

            if (pasivaRobada instanceof Tristana) {
                ((Tristana) pasivaRobada).alMatar(this);
            }
        }
    }

    @Override

    /**
     * Getter de la raza del personaje.
     *
     * @return String "HUMANO".
     * */
    public String getRaza() { return "HUMANO"; }
}
