/**
 *
 * */
public abstract class Personajes {
    protected String nombre;
    protected int vidas;
    protected int vidaMaxima;
    protected int misilesRonda;

    /**
     *
     * */
    public Personajes(String nombre, int vidas, int misilesRonda) {
        this.nombre = nombre;
        this.vidas = vidas;
        this.vidaMaxima = vidas;
        this.misilesRonda = misilesRonda;
    }

    /**
     *
     * */
    public abstract String getTipo();

    /**
     *
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

        int danioFinal = (int) (misiles * multiplicador);
        this.vidas -= danioFinal;

        if (this.vidas <= 0) {
            this.vidas = 0;
            System.out.println("\n" + this.nombre + " ha sido destruido.");
        }

        return danioFinal;
    }

    /**
     *
     * */
    public int defender(int misilesDefensa) {
        int defensaTotal = misilesDefensa / 2;
        this.vidas += defensaTotal;

        if (this.vidas > this.vidaMaxima) {
            this.vidas = this.vidaMaxima;
        }

        return defensaTotal;
    }

    /**
     *
     * */
    public void reiniciarMisilesRonda() {
        this.misilesRonda = 50;
    }

    /**
     *
     * */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * */
    public int getVidas() {
        return vidas;
    }

    /**
     *
     * */
    public int getMisilesRonda() {
        return misilesRonda;
    }

    /**
     *
     * */
    public void setMisilesRonda(int misilesRonda) {
        this.misilesRonda = misilesRonda;
    }

    /**
     *
     * */
    public int getVidaMaxima() {
        return vidaMaxima;
    }
}

/**
 *
 * */
abstract class Humano extends Personajes {

    /**
     *
     * */
    public Humano(String nombre, int vidas, int misilesRonda) {
        super(nombre, vidas, misilesRonda);
    }

    @Override

    /**
     *
     * */
    public String getTipo() {
        return "HUMANO";
    }
}

/**
 *
 * */
abstract class Yordle extends Personajes {

    /**
     *
     * */
    public Yordle(String nombre, int vidas, int misilesRonda) {
        super(nombre, vidas, misilesRonda);
    }

    @Override

    /**
     *
     * */
    public String getTipo() {
        return "YORDLE";
    }
}

/**
 *
 * */
abstract class SerMagico extends Personajes {

    /**
     *
     * */
    public SerMagico(String nombre, int vidas, int misilesRonda) {
        super(nombre, vidas, misilesRonda);
    }

    @Override

    /**
     *
     * */
    public String getTipo() {
        return "SER MAGICO";
    }
}

/**
 *
 * */
class Jinx extends Humano {

    /**
     *
     * */
    public Jinx() {
        super("Jinx", 180, 55);
    }
}

/**
 *
 * */
class Jhin extends Humano {

    /**
     *
     * */
    public Jhin() {
        super("Jhin", 170, 60);
    }
}

/**
 *
 * */
class Gangplank extends Humano {

    /**
     *
     * */
    public Gangplank() {
        super("Gangplank", 210, 45);
    }
}

/**
 *
 * */
class Sylas extends Humano {

    /**
     *
     * */
    public Sylas() {
        super("Sylas", 190, 50);
    }
}

/**
 *
 * */
class Rumble extends Yordle {

    /**
     *
     * */
    public Rumble() {
        super("Rumble", 160, 50);
    }
}

/**
 *
 * */
class Heimerdinger extends Yordle {

    /**
     *
     * */
    public Heimerdinger() {
        super("Heimerdinger", 150, 55);
    }
}

/**
 *
 * */
class Tristana extends Yordle {

    /**
     *
     * */
    public Tristana() {
        super("Tristana", 155, 60);
    }
}

/**
 *
 * */
class Ziggs extends Yordle {

    /**
     *
     * */
    public Ziggs() {
        super("Ziggs", 145, 65);
    }
}

/**
 *
 * */
class Corki extends Yordle {

    /**
     *
     * */
    public Corki() {
        super("Corki", 165, 55);
    }
}

/**
 *
 * */
class AurelionSol extends SerMagico {

    /**
     *
     * */
    public AurelionSol() {
        super("Aurelion Sol", 220, 45);
    }
}

/**
 *
 * */
class Xerath extends SerMagico {

    /**
     *
     * */
    public Xerath() {
        super("Xerath", 160, 70);
    }
}

/**
 *
 * */
class KogMaw extends SerMagico {

    /**
     *
     * */
    public KogMaw() {
        super("Kog'Maw", 170, 60);
    }
}

/**
 *
 * */
class HumanoPersonalizado extends Humano {

    /**
     *
     * */
    public HumanoPersonalizado(String nombre, int vidas, int misilesRonda) {
        super(nombre, vidas, misilesRonda);
    }
}

/**
 *
 * */
class YordlePersonalizado extends Yordle {

    /**
     *
     * */
    public YordlePersonalizado(String nombre, int vidas, int misilesRonda) {
        super(nombre, vidas, misilesRonda);
    }
}

/**
 *
 * */
class SerMagicoPersonalizado extends SerMagico {

    /**
     *
     * */
    public SerMagicoPersonalizado(String nombre, int vidas, int misilesRonda) {
        super(nombre, vidas, misilesRonda);
    }
}