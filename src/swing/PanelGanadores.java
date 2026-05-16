package swing;

import javax.swing.*;
import java.awt.event.*;
import juego.Ganadores;

public class PanelGanadores extends JPanel implements ActionListener {

    private JButton botonVolver;
    private JTextArea areaTexto;

    public PanelGanadores() {
        setLayout(null);

        botonVolver = new JButton("Volver");
        botonVolver.setBounds(20, 20, 100, 30);
        add(botonVolver);
        botonVolver.addActionListener(this);

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        areaTexto.setText(Ganadores.getGanadores());

        JScrollPane scroll = new JScrollPane(areaTexto);
        scroll.setBounds(50, 70, 600, 300);
        add(scroll);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this);

        if (e.getSource().equals(botonVolver)) {
            ventana.setContentPane(new PanelMenu());
            ventana.revalidate();
            ventana.repaint();
        }
    }
}