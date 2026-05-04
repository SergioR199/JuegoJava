/**
 * Clase que representa a Rumble.
 *
 */
class Rumble extends Personajes {

    /**
     * Constructor de Rumble con sus estadísticas iniciales.
     *
     */
    public Rumble() {
        super("Rumble", 160, 50);
    }

    /**
     * Método que aplica quemadura al objetivo atacado tras atacar.
     *
     * @param equipos        Array con todos los personajes de la partida.
     * @param indiceObjetivo Índice del objetivo atacado.
     * @param danioBase      Daño base del ataque.
     *
     */
    public void alAtacar(Personajes[] equipos, int indiceObjetivo, int danioBase) {
        if (indiceObjetivo >= 0 && indiceObjetivo < equipos.length &&
                equipos[indiceObjetivo].getVidas() > 0) {

            equipos[indiceObjetivo].quemadura += 5;
            System.out.println(equipos[indiceObjetivo].getNombre() +
                    " está quemándose! (Quemadura total: " +
                    equipos[indiceObjetivo].quemadura + ")");
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
