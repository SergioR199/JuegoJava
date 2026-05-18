package interfaces;

/**
 * Interfaz que define las habilidades pasivas de los personajes.
 * Las clases que implementen esta interfaz podrán modificar su jugabilidad
 * (curación, inicio de ronda, al matar o al morir).
 */
public interface InterfacePasivas {

    /**
     * Método que ejecuta las acciones que debe realizar un personaje al comienzo del turno global.
     * Si el personaje tiene quemadura, pierde la vida indicada.
     *
     * @param turnoGlobal Número de turno global actual.
     * */
    public void pasivaRonda(int ronda);

    /**
     * Método que permite modificar la curación base recibida por el personaje.
     * Está pensado para ser sobrescrito por subclases que tengan habilidades pasivas de curación.
     *
     * @param curacionBase Cantidad de curación inicial calculada.
     * @return Cantidad final de curación tras aplicar la pasiva.
     */
    public int pasivaCuracion(int curacionBase);

    /**
     * Método que se ejecuta cuando el personaje elimina a otro.
     * Puede ser sobrescrito por subclases para aplicar efectos especiales al matar,
     * como obtener bonificaciones o activar habilidades.
     */
    public void pasivaMatar();

    /**
     * Método que se ejecuta cuando el personaje muere.
     * Puede ser sobrescrito por subclases para aplicar efectos al morir,
     * como infligir daño adicional o activar habilidades especiales.
     */
    public void pasivaMorir();
}