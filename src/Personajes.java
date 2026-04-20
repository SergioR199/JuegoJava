/**
 * Clase que representa un personaje del juego.
 * Contiene estadísticas comunes y métodos que pueden ser sobrescritos por las subclases
 * según sus pasivas.
 * */
public abstract class Personajes {
    protected String nombre;
    protected int vidas;
    protected int vidaMaxima;
    protected int misilesRonda;
    protected int bonusMisiles = 0;
    protected int quemadura = 0;
    protected int bonusDanio = 0;

    /**
     * Método que modifica el daño base sumándole el bonus de daño.
     *
     * @param danioBase Daño antes de aplicar la modificación.
     * @return Daño tras aplicar el bonus de daño.
     * */
    public int modificarDanio(int danioBase) {
        return danioBase + bonusDanio;
    }

    /**
     * Método que ejecuta las acciones que debe realizar un personaje al morir.
     * No hace nada por defecto pero se puede sobrescribir en subclases.
     *
     * @param atacante Personaje que ha matado al que utiliza el método.
     * */
    public void alMorir(Personajes atacante) {
    }

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
     * Método que ejecuta las acciones especiales que se activan al atacar a otro personaje.
     * No hace nada por defecto pero se puede sobrescribir en subclases.
     *
     * @param equipos Array con todos los personajes que juegan la partida.
     * @param indiceAtacante Índice del personaje que ataca.
     * @param danioBase Daño causado por el ataque principal.
     * */
    public void alAtacar(Personajes[] equipos, int indiceAtacante, int danioBase) {
    }

    /**
     * Método que ejecuta las acciones especiales que se activan al matar a un objetivo.
     * No hace nada por defecto pero se puede sobrescribir en subclases.
     *
     * @param objetivo Personaje eliminado.
     * */
    public void alMatar(Personajes objetivo) {
    }

    /**
     * Método que modifica la curación base al defenderse.
     * Por defecto devuelve la curación sin cambios.
     *
     * @param curacionBase Cantidad de curación antes de aplicar bonus.
     * @return Curación final después de aplicar modificaciones.
     * */
    public int modificarCuracion(int curacionBase) {
        return curacionBase;
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
    }

    /**
     * Getter del tipo del personaje.
     * No hace nada por defecto pero se puede sobrescribir en subclases.
     *
     * @return Tipo del personaje.
     * */
    public abstract String getTipo();

    /**
     * Método que calcula un ataque contra este personaje aplicando las modificaciones del tipo,
     * la probabilidad de esquiva de los Yordles y las bonificaciones del atacante.
     *
     * @param misiles Cantidad de misiles usados en el ataque.
     * @param atacante Personaje que realiza el ataque.
     * @return Daño final infligido.
     * */
    public int combate(int misiles, Personajes atacante) {
        if (this instanceof Yordle && Math.random() < 0.25) {
            System.out.println("\n" + this.nombre + " ha esquivado el ataque.");
            return 0;
        }

        double multiplicador = 1.0;

        if (atacante instanceof Humano && this instanceof Yordle) {
            multiplicador = 1.25;
        } else if (atacante instanceof SerMagico && this instanceof Humano) {
            multiplicador = 1.75;
        } else if (atacante instanceof SerMagico && this instanceof Yordle) {
            multiplicador = 0.75;
        }

        int danioBase = (int) (misiles * multiplicador);
        int danioFinal = atacante.modificarDanio(danioBase);
        this.vidas -= danioFinal;

        if (this.vidas <= 0) {
            this.vidas = 0;
            System.out.println(this.nombre + " ha sido destruido.");

            atacante.alMatar(this);
            this.alMorir(atacante);
        }

        return danioFinal;
    }

    /**
     * Método que ejecuta la defensa usando misiles y aplica la curación final.
     *
     * @param misilesDefensa Cantidad de misiles usados para defenderse.
     * @return Cantidad de vida recuperada.
     * */
    public int defender(int misilesDefensa) {
        int curacionBase = misilesDefensa / 2;

        int curacionFinal = modificarCuracion(curacionBase);

        this.vidas += curacionFinal;

        if (this.vidas > this.vidaMaxima) {
            this.vidas = this.vidaMaxima;
        }

        return curacionFinal;
    }

