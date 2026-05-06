/**
 * Clase que representa a Tristana.
 *
 */
public class Tristana extends Personajes {

    /**
     * Constructor de Tristana con sus estadísticas iniciales.
     *
     * @param identificador Identificador del personaje.
     */
    public Tristana(String identificador) {
        super("Tristana", identificador, 155, 60);
    }

    /**
     * Método que cura vida al matar a un enemigo.
     *
     * @param objetivo Personaje eliminado.
     *
     */
    public void alMatar(Personajes objetivo) {
        this.vidas += 15;

        if (this.vidas > this.vidaMaxima) {
            this.vidas = this.vidaMaxima;
        }

        System.out.println(nombre + " se cura 15 de vida!");
    }

    /**
     * Getter de la raza del personaje.
     *
     * @return String "YORDLE".
     * */
    @Override
    public String getRaza() {
        return "YORDLE";
    }
}