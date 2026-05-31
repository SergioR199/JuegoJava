package swing;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Clase para dar imagen a los fondos de cada pantalla.
 */

public class PanelFondo extends JPanel {

    private Image imagenFondo;

    /**
     * Metodo para buscar la imagen.
     *
     * @param rutaImagen
     */
    public PanelFondo(String rutaImagen) {

        URL location = getClass().getResource(rutaImagen);

        System.out.println("RUTA BUSCADA: " + rutaImagen);
        System.out.println("RESULTADO: " + location);

        if (location != null) {
            imagenFondo = new ImageIcon(location).getImage();
        }

        setLayout(new BorderLayout());
    }

    /**
     * Metodo para dibujar la imagen.
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (imagenFondo != null) {
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}