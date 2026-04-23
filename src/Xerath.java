/**
 * Clase que representa a Xerath.
 *
 */
class Xerath extends SerMagico {

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
     * @param misiles Cantidad de misiles usados en el ataque.
     * @param atacante Personaje que realiza el ataque.
     * @return Daño final recibido o 0 si el escudo se activa.
     * */
    public int combate(int misiles, Personajes atacante) {

        if (this.vidas <= 50 && !escudoActivo) {
            escudoActivo = true;
            System.out.println(nombre + " activa escudo!");
            return 0;
        }

        return super.combate(misiles, atacante);
    }
}
