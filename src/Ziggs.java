/**
 * Clase que representa a Ziggs.
 *
 */
class Ziggs extends Yordle {

    /**
     * Constructor de Ziggs con sus estadísticas iniciales.
     *
     */
    public Ziggs() {
        super("Ziggs", 145, 65);
    }

    /**
     * Método que inflige daño en área al atacar.
     *
     * @param equipos        Array con todos los personajes de la partida.
     * @param indiceAtacante Índice del atacante.
     * @param danioBase      Daño del ataque principal.
     *
     */
    public void alAtacar(Personajes[] equipos, int indiceAtacante, int danioBase) {
        int danioArea = (int) (danioBase * 0.3);

        System.out.println(nombre + " lanza una bomba explosiva!");

        for (int i = 0; i < equipos.length; i++) {
            if (i != indiceAtacante && equipos[i].getVidas() > 0) {
                equipos[i].vidas -= danioArea;

                if (equipos[i].vidas <= 0) {
                    equipos[i].vidas = 0;
                    System.out.println(equipos[i].getNombre() + " ha sido destruido por la explosión!");
                } else {
                    System.out.println(equipos[i].getNombre() + " recibe " + danioArea + " de daño en área.");
                }
            }
        }
    }
}