    /**
     * Método que reinicia los misiles disponibles al comienzo de cada ronda.
     * */
    public void reiniciarMisilesRonda() {
        this.misilesRonda = 50 + bonusMisiles;
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

/**
 * Clase que representa a un personaje de tipo Humano.
 * */
abstract class Humano extends Personajes {

    /**
     * Constructor de un personaje Humano con sus estadísticas iniciales.
     *
     * @param nombre Nombre del personaje.
     * @param vidas Vidas iniciales.
     * @param misilesRonda Misiles iniciales por ronda.
     * */
    public Humano(String nombre, int vidas, int misilesRonda) {
        super(nombre, vidas, misilesRonda);
    }

    @Override

    /**
     * Getter del tipo del personaje.
     *
     * @return String "HUMANO".
     * */
    public String getTipo() {
        return "HUMANO";
    }
}

/**
 * Clase que representa a un personaje de tipo Yordle.
 * */
abstract class Yordle extends Personajes {

    /**
     * Constructor de un personaje Yordle con sus estadísticas iniciales.
     *
     * @param nombre Nombre del personaje.
     * @param vidas Vidas iniciales.
     * @param misilesRonda Misiles iniciales por ronda.
     * */
    public Yordle(String nombre, int vidas, int misilesRonda) {
        super(nombre, vidas, misilesRonda);
    }

    @Override

    /**
     * Getter del tipo del personaje.
     *
     * @return String "YORDLE".
     * */
    public String getTipo() {
        return "YORDLE";
    }
}

/**
 * Clase que representa a un personaje de tipo SerMagico.
 * */
abstract class SerMagico extends Personajes {

    /**
     * Constructor de un personaje de tipo SerMagico con sus estadísticas iniciales.
     *
     * @param nombre Nombre del personaje.
     * @param vidas Vidas iniciales.
     * @param misilesRonda Misiles iniciales por ronda.
     * */
    public SerMagico(String nombre, int vidas, int misilesRonda) {
        super(nombre, vidas, misilesRonda);
    }

    @Override

    /**
     * Getter del tipo del personaje.
     *
     * @return String "SER MAGICO".
     * */
    public String getTipo() {
        return "SER MAGICO";
    }
}

/**
 * Clase que representa a Jinx.
 * */
class Jinx extends Humano {

    /**
     * Constructor de Jinx con sus estadísticas iniciales.
     * */
    public Jinx() {
        super("Jinx", 180, 55);
    }

    @Override

    /**
     * Método que al matar a un enemigo, Jinx gana misiles permanentes.
     *
     * @param objetivo Personaje eliminado por Jinx.
     * */
    public void alMatar(Personajes objetivo) {
        this.bonusMisiles += 10;
        System.out.println(nombre + " gana +10 misiles permanentes!");
    }
}

/**
 * Clase que representa a Jhin.
 * */
class Jhin extends Humano {
    private int contadorAtaques = 0;

    /**
     * Constructor de Jhin con sus estadísticas iniciales.
     * */
    public Jhin() {
        super("Jhin", 170, 60);
    }

    @Override

    /**
     * Método que modifica el daño para que después de cada 3 ataques, el cuarto sea crítico.
     *
     * @param danioBase Daño base del ataque.
     * @return Daño final, duplicado si es el cuarto ataque.
     * */
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

/**
 * Clase que representa a Gangplank.
 * */
class Gangplank extends Humano {

    /**
     * Constructor de Gangplank con sus estadísticas iniciales.
     * */
    public Gangplank() {
        super("Gangplank", 210, 45);
    }

    @Override

    /**
     * Método que mejora la curación aplicada al defenderse.
     *
     * @param curacionBase Curación calculada inicialmente.
     * @return Curación aumentada un 50%.
     * */
    public int modificarCuracion(int curacionBase) {
        int curacionMejorada = (int)(curacionBase * 1.5);

        System.out.println(nombre + " mejora su curación!");
        return curacionMejorada;
    }
}

/**
 * Clase que representa a Sylas.
 * */
class Sylas extends Humano {

    private Personajes habilidadRobada = null;
    private int contadorTurnos = 0;

    /**
     * Constructor de Sylas con sus estadísticas iniciales.
     * */
    public Sylas() {
        super("Sylas", 190, 50);
    }

    /**
     * Método que roba la pasiva de otro personaje.
     *
     * @param objetivo Personaje del que se roba la pasiva.
     * */
    public void robarPasiva(Personajes objetivo) {
        this.habilidadRobada = objetivo;
        System.out.println(nombre + " ha robado la pasiva de " + objetivo.getNombre());
    }

    @Override

    /**
     * Método que ejecuta la pasiva robada cada dos turnos globales.
     *
     * @param turnoGlobal Número de turno global actual.
     * */
    public void alInicioTurnoGlobal(int turnoGlobal) {

        if (habilidadRobada == null) {
            return;
        }

        contadorTurnos++;

        if (contadorTurnos == 2) {
            contadorTurnos = 0;

            System.out.println(nombre + " activa la pasiva robada!");

            habilidadRobada.alMatar(this);
        }
    }
}

/**
 * Clase que representa a Rumble.
 * */
class Rumble extends Yordle {

    /**
     * Constructor de Rumble con sus estadísticas iniciales.
     * */
    public Rumble() {
        super("Rumble", 160, 50);
    }

    @Override

    /**
     * Método que aplica quemadura a un enemigo tras atacar.
     *
     * @param equipos Array con todos los personajes de la partida.
     * @param indiceAtacante Índice del atacante.
     * @param danioBase Daño base del ataque.
     * */
    public void alAtacar(Personajes[] equipos, int indiceAtacante, int danioBase) {
        int indice = -1;

        for (int i = 0; i < equipos.length; i++) {
            if (equipos[i].getVidas() <= 0) continue;
            if (equipos[i] != this) {
                indice = i;
                break;
            }
        }

        if (indice != -1) {
            equipos[indice].quemadura = 5;
            System.out.println(equipos[indice].getNombre() + " está quemándose!");
        }
    }
}

/**
 * Clase que representa a Heimerdinger.
 * */
class Heimerdinger extends Yordle {

    private int contador = 0;

    /**
     * Constructor de Heimerdinger con sus estadísticas iniciales.
     * */
    public Heimerdinger() {
        super("Heimerdinger", 150, 55);
    }

    @Override

    /**
     * Método que coloca una torreta cada dos turnos globales e inflige daño a los enemigos vivos.
     *
     * @param turnoGlobal Número de turno global actual.
     * */
    public void alInicioTurnoGlobal(int turnoGlobal) {
        contador++;

        if (contador == 2) {
            contador = 0;

            System.out.println(nombre + " coloca una torreta!");

            for (Personajes p : Main.equipos) {
                if (p.getVidas() > 0 && p != this) {
                    p.vidas -= 10;
                    System.out.println(p.getNombre() + " recibe 10 de daño de la torreta");
                }
            }
        }
    }
}

/**
 * Clase que representa a Tristana.
 * */
class Tristana extends Yordle {

    /**
     * Constructor de Tristana con sus estadísticas iniciales.
     * */
    public Tristana() {
        super("Tristana", 155, 60);
    }

    @Override

    /**
     * Método que cura vida al matar a un enemigo.
     *
     * @param objetivo Personaje eliminado.
     * */
    public void alMatar(Personajes objetivo) {
        this.vidas += 15;

        if (this.vidas > this.vidaMaxima) {
            this.vidas = this.vidaMaxima;
        }

        System.out.println(nombre + " se cura 15 de vida!");
    }
}

/**
 * Clase que representa a Ziggs.
 * */
class Ziggs extends Yordle {

    /**
     * Constructor de Ziggs con sus estadísticas iniciales.
     * */
    public Ziggs() {
        super("Ziggs", 145, 65);
    }

    @Override

    /**
     * Método que inflige daño en área al atacar.
     *
     * @param equipos Array con todos los personajes de la partida.
     * @param indiceAtacante Índice del atacante.
     * @param danioBase Daño del ataque principal.
     * */
    public void alAtacar(Personajes[] equipos, int indiceAtacante, int danioBase) {
        int danioArea = (int)(danioBase * 0.3);

        System.out.println(nombre + " lanza una bomba explosiva!");

        for (int i = 0; i < equipos.length; i++) {
            if (i != indiceAtacante && equipos[i].getVidas() > 0) {
                equipos[i].vidas -= danioArea;

                if (equipos[i].vidas <= 0) {
                    equipos[i].vidas = 0;
                    System.out.println(equipos[i].getNombre() + " ha sido destruido por la explosión!");

                    this.alMatar(equipos[i]);
                } else {
                    System.out.println(equipos[i].getNombre() + " recibe " + danioArea + " de daño en área.");
                }
            }
        }
    }
}

/**
 * Clase que representa a Corki.
 * */
class Corki extends Yordle {

    private int contadorTurnos = 0;

    /**
     * Constructor de Corki con sus estadísticas iniciales.
     * */
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
                this.alMatar(equipo[indiceObjetivo]);
            }

            for (int i = 0; i < equipo.length; i++) {
                if (i != indiceObjetivo && equipo[i].getVidas() > 0 && !equipo[i].equals(this)) {

                    equipo[i].vidas -= 10;

                    System.out.println(equipo[i].getNombre() + " recibe 10 de daño en área.");

                    if (equipo[i].getVidas() <= 0) {
                        equipo[i].vidas = 0;
                        System.out.println(equipo[i].getNombre() + " ha sido destruido por la explosión!");
                        this.alMatar(equipo[i]);
                    }
                }
            }
        }
    }
}

