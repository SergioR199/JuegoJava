/**
 * Clase que representa a Sylas.
 *
 */
public class Sylas extends Personajes {

    private int contadorTurnos = 0;

    /**
     * Constructor de Sylas con sus estadísticas iniciales.
     *
     * @param identificador Identificador del personaje.
     */
    public Sylas(String identificador) {
        super("Sylas", identificador, 190, 50);
    }

    /**
     * Método que hace que este personaje robe vida y misiles cada 2 turnos.
     *
     * @param turnoGlobal Número del turno global actual del juego.
     */
    @Override
    public void pasivaRonda(int turnoGlobal) {

        contadorTurnos++;

        if (contadorTurnos == 2) {
            contadorTurnos = 0;

            Personajes objetivo = null;

            for (Personajes p : Main.equipos) {
                if (p != this && p.getVidas() > 0) {
                    if (objetivo == null || p.getVidas() > objetivo.getVidas()) {
                        objetivo = p;
                    }
                }
            }

            if (objetivo != null) {
                int roboVida = 10;
                objetivo.vidas -= roboVida;
                if (objetivo.vidas < 0) {
                    objetivo.vidas = 0;
                }

                this.vidas += roboVida;

                if (this.vidas > this.vidaMaxima) {
                    this.vidas = this.vidaMaxima;
                }

                int roboMisiles = Math.min(5, objetivo.getMisilesRonda());
                objetivo.setMisilesRonda(objetivo.getMisilesRonda() - roboMisiles);
                this.setMisilesRonda(this.getMisilesRonda() + roboMisiles);

                System.out.println(nombre + " roba poder de " + objetivo.getNombre()
                        + " (-10 vida, -" + roboMisiles + " misiles)");
            }
        }
    }

    /**
     * Getter de la raza del personaje.
     *
     * @return String "HUMANO".
     * */
    @Override
    public String getRaza() {
        return "HUMANO";
    }
}