class Sylas extends Personajes {

    private int contadorTurnos = 0;

    /**
     * Constructor
     */

    public Sylas() {
        super("Sylas", 190, 50);
    }

    /**
     * Pasiva: cada 2 turnos roba vida y misiles
     */
    @Override
    public void alInicioTurnoGlobal(int turnoGlobal) {
    /**
    * @param turnoGlobal Número del turno global actual del juego.
     */
        contadorTurnos++;

        if (contadorTurnos == 2) {
            contadorTurnos = 0;

            Personajes objetivo = null;

            /**
             * buscar enemigo con más vida
             */

            for (Personajes p : Main.equipos) {
                if (p != this && p.getVidas() > 0) {
                    if (objetivo == null || p.getVidas() > objetivo.getVidas()) {
                        objetivo = p;
                    }
                }
            }

            if (objetivo != null) {

                /**
                 * robar vida
                 */

                int roboVida = 10;
                objetivo.vidas -= roboVida;
                this.vidas += roboVida;

                if (this.vidas > this.vidaMaxima) {
                    this.vidas = this.vidaMaxima;
                }

                /**
                 * robar misiles
                 */

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