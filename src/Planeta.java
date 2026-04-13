public class Planeta {

    private String nombre;
    private int vidas;
    private int misiles_ronda;
    private TipoPlaneta tipo;
    private int vidaMaxima;

    public Planeta(String nombre, int vidas, int misiles_ronda, TipoPlaneta tipo) {
        this.nombre = nombre;
        this.vidas = vidas;
        this.misiles_ronda = misiles_ronda;
        this.tipo = tipo;
        this.vidaMaxima = vidas;

        if (tipo == TipoPlaneta.TANQUE) {
            this.vidas = vidas * 2;
            this.vidaMaxima = vidas * 2;
        } else if (tipo == TipoPlaneta.LADRON) {
            this.vidas = vidas / 2;
            this.vidaMaxima = vidas / 2;
        }
    }

    public boolean estaVivo() {
        return vidas > 0;
    }

    public int combate(int misiles, Planeta atacante) {
        if (this.tipo == TipoPlaneta.LADRON && Math.random() < 0.5) {
            System.out.println("\n" + this.nombre + " ha esquivado el ataque.");
            return 0;
        }

        int danioFinal = misiles;

        switch (atacante.getTipo()) {
            case ASESINO:
                danioFinal = misiles * 2;
                break;
            case TIRADOR:
                danioFinal = (int) (misiles * 1.5);
                break;
            case MAGO:
                danioFinal = misiles;
                break;
            case CABALLERO:
                danioFinal = misiles;
                break;
            case TANQUE:
                danioFinal = misiles;
                break;
            case LADRON:
                danioFinal = misiles;
                break;
        }

        if (this.tipo == TipoPlaneta.TANQUE && atacante.getTipo() != TipoPlaneta.MAGO) {
            danioFinal = danioFinal / 2;
        }

        this.vidas -= danioFinal;

        if (this.vidas < 0) {
            this.vidas = 0;
        }

        return danioFinal;
    }

    public void recibirDanio(int cantidad) {
        this.vidas -= cantidad;
        if (this.vidas < 0) {
            this.vidas = 0;
            System.out.println("\n" + this.nombre + " ha sido destruido.");
        }
    }

    public int defender(int misilesDefensa) {
        int defensaTotal = misilesDefensa / 2;

        if (this.tipo == TipoPlaneta.CABALLERO) {
            defensaTotal *= 2;
        }

        this.vidas += defensaTotal;

        if (this.vidas > this.vidaMaxima) {
            this.vidas = this.vidaMaxima;
        }

        return defensaTotal;
    }

    public void usarMisiles(int cantidad) {
        this.misiles_ronda -= cantidad;
        if (this.misiles_ronda < 0) {
            this.misiles_ronda = 0;
        }
    }

    public void recargarMisiles(int cantidad) {
        this.misiles_ronda = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
        if (this.vidas < 0) {
            this.vidas = 0;
        }
    }

    public int getMisiles_ronda() {
        return misiles_ronda;
    }

    public void setMisiles_ronda(int misiles_ronda) {
        this.misiles_ronda = misiles_ronda;
    }

    public TipoPlaneta getTipo() {
        return tipo;
    }

    public void setTipo(TipoPlaneta tipo) {
        this.tipo = tipo;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public void setVidaMaxima(int vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
    }
}