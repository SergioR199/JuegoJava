package swing;

import javax.swing.*;
import java.awt.event.*;

public class PanelMenu extends JPanel implements ActionListener {

    private JButton botonJugar, botonReglas, botonCreadores, botonPersonajes, botonGanadores, botonSalir;

    public PanelMenu() {
        setLayout(null);

        botonJugar = new JButton("Jugar");
        botonJugar.setBounds(250, 50, 200, 30);
        add(botonJugar);
        botonJugar.addActionListener(this);

        botonReglas = new JButton("Reglas del juego");
        botonReglas.setBounds(250, 100, 200, 30);
        add(botonReglas);
        botonReglas.addActionListener(this);

        botonCreadores = new JButton("Información general");
        botonCreadores.setBounds(250, 150, 200, 30);
        add(botonCreadores);
        botonCreadores.addActionListener(this);

        botonPersonajes = new JButton("Información de personajes");
        botonPersonajes.setBounds(250, 200, 200, 30);
        add(botonPersonajes);
        botonPersonajes.addActionListener(this);

        botonGanadores = new JButton("Lista de ganadores");
        botonGanadores.setBounds(250, 250, 200, 30);
        add(botonGanadores);
        botonGanadores.addActionListener(this);

        botonSalir = new JButton("Salir");
        botonSalir.setBounds(250, 300, 200, 30);
        add(botonSalir);
        botonSalir.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this);

        if (e.getSource().equals(botonReglas)) {
            ventana.setContentPane(new PanelReglas());
            ventana.revalidate();
            ventana.repaint();
        } else if (e.getSource().equals(botonCreadores)) {
            ventana.setContentPane(new PanelCreadores());
            ventana.revalidate();
            ventana.repaint();
        } else if (e.getSource().equals(botonPersonajes)) {
            ventana.setContentPane(new PanelInfoPersonajes());
            ventana.revalidate();
            ventana.repaint();
        } else if (e.getSource().equals(botonGanadores)) {
            ventana.setContentPane(new PanelGanadores());
            ventana.revalidate();
            ventana.repaint();
        } else if (e.getSource().equals(botonJugar)) {
            ventana.setContentPane(new PanelJuego());
            ventana.revalidate();
            ventana.repaint();
        } else if (e.getSource().equals(botonSalir)) {
            System.exit(0);
        }
    }
}