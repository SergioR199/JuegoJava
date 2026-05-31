package swing;

import javax.swing.JFrame;
/**
 * Clase encargada de crear la ventana principal del juego.
 */
public class Ventana extends JFrame {
    /**
     * Constructor de la ventana principal.
     *
     * Inicializa la interfaz principal del juego.
     */
    public Ventana() {
        setTitle("LEAGUE OF DAM");
        setBounds(400, 100, 1280, 720);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new PanelMenu());
        setLocationRelativeTo(null);

        setVisible(true);
    }
}