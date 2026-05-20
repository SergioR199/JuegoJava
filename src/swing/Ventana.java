package swing;

import javax.swing.JFrame;

public class Ventana extends JFrame {

    public Ventana() {
        setTitle("LEAGUE OF DAM");
        setBounds(400, 100, 1280, 720);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new PanelMenu());

        setVisible(true);
    }
}