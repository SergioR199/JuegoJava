/**
 * Clase que representa a Tristana.
 *
 */
class Tristana extends Yordle {

    /**
     * Constructor de Tristana con sus estadísticas iniciales.
     *
     */
    public Tristana() {
        super("Tristana", 155, 60);
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
}
