/**
 * Clase que representa a Corki.
 *
 */
class Corki extends Yordle {

    private int contadorTurnos = 0;

    /**
     * Constructor de Corki con sus estadísticas iniciales.
     *
     */
    public Corki() {
        super("Corki", 165, 55);
    }

    @Override

    /**
     * Método que lanza un misil cada cuatro turnos globales e inflige daño en área.
     *
     * @param turnoGlobal Número de turno global actual.
     * */
    public void alInicioTurnoGlobal(int turnoGlobal) {

        contadorTurnos++;

        if (contadorTurnos == 4) {
            contadorTurnos = 0;

            System.out.println(nombre + " lanza un misil hextech!");

            Personajes objetivo = null;

            Personajes[] equipo = Main.equipos;

            int indiceObjetivo = -1;

            for (int i = 0; i < equipo.length; i++) {
                if (!equipo[i].equals(this) && equipo[i].getVidas() > 0) {
                    indiceObjetivo = i;
                    break;
                }
            }

            if (indiceObjetivo == -1) return;

            equipo[indiceObjetivo].vidas -= 75;

            System.out.println(equipo[indiceObjetivo].getNombre() + " recibe 75 de daño.");

            if (equipo[indiceObjetivo].getVidas() <= 0) {
                equipo[indiceObjetivo].vidas = 0;
                System.out.println(equipo[indiceObjetivo].getNombre() + " ha sido destruido!");
            }

            for (int i = 0; i < equipo.length; i++) {
                if (i != indiceObjetivo && equipo[i].getVidas() > 0 && !equipo[i].equals(this)) {

                    equipo[i].vidas -= 10;

                    System.out.println(equipo[i].getNombre() + " recibe 10 de daño en área.");

                    if (equipo[i].getVidas() <= 0) {
                        equipo[i].vidas = 0;
                        System.out.println(equipo[i].getNombre() + " ha sido destruido por la explosión!");
                    }
                }
            }
        }
    }
}
