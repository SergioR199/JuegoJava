package juego;

import interfaces.InterfacePasivas;

/**
 * Clase que representa un personaje del juego.
 * Contiene estadísticas comunes y métodos que pueden ser sobrescritos por las subclases
 * según sus pasivas.
 * */
public class Personajes implements InterfacePasivas{
    protected String nombre;
    protected String identificador;
    protected int vidas;
    protected int vidaMaxima;
    protected int misilesRonda;
    protected int bonusMisiles = 0;
    protected int quemadura = 0;
    protected int bonusDanio = 0;
    protected int misilesBase;
    protected String mensajeEsquiva = "";

    /**
     * Constructor de un personaje con sus estadísticas iniciales.
     *
     * @param nombre Nombre del personaje.
     * @param identificador Identificador del personaje.
     * @param vidas Cantidad inicial de vidas.
     * @param misilesRonda Misiles disponibles por ronda.
     * */
    public Personajes(String nombre, String identificador, int vidas, int misilesRonda) {
        this.nombre = nombre;
        this.identificador = identificador;
        this.vidas = vidas;
        this.vidaMaxima = vidas;
        this.misilesRonda = misilesRonda;
        this.misilesBase = misilesRonda;
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
     * Getter del identificador del personaje.
     *
     * @return Identificador del personaje.
     * */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * Getter de las vidas actuales del personaje.
     *
     * @return Vidas actuales.
     * */
    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {

        this.vidas = vidas;
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

    public int getMisilesBase(int misilesBase){

        return misilesBase;
    }

    public String getMensajeEsquiva() {
        return mensajeEsquiva;
    }

    public void setMensajeEsquiva(String mensajeEsquiva) {
        this.mensajeEsquiva = mensajeEsquiva;
    }

    /**
     * Método que ejecuta las acciones que debe realizar un personaje al comienzo del turno global.
     * Si el personaje tiene quemadura, pierde la vida indicada.
     *
     * @param ronda Número de turno actual.
     * */
    public void pasivaRonda(int ronda) {
        if (quemadura > 0) {
            this.vidas -= quemadura;
            if (this.vidas < 0) {
                this.vidas = 0;
            }
            Main.agregarMensajePasiva(nombre + " sufre quemadura: -" + quemadura + " vida");
        }
    }

    /**
     * Método que permite modificar la curación base recibida por el personaje.
     * Está pensado para ser sobrescrito por subclases que tengan habilidades pasivas de curación.
     *
     * @param curacionBase Cantidad de curación inicial calculada.
     * @return Cantidad final de curación tras aplicar la pasiva.
     */
    public int pasivaCuracion(int curacionBase){
        return curacionBase;
    }

    /**
     * Método que se ejecuta cuando algun jugador elimina a otro.
     * */
    public void pasivaMatar(){}

    /**
     * Método que ejecuta cuando el jugador es eliminado.
     * */
    public void pasivaMorir(Personajes atacante){}

    /**
     * Método que ataca o defiende dependiendo de la cadena que se le dé como parámetro.
     *
     * @param accion "atacar" o "defender".
     * @param misiles Cantidad de misiles usados.
     * @param atacante Personaje que realiza la acción.
     * @return Resultado final: daño causado o vida recuperada.
     * */
    public int accion(String accion, int misiles, Personajes atacante) {

        if (accion.equalsIgnoreCase("defender")) {
            int curacionBase = misiles / 2;
            int curacionFinal = pasivaCuracion(curacionBase);

            this.vidas += curacionFinal;

            if (this.vidas > this.vidaMaxima) {
                this.vidas = this.vidaMaxima;
            }

            return curacionFinal;

        } else if (accion.equalsIgnoreCase("atacar")) {
            if ((this instanceof Corki||this instanceof Heimerdinger ||this instanceof Rumble
                    ||this instanceof Tristana ||this instanceof YordlePersonalizado
                    ||this instanceof Ziggs) && Math.random() < 0.25) {
                this.mensajeEsquiva = this.nombre + " ha esquivado el ataque.";
                atacante.setMisilesRonda(atacante.getMisilesRonda() - misiles);
                return 0;
            }

            double multiplicador = 1.0;

            if ((atacante instanceof Gangplank ||atacante instanceof HumanoPersonalizado
                    ||atacante instanceof Jhin||atacante instanceof Jinx
                    ||atacante instanceof Sylas)
                    && (this instanceof Corki ||this instanceof Heimerdinger
                    ||this instanceof Rumble ||this instanceof Tristana
                    ||this instanceof YordlePersonalizado ||this instanceof Ziggs)) {
                multiplicador = 1.25;
            } else if ((atacante instanceof AurelionSol ||atacante instanceof KogMaw
                    ||atacante instanceof SerMagicoPersonalizado ||atacante instanceof Xerath)
                    && (this instanceof Gangplank|this instanceof HumanoPersonalizado
                    ||this instanceof Jhin||this instanceof Jinx
                    ||this instanceof Sylas)) {
                multiplicador = 1.75;
            } else if ((atacante instanceof AurelionSol||atacante instanceof KogMaw
                    ||atacante instanceof SerMagicoPersonalizado ||atacante instanceof Xerath)
                    && (this instanceof Corki
                    ||this instanceof Heimerdinger||this instanceof Rumble
                    ||this instanceof Tristana ||this instanceof YordlePersonalizado ||this instanceof Ziggs)) {
                multiplicador = 0.75;
            }

            int danioBase = (int) (misiles * multiplicador) + atacante.bonusDanio;
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
                    ((Jinx) atacante).pasivaMatar();
                } else if (atacante instanceof Tristana) {
                    ((Tristana) atacante).pasivaMatar();
                }

                if (this instanceof KogMaw) {
                    ((KogMaw) this).pasivaMorir(atacante);
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
}