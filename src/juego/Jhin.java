package juego;

/**
 * Clase que representa a Jhin.
 *
 */
public class Jhin extends Personajes {
    private int contadorAtaques = 0;

    /**
     * Constructor de Jhin con sus estadísticas iniciales.
     *
     * @param identificador Identificador del personaje.
     */
    public Jhin(String identificador) {
        super("Jhin", identificador, 170, 60);
    }

    /**
     * Método que modifica el daño para que después de cada 3 ataques, el cuarto sea crítico.
     *
     * @param danioBase Daño base del ataque.
     * @return Daño final, duplicado si es el cuarto ataque.
     *
     */
    public int modificarDanio(int danioBase) {
        contadorAtaques++;

        if (contadorAtaques == 4) {
            contadorAtaques = 0;
            System.out.println(nombre + " activa su disparo crítico!");
            return danioBase * 2;
        }

        return danioBase;
    }
}