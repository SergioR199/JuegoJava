package swing;

import javax.swing.JFrame;

public class Ventana extends JFrame {

    public Ventana() {
        setTitle("LEAGUE OF DAM");
        setBounds(400, 100, 700, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new PanelMenu());

        setVisible(true);
    }
}