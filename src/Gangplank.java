/**
 * Clase que representa a Gangplank.
 *
 */
class Gangplank extends Personajes {

    /**
     * Constructor de Gangplank con sus estadísticas iniciales.
     *
     */
    public Gangplank() {
        super("Gangplank", 210, 45);
    }

    /**
     * Método que mejora la curación aplicada al defenderse.
     *
     * @param curacionBase Curación calculada inicialmente.
     * @return Curación aumentada un 50%.
     *
     */
    public int modificarCuracion(int curacionBase) {
        int curacionMejorada = (int) (curacionBase * 1.5);

        System.out.println(nombre + " mejora su curación!");
        return curacionMejorada;
    }

    @Override

    /**
     * Getter de la raza del personaje.
     *
     * @return String "HUMANO".
     * */
    public String getRaza() { return "HUMANO"; }
}
