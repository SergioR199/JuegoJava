package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import juego.Main;

public class PanelJuego extends PanelFondo implements ActionListener {

    private JButton botonVolver;
    private JButton botonCrearEquipos;
    private JTextArea areaTexto;

    public PanelJuego() {
        super("/imagenes/Juego.png");
        setLayout(new BorderLayout());


        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.setOpaque(false);

        botonVolver = new botonJugar("VOLVER");
        botonCrearEquipos = new botonJugar("CREAR EQUIPOS");

        panelSuperior.add(botonVolver);
        panelSuperior.add(Box.createHorizontalStrut(15)); // 👈 separación
        panelSuperior.add(botonCrearEquipos);

        add(panelSuperior, BorderLayout.NORTH);

        botonVolver.addActionListener(this);
        botonCrearEquipos.addActionListener(this);


        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        areaTexto.setMargin(new Insets(10, 10, 10, 10));

        areaTexto.setFont(new Font("Serif", Font.PLAIN, 16));
        areaTexto.setForeground(Color.black);
        areaTexto.setBackground(new Color(230, 210, 140));

        areaTexto.setBorder(
                BorderFactory.createLineBorder(
                        new Color(212, 175, 55),
                        3
                )
        );

        JScrollPane scroll = new JScrollPane(areaTexto);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setPreferredSize(new Dimension(650, 350));

        JPanel panelCentro = new JPanel(new GridBagLayout());
        panelCentro.setOpaque(false);
        panelCentro.add(scroll);

        add(panelCentro, BorderLayout.CENTER);

        areaTexto.setText("Pulsa 'CREAR EQUIPOS' para empezar la partida.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this);

        if (e.getSource().equals(botonVolver)) {
            ventana.setContentPane(new PanelMenu());
            ventana.revalidate();
            ventana.repaint();
        }

        else if (e.getSource().equals(botonCrearEquipos)) {
            int numEquipos = Main.pedirNumeroEquipos();

            if (numEquipos == -1) {
                areaTexto.setText("Creación de equipos cancelada.");
                return;
            }

            Main.equipos = new juego.Personajes[numEquipos];
            Main.crearEquipos(Main.equipos);

            areaTexto.setText("Equipos creados correctamente.\n\n");
            areaTexto.append(Main.mostrarEstadoRonda(Main.equipos));
        }
    }
}