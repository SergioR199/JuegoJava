public class Planeta {

    // Atributos
    private int vidas;
    private String nombre;
    private int misiles_ronda;

    // Constructor
    public Planeta(String nombre, int vidas, int misiles_ronda) {
        this.nombre = nombre;
        this.vidas = vidas;
        this.misiles_ronda = misiles_ronda;
    }

    // Metodo combate
    public void combate(int misiles, Planeta atacante) {
        this.vidas -= misiles;

        if (this.vidas <= 0) {
            this.vidas = 0;
            System.out.println("\n" + this.nombre + " ha sido destruido.");
        }
    }

    // Getters y Setters
    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMisiles_ronda() {
        return misiles_ronda;
    }

    public void setMisiles_ronda(int misiles_ronda) {
        this.misiles_ronda = misiles_ronda;
    }
}