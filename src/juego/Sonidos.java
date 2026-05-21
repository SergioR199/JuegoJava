package juego;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sonidos {

    private static Clip musicaFondo;
    private static boolean sonidoActivado = true;

    public static void reproducirMusica(String ruta) {
        try {
            if (!sonidoActivado) {
                return;
            }

            if (musicaFondo != null && musicaFondo.isRunning()) {
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    public static void cambiarEstadoSonido() {
        sonidoActivado = !sonidoActivado;

        if (!sonidoActivado) {
            detenerMusica();
        } else {
            reproducirMusica("/audio/menu.wav");
        }
    }

    public static boolean isSonidoActivado() {
        return sonidoActivado;
    }
}