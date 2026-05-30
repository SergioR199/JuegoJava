package swing;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import juego.Sonidos;

public class PanelMenu extends PanelFondo implements ActionListener {
    private JButton botonJugar, botonReglas, botonCreadores, botonPersonajes, botonGanadores, botonSalir;
    private Image fondo;
    private JButton botonMute;
    private boolean muted = false;

    private ImageIcon iconoSonido;
    private ImageIcon iconoMute;

    public PanelMenu() {
        super("/imagenes/leagueoflatam.png");
        setLayout(null);

        fondo = new ImageIcon(getClass().getResource("/Imagenes/fondo.png")).getImage();
        Sonidos.reproducirMusica("/audio/menu.wav");

        int anchoBoton = 260;
        int altoBoton = 35;
        int x = 510;
        int yInicial = 300;
        int separacion = 20;

        botonJugar = new botonEstilo("JUGAR");
        botonJugar.setBounds(x, yInicial, anchoBoton, altoBoton);
        add(botonJugar);
        botonJugar.addActionListener(this);

        botonReglas = new botonEstilo("REGLAS DEL JUEGO");
        botonReglas.setBounds(x, yInicial + (altoBoton + separacion) * 1, anchoBoton, altoBoton);
        add(botonReglas);
        botonReglas.addActionListener(this);

        botonCreadores = new botonEstilo("INFORMACIÓN GENERAL");
        botonCreadores.setBounds(475, yInicial + (altoBoton + separacion) * 2, anchoBoton * 5/4, altoBoton);
        add(botonCreadores);
        botonCreadores.addActionListener(this);

        botonPersonajes = new botonEstilo("INFO PERSONAJES");
        botonPersonajes.setBounds(x, yInicial + (altoBoton + separacion) * 3, anchoBoton, altoBoton);
        add(botonPersonajes);
        botonPersonajes.addActionListener(this);

        botonGanadores = new botonEstilo("GANADORES");
        botonGanadores.setBounds(x, yInicial + (altoBoton + separacion) * 4, anchoBoton, altoBoton);
        add(botonGanadores);
        botonGanadores.addActionListener(this);

        botonSalir = new botonEstilo("SALIR");
        botonSalir.setBounds(x, yInicial + (altoBoton + separacion) * 5, anchoBoton, altoBoton);
        add(botonSalir);
        botonSalir.addActionListener(this);

        iconoSonido = new ImageIcon(getClass().getResource("/imagenes/mute.png"));
        iconoMute = new ImageIcon(getClass().getResource("/imagenes/demute.png"));

        Image imgSonido = iconoSonido.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        Image imgMute = iconoMute.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);

        iconoSonido = new ImageIcon(imgSonido);
        iconoMute = new ImageIcon(imgMute);

        botonMute = new JButton(iconoSonido);
        botonMute.setBounds(20, 20, 50, 50);

        botonMute.setContentAreaFilled(false);
        botonMute.setBorderPainted(false);
        botonMute.setFocusPainted(false);
        botonMute.setOpaque(false);
        botonMute.setBorder(BorderFactory.createLineBorder( new Color(230, 210, 140)));

        add(botonMute);
        botonMute.addActionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this);

        Sonidos.reproducirEfecto("/audio/click.wav");

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
            Sonidos.detenerMusica();
            System.exit(0);
        }else if (e.getSource().equals(botonMute)) {
                muted = !muted;

                if (muted) {
                    Sonidos.detenerMusica();
                    botonMute.setIcon(iconoMute);
                } else {
                    Sonidos.reproducirMusica("/audio/menu.wav");
                    botonMute.setIcon(iconoSonido);
                }
            }
        }
    }