/**
 * Clase que representa a Aurelion Sol.
 * */
class AurelionSol extends SerMagico {

    /**
     * Constructor de Aurelion Sol con sus estadísticas iniciales.
     * */
    public AurelionSol() {
        super("Aurelion Sol", 220, 45);
    }

    @Override

    /**
     * Método que aumenta permanentemente el daño del personaje al inicio de cada turno global.
     *
     * @param turnoGlobal Número de turno global actual.
     * */
    public void alInicioTurnoGlobal(int turnoGlobal) {
        bonusDanio += 5;
        System.out.println(nombre + " gana +5 daño permanente!");
    }
}

/**
 * Clase que representa a Xerath.
 * */
class Xerath extends SerMagico {

    private boolean escudoActivo = false;

    /**
     * Constructor de Xerath con sus estadísticas iniciales.
     * */
    public Xerath() {
        super("Xerath", 160, 70);
    }

    @Override

    /**
     * Método que activa un escudo cuando baja de 50 vidas por primera vez.
     *
     * @param misiles Cantidad de misiles usados en el ataque.
     * @param atacante Personaje que realiza el ataque.
     * @return Daño final recibido o 0 si el escudo se activa.
     * */
    public int combate(int misiles, Personajes atacante) {

        if (this.vidas <= 50 && !escudoActivo) {
            escudoActivo = true;
            System.out.println(nombre + " activa escudo!");
            return 0;
        }

        return super.combate(misiles, atacante);
    }
}

