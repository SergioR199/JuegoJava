/**
 * Clase que representa a Heimerdinger.
 *
 */
class Heimerdinger extends Personajes {

    private int contador = 0;

    /**
     * Constructor de Heimerdinger con sus estadísticas iniciales.
     *
     */
    public Heimerdinger() {
        super("Heimerdinger", 150, 55);
    }

    @Override

    /**
     * Método que coloca una torreta cada dos turnos globales e inflige daño a los enemigos vivos.
     *
     * @param turnoGlobal Número de turno global actual.
     * */
    public void alInicioTurnoGlobal(int turnoGlobal) {
        contador++;

        if (contador == 2) {
            contador = 0;

            System.out.println(nombre + " coloca una torreta!");

            for (Personajes p : Main.equipos) {
                if (p.getVidas() > 0 && p != this) {
                    p.vidas -= 10;
                    System.out.println(p.getNombre() + " recibe 10 de daño de la torreta");
                }
            }
        }
    }

    @Override

    /**
     * Getter de la raza del personaje.
     *
     * @return String "YORDLE".
     * */
    public String getRaza() { return "YORDLE"; }
}
