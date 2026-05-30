package juego;

/**
 * Clase que representa a Jinx.
 *
 */
public class Jinx extends Personajes {

    /**
     * Constructor de Jinx con sus estadísticas iniciales.
     *
     * @param identificador Identificador del personaje.
     */
    public Jinx(String identificador) {
        super("Jinx", identificador, 180, 55);
    }

    /**
     * Método que al matar a un enemigo, Jinx gana misiles permanentes.
     *
     *
     */
    public void pasivaMatar() {
        this.bonusMisiles += 10;
        System.out.println(nombre + " gana +10 misiles permanentes!");
    }
}