package juego;

/**
 * Clase que representa a juego.Tristana.
 *
 */
public class Tristana extends Personajes {

    /**
     * Constructor de juego.Tristana con sus estadísticas iniciales.
     *
     * @param identificador Identificador del personaje.
     */
    public Tristana(String identificador) {
        super("juego.Tristana", identificador, 155, 60);
    }

    /**
     * Método que cura vida al matar a un enemigo.
     *
     *
     */
    public void pasivaMatar() {
        this.vidas += 15;

        if (this.vidas > this.vidaMaxima) {
            this.vidas = this.vidaMaxima;
        }

        System.out.println(nombre + " se cura 15 de vida!");
    }
}