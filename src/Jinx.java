/**
 * Clase que representa a Jinx.
 *
 */
class Jinx extends Personajes {

    /**
     * Constructor de Jinx con sus estadísticas iniciales.
     *
     */
    public Jinx() {
        super("Jinx", 180, 55);
    }

    /**
     * Método que al matar a un enemigo, Jinx gana misiles permanentes.
     *
     * @param objetivo Personaje eliminado por Jinx.
     *
     */
    public void alMatar(Personajes objetivo) {
        this.bonusMisiles += 10;
        System.out.println(nombre + " gana +10 misiles permanentes!");
    }

    @Override

    /**
     * Getter de la raza del personaje.
     *
     * @return String "HUMANO".
     * */
    public String getRaza() { return "HUMANO"; }
}
