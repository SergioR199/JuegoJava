public abstract class Personajes {
    protected String nombre;
    protected int vidas;
    protected int vidaMaxima;
    protected int misilesRonda;
    protected int bonusMisiles = 0;
    protected int quemadura = 0;
    protected int bonusDanio = 0;

    public int modificarDanio(int danioBase) {
        return danioBase + bonusDanio;
    }

    public void alMorir(Personajes atacante) {
    }
    public void alInicioTurnoGlobal(int turnoGlobal) {
        if (quemadura > 0) {
            this.vidas -= quemadura;
            System.out.println(nombre + " sufre quemadura: -" + quemadura + " vida");
        }
    }


    public void alAtacar(Personajes[] equipos, int indiceAtacante, int danioBase) {
    }

    public void alMatar(Personajes objetivo) {

    }

    public int modificarCuracion(int curacionBase) {
        return curacionBase;
    }

    public Personajes(String nombre, int vidas, int misilesRonda) {
        this.nombre = nombre;
        this.vidas = vidas;
        this.vidaMaxima = vidas;
        this.misilesRonda = misilesRonda;
    }

    public abstract String getTipo();

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

    public int defender(int misilesDefensa) {
        int curacionBase = misilesDefensa / 2;

        int curacionFinal = modificarCuracion(curacionBase);

        this.vidas += curacionFinal;

        if (this.vidas > this.vidaMaxima) {
            this.vidas = this.vidaMaxima;
        }

        return curacionFinal;
    }

    public void reiniciarMisilesRonda() {
        this.misilesRonda = 50 + bonusMisiles;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVidas() {
        return vidas;
    }

    public int getMisilesRonda() {
        return misilesRonda;
    }

    public void setMisilesRonda(int misilesRonda) {
        this.misilesRonda = misilesRonda;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }
}

abstract class Humano extends Personajes {
    public Humano(String nombre, int vidas, int misilesRonda) {
        super(nombre, vidas, misilesRonda);
    }

    @Override
    public String getTipo() {
        return "HUMANO";
    }
}

abstract class Yordle extends Personajes {
    public Yordle(String nombre, int vidas, int misilesRonda) {
        super(nombre, vidas, misilesRonda);
    }

    @Override
    public String getTipo() {
        return "YORDLE";
    }
}

abstract class SerMagico extends Personajes {
    public SerMagico(String nombre, int vidas, int misilesRonda) {
        super(nombre, vidas, misilesRonda);
    }

    @Override
    public String getTipo() {
        return "SER MAGICO";
    }
}

class Jinx extends Humano {
    public Jinx() {
        super("Jinx", 180, 55);
    }

    @Override
    public void alMatar(Personajes objetivo) {
        this.bonusMisiles += 10;
        System.out.println(nombre + " gana +10 misiles permanentes!");
    }
}

class Jhin extends Humano {
    private int contadorAtaques = 0;

    public Jhin() {
        super("Jhin", 170, 60);
    }

    @Override
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

class Gangplank extends Humano {

    public Gangplank() {
        super("Gangplank", 210, 45);
    }

    @Override
    public int modificarCuracion(int curacionBase) {
        int curacionMejorada = (int)(curacionBase * 1.5); // 75% en vez de 50%

        System.out.println(nombre + " mejora su curación!");
        return curacionMejorada;
    }
}

class Sylas extends Humano {

    private Personajes habilidadRobada = null;
    private int contadorTurnos = 0;

    public Sylas() {
        super("Sylas", 190, 50);
    }

    // 👇 elegir objetivo al principio (lo haces desde Main si quieres mejorar esto)
    public void robarPasiva(Personajes objetivo) {
        this.habilidadRobada = objetivo;
        System.out.println(nombre + " ha robado la pasiva de " + objetivo.getNombre());
    }

    @Override
    public void alInicioTurnoGlobal(int turnoGlobal) {

        if (habilidadRobada == null) return;

        contadorTurnos++;

        if (contadorTurnos == 2) {
            contadorTurnos = 0;

            System.out.println(nombre + " activa la pasiva robada!");

            // 🔥 aquí copiamos comportamiento según el tipo de pasiva
            habilidadRobada.alMatar(this); // ejemplo básico
        }
    }
}

class Rumble extends Yordle {

    public Rumble() {
        super("Rumble", 160, 50);
    }

    @Override
    public void alAtacar(Personajes[] equipos, int indiceAtacante, int danioBase) {
        int idx = -1;

        for (int i = 0; i < equipos.length; i++) {
            if (equipos[i].getVidas() <= 0) continue;
            if (equipos[i] != this) {
                idx = i;
                break;
            }
        }

        if (idx != -1) {
            equipos[idx].quemadura = 5;
            System.out.println(equipos[idx].getNombre() + " está quemándose!");
        }
    }
}

class Heimerdinger extends Yordle {

    private int contador = 0;

    public Heimerdinger() {
        super("Heimerdinger", 150, 55);
    }

    @Override
    public void alInicioTurnoGlobal(int turnoGlobal) {
        contador++;

        if (contador == 2) {
            contador = 0;

            System.out.println(nombre + " coloca una torreta! 🤖");

            for (Personajes p : Main.equipos) {
                if (p.getVidas() > 0 && p != this) {
                    p.vidas -= 10;
                    System.out.println(p.getNombre() + " recibe 10 de daño de la torreta");
                }
            }
        }
    }
}

class Tristana extends Yordle {

    public Tristana() {
        super("Tristana", 155, 60);
    }

    @Override
    public void alMatar(Personajes objetivo) {
        this.vidas += 15;

        if (this.vidas > this.vidaMaxima) {
            this.vidas = this.vidaMaxima;
        }

        System.out.println(nombre + " se cura 15 de vida!");
    }
}

class Ziggs extends Yordle {
    public Ziggs() {
        super("Ziggs", 145, 65);
    }

    @Override
    public void alAtacar(Personajes[] equipos, int indiceAtacante, int danioBase) {
        int danioArea = (int)(danioBase * 0.3); // 30% del daño

        System.out.println(nombre + " lanza una bomba explosiva! 💣");

        for (int i = 0; i < equipos.length; i++) {
            if (i != indiceAtacante && equipos[i].getVidas() > 0) {
                equipos[i].vidas -= danioArea;

                if (equipos[i].vidas <= 0) {
                    equipos[i].vidas = 0;
                    System.out.println(equipos[i].getNombre() + " ha sido destruido por la explosión!");

                    // 🔥 importante: activar pasiva al matar
                    this.alMatar(equipos[i]);
                } else {
                    System.out.println(equipos[i].getNombre() + " recibe " + danioArea + " de daño en área.");
                }
            }
        }
    }
}

class Corki extends Yordle {

    private int contadorTurnos = 0;

    public Corki() {
        super("Corki", 165, 55);
    }

    @Override
    public void alInicioTurnoGlobal(int turnoGlobal) {

        contadorTurnos++;

        if (contadorTurnos == 4) {
            contadorTurnos = 0;

            System.out.println(nombre + " lanza un misil hextech! 🚀");

            // elegir objetivo aleatorio
            Personajes objetivo = null;

            // necesitamos referencia al equipo → lo resolvemos simple desde combate
            // SOLUCIÓN: buscar el primero vivo que no sea Corki

            Personajes[] equipo = Main.equipos; // ❗ ver nota abajo

            int idxObjetivo = -1;

            for (int i = 0; i < equipo.length; i++) {
                if (!equipo[i].equals(this) && equipo[i].getVidas() > 0) {
                    idxObjetivo = i;
                    break;
                }
            }

            if (idxObjetivo == -1) return;

            // 💥 daño principal
            equipo[idxObjetivo].vidas -= 75;

            System.out.println(equipo[idxObjetivo].getNombre() + " recibe 75 de daño.");

            if (equipo[idxObjetivo].getVidas() <= 0) {
                equipo[idxObjetivo].vidas = 0;
                System.out.println(equipo[idxObjetivo].getNombre() + " ha sido destruido!");
                this.alMatar(equipo[idxObjetivo]);
            }

            // 💣 daño área
            for (int i = 0; i < equipo.length; i++) {
                if (i != idxObjetivo && equipo[i].getVidas() > 0 && !equipo[i].equals(this)) {

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

class AurelionSol extends SerMagico {

    public AurelionSol() {
        super("Aurelion Sol", 220, 45);
    }

    @Override
    public void alInicioTurnoGlobal(int turnoGlobal) {
        bonusDanio += 5;
        System.out.println(nombre + " gana +5 daño permanente!");
    }
}

class Xerath extends SerMagico {

    private boolean escudoActivo = false;

    public Xerath() {
        super("Xerath", 160, 70);
    }

    @Override
    public int combate(int misiles, Personajes atacante) {

        if (this.vidas <= 50 && !escudoActivo) {
            escudoActivo = true;
            System.out.println(nombre + " activa escudo!");
            return 0;
        }

        return super.combate(misiles, atacante);
    }
}

class KogMaw extends SerMagico {

    public KogMaw() {
        super("Kog'Maw", 170, 60);
    }

    @Override
    public void alMorir(Personajes atacante) {
        atacante.vidas -= 20;
        System.out.println(nombre + " explota y hace 20 de daño!");
    }
}

class HumanoPersonalizado extends Humano {
    public HumanoPersonalizado(String nombre, int vidas, int misilesRonda) {
        super(nombre, vidas, misilesRonda);
    }
}

class YordlePersonalizado extends Yordle {
    public YordlePersonalizado(String nombre, int vidas, int misilesRonda) {
        super(nombre, vidas, misilesRonda);
    }
}

class SerMagicoPersonalizado extends SerMagico {
    public SerMagicoPersonalizado(String nombre, int vidas, int misilesRonda) {
        super(nombre, vidas, misilesRonda);
    }
}