package swing;

import juego.InformacionJuego;

import javax.swing.*;
import java.awt.event.*;

public class PanelCreadores extends JPanel implements ActionListener {

    private JButton botonVolver;
    private JLabel titulo;
    private JTextArea areaTexto;

    public PanelCreadores() {
        setLayout(null);

        botonVolver = new JButton("Volver");
        botonVolver.setBounds(20, 20, 100, 30);
        add(botonVolver);
        botonVolver.addActionListener(this);

        titulo = new JLabel("Información de creadores");
        titulo.setBounds(250, 40, 250, 30);
        add(titulo);

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setText(InformacionJuego.getInformacionGeneral());

        JScrollPane scroll = new JScrollPane(areaTexto);
        scroll.setBounds(100, 100, 500, 200);
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