/**
 * Clase que representa a Xerath.
 *
 */
class Xerath extends Personajes {

    private boolean escudoActivo = false;

    /**
     * Constructor de Xerath con sus estadísticas iniciales.
     *
     */
    public Xerath() {
        super("Xerath", 160, 70);
    }

    @Override

    /**
     * Método que activa un escudo cuando baja de 50 vidas por primera vez.
     *
     * @param accion "atacar" o "defender"
     * @param misiles Cantidad de misiles usados en el ataque.
     * @param atacante Personaje que realiza el ataque.
     * @return Daño final recibido o 0 si el escudo se activa.
     * */
    public int accion(String accion, int misiles, Personajes atacante) {

        if (accion.equalsIgnoreCase("atacar")) {
            if (this.vidas <= 50 && !escudoActivo) {
                escudoActivo = true;
                System.out.println(nombre + " activa escudo!");
                atacante.setMisilesRonda(atacante.getMisilesRonda() - misiles);
                return 0;
            }
        }

        return super.accion(accion, misiles, atacante);
    }

    @Override

    /**
     * Getter de la raza del personaje.
     *
     * @return String "SER MAGICO".
     * */
    public String getRaza() { return "SER MAGICO"; }
}
