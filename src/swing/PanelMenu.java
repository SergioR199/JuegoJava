package swing;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class PanelMenu extends PanelFondo implements ActionListener {
    private JButton botonJugar, botonReglas, botonCreadores, botonPersonajes, botonGanadores, botonSalir;
    private Image fondo;

    public PanelMenu() {
        super("/imagenes/leagueoflatam.png");
        setLayout(null);

        fondo = new ImageIcon(getClass().getResource("/fondo.png")).getImage();

        int anchoBoton = 260;
        int altoBoton = 35;
        int x = 510;     // posición horizontal centrada aproximada
        int yInicial = 300;
        int separacion = 20;

        botonJugar = new botonJugar("JUGAR");
        botonJugar.setBounds(x, yInicial, anchoBoton, altoBoton);
        add(botonJugar);
        botonJugar.addActionListener(this);

        botonReglas = new botonJugar("REGLAS DEL JUEGO");
        botonReglas.setBounds(x, yInicial + (altoBoton + separacion) * 1, anchoBoton, altoBoton);
        add(botonReglas);
        botonReglas.addActionListener(this);

        botonCreadores = new botonJugar("INFORMACIÓN GENERAL");
        botonCreadores.setBounds(x, yInicial + (altoBoton + separacion) * 2, anchoBoton, altoBoton);
        add(botonCreadores);
        botonCreadores.addActionListener(this);

        botonPersonajes = new botonJugar("INFO PERSONAJES");
        botonPersonajes.setBounds(x, yInicial + (altoBoton + separacion) * 3, anchoBoton, altoBoton);
        add(botonPersonajes);
        botonPersonajes.addActionListener(this);

        botonGanadores = new botonJugar("GANADORES");
        botonGanadores.setBounds(x, yInicial + (altoBoton + separacion) * 4, anchoBoton, altoBoton);
        add(botonGanadores);
        botonGanadores.addActionListener(this);

        botonSalir = new botonJugar("SALIR");
        botonSalir.setBounds(x, yInicial + (altoBoton + separacion) * 5, anchoBoton, altoBoton);
        add(botonSalir);
        botonSalir.addActionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
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