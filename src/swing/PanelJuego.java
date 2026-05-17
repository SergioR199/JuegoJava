package swing;

import javax.swing.*;
import java.awt.event.*;
import juego.Main;

public class PanelJuego extends JPanel implements ActionListener {

    private JButton botonVolver;
    private JButton botonCrearEquipos;
    private JTextArea areaTexto;

    public PanelJuego() {
        setLayout(null);

        botonVolver = new JButton("Volver");
        botonVolver.setBounds(20, 20, 100, 30);
        add(botonVolver);
        botonVolver.addActionListener(this);

        botonCrearEquipos = new JButton("Crear equipos");
        botonCrearEquipos.setBounds(140, 20, 150, 30);
        add(botonCrearEquipos);
        botonCrearEquipos.addActionListener(this);

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);

        JScrollPane scroll = new JScrollPane(areaTexto);
        scroll.setBounds(50, 80, 600, 300);
        add(scroll);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this);

        if (e.getSource().equals(botonVolver)) {
            ventana.setContentPane(new PanelMenu());
            ventana.revalidate();
            ventana.repaint();
        } else if (e.getSource().equals(botonCrearEquipos)) {
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