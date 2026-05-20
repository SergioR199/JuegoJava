package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class botonJugar extends JButton {

    private final Color colorNormal = new Color(30, 35, 45);
    private final Color colorHover = new Color(60, 70, 90);

    public botonJugar(String texto) {
        super(texto);

        setFont(new Font("Serif", Font.BOLD, 22));
        setForeground(new Color(212, 176, 92));
        setBackground(colorNormal);
        setFocusPainted(false);
        setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
        setContentAreaFilled(true);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(colorHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(colorNormal);
            }
        });
    }
}