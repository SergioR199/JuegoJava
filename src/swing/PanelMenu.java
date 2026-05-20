package swing;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class PanelMenu extends JPanel implements ActionListener {

    private JButton botonJugar, botonReglas, botonCreadores, botonPersonajes, botonGanadores, botonSalir;
    private Image fondo;

    public PanelMenu() {
        setLayout(null);

        fondo = new ImageIcon(getClass().getResource("/fondo.png")).getImage();

        botonJugar = new botonJugar("JUGAR");
        botonJugar.setBounds(560, 340, 160, 60);
        add(botonJugar);
        botonJugar.addActionListener(this);

        botonReglas = new JButton("Reglas del juego");
        botonReglas.setBounds(28, 25, 200, 50);
        add(botonReglas);
        botonReglas.addActionListener(this);

        botonCreadores = new JButton("Información general");
        botonCreadores.setBounds(284, 25, 200, 50);
        add(botonCreadores);
        botonCreadores.addActionListener(this);

        botonPersonajes = new JButton("Información de personajes");
        botonPersonajes.setBounds(540, 25, 200, 50);
        add(botonPersonajes);
        botonPersonajes.addActionListener(this);

        botonGanadores = new JButton("Lista de ganadores");
        botonGanadores.setBounds(796, 25, 200, 50);
        add(botonGanadores);
        botonGanadores.addActionListener(this);

        botonSalir = new JButton("Salir");
        botonSalir.setBounds(1052, 25, 200, 50);
        add(botonSalir);
        botonSalir.addActionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 100, getWidth(), getHeight(), this);
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