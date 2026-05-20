package swing;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class PanelFondo extends JPanel {

    private Image imagenFondo;

    public PanelFondo(String rutaImagen) {

        URL location = getClass().getResource(rutaImagen);

        System.out.println("RUTA BUSCADA: " + rutaImagen);
        System.out.println("RESULTADO: " + location);

        if (location != null) {
            imagenFondo = new ImageIcon(location).getImage();
        }

        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (imagenFondo != null) {
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}