package juego;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase que se encarga de guardar los ganadores en un fichero y leerlo cuando sea necesario.
 * */
public class Ganadores {

    /**
     * Método que anuncia qué equipo ha ganado la partida y lo guarda en el archivo "ganadores.txt".
     * Si ningún personaje tiene vidas, se registra un empate.
     *
     * @param equipos Array con todos los personajes de la partida.
     * @param partida Número de la partida que se está registrando.
     * */
    public static void anunciarGanador(Personajes[] equipos, int partida) {
        boolean empate = true;

        for (Personajes equipo : equipos) {
            if (equipo.getVidas() > 0) {
                empate = false;

                try {
                    BufferedWriter guardarGanadores = new BufferedWriter(new FileWriter("ganadores.txt", true));

                    guardarGanadores.write("\tGANADOR DE LA " + partida + "ª PARTIDA");
                    guardarGanadores.newLine();
                    guardarGanadores.write(equipo.getNombre() + " [" + equipo.getIdentificador() + "]");
                    guardarGanadores.newLine();
                    guardarGanadores.newLine();

                    guardarGanadores.close();

                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error de escritura en el fichero de ganadores.");
                }

                JOptionPane.showMessageDialog(null, "EL GANADOR ES: "
                        + equipo.getNombre() + " [" + equipo.getIdentificador() + "]");
                return;
            }
        }

        if (empate) {
            try {
                BufferedWriter guardarGanadores = new BufferedWriter(new FileWriter("ganadores.txt", true));

                guardarGanadores.write("\tGANADOR DE LA " + partida + "ª PARTIDA");
                guardarGanadores.newLine();
                guardarGanadores.write("EMPATE");
                guardarGanadores.newLine();
                guardarGanadores.newLine();

                guardarGanadores.close();

            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "ERROR DE ESCRITURA.");
            }

            JOptionPane.showMessageDialog(null, "LA PARTIDA HA TERMINADO EN EMPATE.");
        }
    }

    /**
     * Método que lee el contenido completo del fichero de ganadores y lo devuelve como texto.
     *
     * @return Texto completo del fichero.
     */
    public static String getGanadores() {
        String texto = "";

        try (BufferedReader br = new BufferedReader(new FileReader("ganadores.txt"))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                texto += linea + "\n";
            }

        } catch (IOException e) {
            return "No se pudo leer el fichero de ganadores.";
        }

        if (texto.isEmpty()) {
            return "Todavía no hay ganadores guardados.";
        }

        return texto;
    }

    /**
     * Método que vacía el fichero de ganadores.
     */
    public static void borrarGanadores() {
        try {
            new FileWriter("ganadores.txt").close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al borrar el archivo de ganadores.");
        }
    }
}