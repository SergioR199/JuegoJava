/**
 * Clase que representa a Jhin.
 *
 */
class Jhin extends Humano {
    private int contadorAtaques = 0;

    /**
     * Constructor de Jhin con sus estadísticas iniciales.
     *
     */
    public Jhin() {
        super("Jhin", 170, 60);
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
