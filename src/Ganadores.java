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
                    System.out.println("ERROR DE ESCRITURA");
                }

                lecturaArchivo();
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
                System.out.println("ERROR DE ESCRITURA");
            }

            lecturaArchivo();
        }
    }

    /**
     * Método que lee el contenido de "ganadores.txt" y lo muestra por consola.
     *
     */
    public static void lecturaArchivo() {
        String linea = "";

        try {
            BufferedReader lecturaGanadores = new BufferedReader(new FileReader("ganadores.txt"));

            while ((linea = lecturaGanadores.readLine()) != null) {
                System.out.println(linea);
            }

            lecturaGanadores.close();

        } catch (IOException e) {
            System.out.println("ERROR DE LECTURA");
        }
    }
}