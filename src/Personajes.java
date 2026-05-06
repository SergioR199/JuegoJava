/**
 * Clase que representa un personaje del juego.
 * Contiene estadísticas comunes y métodos que pueden ser sobrescritos por las subclases
 * según sus pasivas.
 * */
public class Personajes {
    protected String nombre;
    protected int vidas;
    protected int vidaMaxima;
    protected int misilesRonda;
    protected int bonusMisiles = 0;
    protected int quemadura = 0;
    protected int bonusDanio = 0;
    protected int misilesBase;

    /**
     * Método que ejecuta las acciones que debe realizar un personaje al comienzo del turno global.
     * Si el personaje tiene quemadura, pierde la vida indicada.
     *
     * @param turnoGlobal Número de turno global actual.
     * */
    public void alInicioTurnoGlobal(int turnoGlobal) {
        if (quemadura > 0) {
            this.vidas -= quemadura;
            System.out.println(nombre + " sufre quemadura: -" + quemadura + " vida");
        }
    }

    /**
     * Constructor de un personaje con sus estadísticas iniciales.
     *
     * @param nombre Nombre del personaje.
     * @param vidas Cantidad inicial de vidas.
     * @param misilesRonda Misiles disponibles por ronda.
     * */
    public Personajes(String nombre, int vidas, int misilesRonda) {
        this.nombre = nombre;
        this.vidas = vidas;
        this.vidaMaxima = vidas;
        this.misilesRonda = misilesRonda;
        this.misilesBase = misilesRonda;
    }

    /**
     * Getter de la raza del personaje (Humano, Yordle o Ser Mágico).
     * No hace nada por defecto pero se puede sobrescribir en subclases.
     *
     * @return Tipo del personaje.
     * */
    public String getRaza(){
        return "DESCONOCIDA";
    }

    /**
     * Método que ataca o defiende dependiendo de la cadena que se le de como parámetro.
     *
     * @param accion "atacar" o "defender"
     * @param misiles Cantidad de misiles usados
     * @param atacante Personaje que realiza la acción
     * @return Resultado final: daño causado o vida recuperada
     */
    public int accion(String accion, int misiles, Personajes atacante) {

        if (accion.equalsIgnoreCase("defender")) {
            int curacionBase = misiles / 2;
            int curacionFinal = curacionBase;

            if (this instanceof Gangplank) {
                curacionFinal = ((Gangplank) this).modificarCuracion(curacionBase);
            }

            this.vidas += curacionFinal;

            if (this.vidas > this.vidaMaxima) {
                this.vidas = this.vidaMaxima;
            }

            return curacionFinal;
        }else if (accion.equalsIgnoreCase("atacar")) {
            if (this.getRaza().equals("YORDLE") && Math.random() < 0.25) {
                System.out.println("\n" + this.nombre + " ha esquivado el ataque.");
                atacante.setMisilesRonda(atacante.getMisilesRonda() - misiles);
                return 0;
            }

            double multiplicador = 1.0;

            if (atacante.getRaza().equals("HUMANO") && this.getRaza().equals("YORDLE")) {
                multiplicador = 1.25;
            } else if (atacante.getRaza().equals("SER MAGICO") && this.getRaza().equals("HUMANO")) {
                multiplicador = 1.75;
            } else if (atacante.getRaza().equals("SER MAGICO") && this.getRaza().equals("YORDLE")) {
                multiplicador = 0.75;
            }

            int danioBase = (int) (misiles * multiplicador);
            int danioFinal = danioBase;

            if (atacante instanceof Jhin) {
                danioFinal = ((Jhin) atacante).modificarDanio(danioBase);
            }

            atacante.setMisilesRonda(atacante.getMisilesRonda() - misiles);

            this.vidas -= danioFinal;

            if (this.vidas <= 0) {
                this.vidas = 0;
                System.out.println(this.nombre + " ha sido destruido.");

                if (atacante instanceof Jinx) {
                    ((Jinx) atacante).alMatar(this);
                } else if (atacante instanceof Tristana) {
                    ((Tristana) atacante).alMatar(this);
                }

                if (this instanceof KogMaw) {
                    ((KogMaw) this).alMorir(atacante);
                }
            }

            return danioFinal;
        }

        return 0;
    }

    /**
     * Método que reinicia los misiles disponibles al comienzo de cada ronda.
     * */
    public void reiniciarMisilesRonda() {
        this.misilesRonda = misilesBase + bonusMisiles;
    }

    /**
     * Getter del nombre del personaje.
     *
     * @return Nombre del personaje.
     * */
    public String getNombre() {
        return nombre;
    }

    /**
     * Getter de las vidas actuales del personaje.
     *
     * @return Vidas actuales.
     * */
    public int getVidas() {
        return vidas;
    }

    /**
     * Getter de los misiles disponibles en la ronda actual.
     *
     * @return Misiles de la ronda actual.
     * */
    public int getMisilesRonda() {
        return misilesRonda;
    }

    /**
     * Setter de la cantidad de misiles disponibles en la ronda actual.
     *
     * @param misilesRonda Nuevo número de misiles de ronda.
     * */
    public void setMisilesRonda(int misilesRonda) {
        this.misilesRonda = misilesRonda;
    }

    /**
     * Getter de la vida máxima del personaje.
     *
     * @return Vida máxima.
     * */
    public int getVidaMaxima() {
        return vidaMaxima;
    }
}