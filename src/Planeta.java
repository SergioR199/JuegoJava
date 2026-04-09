/**
 *
 * */
public class Planeta {

    /**
     *
     * */
    private String nombre;
    private int vidas;
    private int misiles_ronda;

    /**
     *
     * */
    public Planeta(String nombre, int vidas, int misiles_ronda) {
        this.nombre = nombre;
        this.vidas = vidas;
        this.misiles_ronda = misiles_ronda;
    }

    /**
     *
     * */
    public boolean estaVivo() {
        return vidas > 0;
    }

    /**
     *
     * */
    public void recibirDanio(int cantidad) {
        this.vidas -= cantidad;
        if (this.vidas < 0) {
            this.vidas = 0;
        }
    }

    /**
     *
     * */
    public void usarMisiles(int cantidad) {
        this.misiles_ronda -= cantidad;
        if (this.misiles_ronda < 0) {
            this.misiles_ronda = 0;
        }
    }

    /**
     *
     * */
    public void recargarMisiles(int cantidad) {
        this.misiles_ronda = cantidad;
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
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    public void setVidas(int vidas) {
        this.vidas = vidas;
        if (this.vidas < 0) {
            this.vidas = 0;
        }
    }

    /**
     *
     * */
    public int getMisiles_ronda() {
        return misiles_ronda;
    }

    /**
     *
     * */
    public void setMisiles_ronda(int misiles_ronda) {
        this.misiles_ronda = misiles_ronda;
    }
}