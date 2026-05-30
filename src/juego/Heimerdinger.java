package juego;

/**
 * Clase que representa a Heimerdinger.
 *
 */
public class Heimerdinger extends Personajes {

    private int contador = 0;

    /**
     * Constructor de Heimerdinger con sus estadísticas iniciales.
     *
     * @param identificador Identificador del personaje.
     */
    public Heimerdinger(String identificador) {
        super("Heimerdinger", identificador, 150, 55);
    }

    /**
     * Método que coloca una torreta cada dos turnos globales e inflige daño a los enemigos vivos.
     *
     * @param ronda Número de turno actual.
     * */
    @Override
    public void pasivaRonda(int ronda) {
        contador++;

        if (contador == 2) {
            contador = 0;

            System.out.println(nombre + " coloca una torreta!");

            for (Personajes p : Main.equipos) {
                if (p.getVidas() > 0 && p != this) {
                    p.vidas -= 10;
                    if (p.vidas < 0) {
                        p.vidas = 0;
                    }
                    System.out.println(p.getNombre() + " recibe 10 de daño de la torreta");
                }
            }
        }
    }
}