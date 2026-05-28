package testJuego1_Utils;

/**
 * Clase de utilidades para validar datos del juego.
 * */
public class Validaciones {

    /**
     * Método que valida si un identificador tiene el formato correcto.
     * El formato válido es: 4 números seguidos de 3 letras mayúsculas.
     * Ejemplo válido: 4325EIS.
     *
     * @param identificador Cadena que se quiere validar.
     * @return true si el identificador es válido, false en caso contrario.
     * */
    public static boolean validarIdentificador(String identificador) {
        return identificador.matches("^[0-9]{4}[A-Z]{3}$");
    }
}