package juego;

import javax.sound.sampled.*;
import java.net.URL;

/**
 * Clase que se encarga de aplicar el sonido tanto al fondo como a los botones
 * y de silenciarla si es necesario.
 */
public class Sonidos {

    private static Clip musicaFondo;
    private static boolean sonidoActivado = true;
    /**
     * Reproduce música de fondo en bucle continuo.
     *
     * Si ya existe una música cargada, simplemente continúa
     * reproduciéndola.
     *
     * @param ruta ruta relativa del archivo de audio
     */

    public static void reproducirMusica(String ruta) {
        try {
            if (musicaFondo != null) {
                if (!musicaFondo.isRunning()) {
                    musicaFondo.start();
                }
                aplicarEstadoSonido();
                return;
            }

            URL url = Sonidos.class.getResource(ruta);
            if (url == null) {
                System.out.println("No se encontró el archivo de audio: " + ruta);
                return;
            }

            AudioInputStream audio = AudioSystem.getAudioInputStream(url);
            musicaFondo = AudioSystem.getClip();
            musicaFondo.open(audio);
            musicaFondo.loop(Clip.LOOP_CONTINUOUSLY);
            musicaFondo.start();

            aplicarEstadoSonido();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Detiene completamente la música de fondo actual
     * y libera los recursos utilizados.
     */
    public static void detenerMusica() {
        try {
            if (musicaFondo != null) {
                musicaFondo.stop();
                musicaFondo.close();
                musicaFondo = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Reproduce un efecto de sonido individual.
     *
     * El efecto solamente se reproduce si el sonido
     * general del juego se encuentra activado.
     *
     * @param ruta ruta relativa del archivo de audio
     */
    public static void reproducirEfecto(String ruta) {
        try {
            if (!sonidoActivado) {
                return;
            }

            URL url = Sonidos.class.getResource(ruta);
            if (url == null) {
                System.out.println("No se encontró el archivo de audio: " + ruta);
                return;
            }

            AudioInputStream audio = AudioSystem.getAudioInputStream(url);
            Clip efecto = AudioSystem.getClip();
            efecto.open(audio);
            efecto.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Desactiva todos los sonidos del juego.
     *
     * Silencia la música de fondo
     */
    public static void mutearSonido() {
        sonidoActivado = false;
        aplicarEstadoSonido();
    }
    /**
     * Activa nuevamente el sonido del juego.
     *
     * Permite reproducir música
     * después de haber sido silenciados.
     */
    public static void activarSonido() {
        sonidoActivado = true;
        aplicarEstadoSonido();
    }
    /**
     * Aplica internamente el estado actual del sonido
     *
     * Activa o silencia el clip
     */
    private static void aplicarEstadoSonido() {
        if (musicaFondo == null) return;

        try {
            BooleanControl muteControl = (BooleanControl) musicaFondo.getControl(BooleanControl.Type.MUTE);
            muteControl.setValue(!sonidoActivado);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}