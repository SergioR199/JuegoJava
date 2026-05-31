package juego;

/**
 * Clase que representa a juego.Jinx.
 *
 */
public class Jinx extends Personajes {

    /**
     * Constructor de juego.Jinx con sus estadísticas iniciales.
     *
     * @param identificador Identificador del personaje.
     */
    public Jinx(String identificador) {

        super("juego.Jinx", identificador, 180, 55);
    }

    /**
     * Método que al matar a un enemigo, juego.Jinx gana misiles permanentes.
     *
     *
     */
    public void pasivaMatar() {
        this.bonusMisiles += 10;
        System.out.println(nombre + " gana +10 misiles permanentes!");
    }
}