/**
 * Clase que representa a Kog'Maw.
 * */
class KogMaw extends SerMagico {

    /**
     * Constructor de Kog'Maw con sus estadísticas iniciales.
     * */
    public KogMaw() {
        super("Kog'Maw", 170, 60);
    }

    @Override

    /**
     * Método que inflige daño al atacante cuando muere Kog'Maw.
     *
     * @param atacante Personaje que ha matado a Kog'Maw.
     * */
    public void alMorir(Personajes atacante) {
        atacante.vidas -= 20;
        System.out.println(nombre + " explota y hace 20 de daño!");
    }
}

/**
 * Clase que representa a un Humano personalizado.
 * */
class HumanoPersonalizado extends Humano {

    /**
     * Constructor de un humano personalizado con el nombre y estadísticas indicadas.
     *
     * @param nombre Nombre del personaje.
     * @param vidas Vidas iniciales.
     * @param misilesRonda Misiles iniciales por ronda.
     * */
    public HumanoPersonalizado(String nombre, int vidas, int misilesRonda) {
        super(nombre, vidas, misilesRonda);
    }
}

/**
 * Clase que representa a un Yordle personalizado.
 * */
class YordlePersonalizado extends Yordle {

    /**
     * Constructor de un Yordle personalizado con el nombre y estadísticas indicadas.
     *
     * @param nombre Nombre del personaje.
     * @param vidas Vidas iniciales.
     * @param misilesRonda Misiles iniciales por ronda.
     * */
    public YordlePersonalizado(String nombre, int vidas, int misilesRonda) {
        super(nombre, vidas, misilesRonda);
    }
}

/**
 * Clase que representa a un Ser Mágico personalizado.
 * */
class SerMagicoPersonalizado extends SerMagico {

    /**
     * Contructor de un Ser Mágico personalizado con el nombre y estadísticas indicadas.
     *
     * @param nombre Nombre del personaje.
     * @param vidas Vidas iniciales.
     * @param misilesRonda Misiles iniciales por ronda.
     * */
    public SerMagicoPersonalizado(String nombre, int vidas, int misilesRonda) {
        super(nombre, vidas, misilesRonda);
    }
}