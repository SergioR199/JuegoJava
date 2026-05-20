package swing;

import juego.InformacionJuego;

import javax.swing.*;
import java.awt.event.*;

public class PanelInfoPersonajes extends PanelFondo implements ActionListener {

    private JButton botonAtras;
    private JComboBox<String> comboPersonajes;
    private JTextArea areaTexto;

    public PanelInfoPersonajes() {
        super("/imagenes/infoPerso1.png");
        setLayout(null);

        JLabel titulo = new JLabel("INFORMACIÓN DE PERSONAJES");
        titulo.setBounds(280, 40, 250, 30);
        add(titulo);

        String[] personajes = {
                "Jinx", "Jhin", "Gangplank", "Rumble", "Heimerdinger",
                "Kog'Maw", "Aurelion Sol", "Sylas", "Tristana",
                "Xerath", "Ziggs", "Corki", "Personalizado", "Aleatorio"
        };

        comboPersonajes = new JComboBox<>(personajes);
        comboPersonajes.setBounds(250, 90, 250, 30);
        add(comboPersonajes);
        comboPersonajes.addActionListener(this);

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        areaTexto.setText("Selecciona un personaje para ver su información.");

        JScrollPane scroll = new JScrollPane(areaTexto);
        scroll.setBounds(250, 110, 200, 200);
        add(scroll);

        botonAtras = new JButton("Atrás");
        botonAtras.setBounds(20, 20, 120, 35);
        add(botonAtras);
        botonAtras.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this);

        if (e.getSource().equals(botonAtras)) {
            ventana.setContentPane(new PanelMenu());
            ventana.revalidate();
            ventana.repaint();
        }

        if (e.getSource().equals(comboPersonajes)) {
            String personajeSeleccionado = (String) comboPersonajes.getSelectedItem();
            areaTexto.setText(InformacionJuego.getInfoPersonaje(personajeSeleccionado));
        }
    }
}