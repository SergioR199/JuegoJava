package juego;

/**
 * Clase que representa a Corki.
 *
 */
public class Corki extends Personajes {

    private int contadorTurnos = 0;

    /**
     * Constructor de Corki con sus estadísticas iniciales.
     *
     * @param identificador Identificador del personaje.
     */
    public Corki(String identificador) {
        super("Corki", identificador, 165, 55);
    }

    /**
     * Método que lanza un misil cada cuatro turnos globales e inflige daño en área.
     *
     * @param ronda Número de turno actual.
     * */
    @Override
    public void pasivaRonda(int ronda) {
        super.pasivaRonda(ronda);

        contadorTurnos++;

        if (contadorTurnos == 4) {
            contadorTurnos = 0;

            Main.agregarMensajePasiva(nombre + " lanza un misil hextech!");

            Personajes[] equipo = Main.equipos;
            int indiceObjetivo = -1;

            for (int i = 0; i < equipo.length; i++) {
                if (equipo[i] != this && equipo[i].getVidas() > 0) {
                    indiceObjetivo = i;
                    break;
                }
            }

            if (indiceObjetivo == -1) {
                return;
            }

            equipo[indiceObjetivo].vidas -= 75;
            Main.agregarMensajePasiva(equipo[indiceObjetivo].getNombre() + " recibe 75 de daño.");

            if (equipo[indiceObjetivo].getVidas() <= 0) {
                equipo[indiceObjetivo].vidas = 0;
                Main.agregarMensajePasiva(equipo[indiceObjetivo].getNombre() + " ha sido destruido!");
            }

            for (int i = 0; i < equipo.length; i++) {
                if (i != indiceObjetivo && equipo[i] != this && equipo[i].getVidas() > 0) {
                    equipo[i].vidas -= 10;
                    if (equipo[i].vidas <= 0) {
                        equipo[i].vidas = 0;
                        Main.agregarMensajePasiva(equipo[i].getNombre() + " ha sido destruido por la explosión!");
                    } else {
                        Main.agregarMensajePasiva(equipo[i].getNombre() + " recibe 10 de daño en área.");
                    }
                }
            }
        }
    }
}