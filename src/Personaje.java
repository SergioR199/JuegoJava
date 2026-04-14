public class Personaje {

    private int vidas;
    private String nombre;
    private int misilesRonda;
    private int vidaMaxima;
    private Tipo tipo;

    public Personaje(String nombre, int vidas, int misilesRonda, Tipo tipo) {
        this.nombre = nombre;
        this.vidas = vidas;
        this.vidaMaxima = vidas;
        this.misilesRonda = misilesRonda;
        this.tipo = tipo;
    }

    public int combate(int misiles, Personaje atacante) {

        if (this.tipo == Tipo.YORDLE && Math.random() < 0.25) {
            System.out.println("\n" + this.nombre + " ha esquivado el ataque.");
            return 0;
        }

        double multiplicador = 1.0;

        switch (atacante.getTipo()) {
            case HUMANO:
                if (this.tipo == Tipo.YORDLE) {
                    multiplicador = 1.25;
                }
                break;

            case SER_MAGICO:
                if (this.tipo == Tipo.HUMANO) {
                    multiplicador = 1.75;
                } else if (this.tipo == Tipo.YORDLE) {
                    multiplicador = 0.75;
                }
                break;

            case YORDLE:
                multiplicador = 1.0;
                break;
        }

        int danioFinal = (int) (misiles * multiplicador);
        this.vidas -= danioFinal;

        if (this.vidas <= 0) {
            this.vidas = 0;
            System.out.println("\n" + this.nombre + " ha sido destruido.");
        }

        return danioFinal;
    }

    public int defender(int misilesDefensa) {
        int defensaTotal = misilesDefensa / 2;
        this.vidas += defensaTotal;

        if (this.vidas > this.vidaMaxima) {
            this.vidas = this.vidaMaxima;
        }

        return defensaTotal;
    }

    public void reiniciarMisilesRonda() {
        this.misilesRonda = 50;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public String getNombre() {
        return nombre;
    }

    public int getMisilesRonda() {
        return misilesRonda;
    }

    public void setMisilesRonda(int misilesRonda) {
        this.misilesRonda = misilesRonda;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }
}