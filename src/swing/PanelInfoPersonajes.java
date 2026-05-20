package swing;

import juego.InformacionJuego;

import javax.swing.*;
import java.awt.*;

public class PanelInfoPersonajes extends JPanel {

    private JButton botonAtras;
    private JComboBox<String> comboPersonajes;
    private JTextArea areaTexto;
    private JLabel etiquetaImagen;

    public PanelInfoPersonajes() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel panelSuperior = new JPanel(new BorderLayout());
        botonAtras = new JButton("Atrás");
        panelSuperior.add(botonAtras, BorderLayout.WEST);

        JLabel titulo = new JLabel("Información de personajes", SwingConstants.CENTER);
        panelSuperior.add(titulo, BorderLayout.CENTER);

        add(panelSuperior, BorderLayout.NORTH);

        String[] personajes = {
                "Jinx", "Jhin", "Gangplank", "Rumble", "Heimerdinger",
                "Kog'Maw", "Aurelion Sol", "Sylas", "Tristana",
                "Xerath", "Ziggs", "Corki", "Personalizado", "Aleatorio"
        };

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        comboPersonajes = new JComboBox<>(personajes);
        comboPersonajes.setPreferredSize(new Dimension(260, 30));
        comboPersonajes.setMaximumSize(new Dimension(260, 30));

        JPanel panelCombo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelCombo.add(comboPersonajes);

        JPanel panelInfo = new JPanel(new BorderLayout(15, 15));
        panelInfo.setPreferredSize(new Dimension(600, 280));
        panelInfo.setMaximumSize(new Dimension(600, 280));
        panelInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        etiquetaImagen = new JLabel();
        etiquetaImagen.setPreferredSize(new Dimension(180, 220));
        etiquetaImagen.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaImagen.setVerticalAlignment(SwingConstants.CENTER);
        etiquetaImagen.setBorder(BorderFactory.createEtchedBorder());

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        areaTexto.setMargin(new Insets(10, 10, 10, 10));
        areaTexto.setText("Selecciona un personaje para ver su información.");

        JScrollPane scrollTexto = new JScrollPane(areaTexto);

        panelInfo.add(etiquetaImagen, BorderLayout.WEST);
        panelInfo.add(scrollTexto, BorderLayout.CENTER);

        JPanel panelInfoCentrado = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelInfoCentrado.add(panelInfo);

        panelCentral.add(panelCombo);
        panelCentral.add(Box.createVerticalStrut(15));
        panelCentral.add(panelInfoCentrado);

        add(panelCentral, BorderLayout.CENTER);

        botonAtras.addActionListener(e -> {
            JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this);
            ventana.setContentPane(new PanelMenu());
            ventana.revalidate();
            ventana.repaint();
        });

        comboPersonajes.addActionListener(e -> actualizarPersonaje());

        actualizarPersonaje();
    }

    private void actualizarPersonaje() {
        String personajeSeleccionado = (String) comboPersonajes.getSelectedItem();
        areaTexto.setText(InformacionJuego.getInfoPersonaje(personajeSeleccionado));
        areaTexto.setCaretPosition(0);

        String ruta = obtenerRutaImagen(personajeSeleccionado);
        ImageIcon icono = new ImageIcon(ruta);
        Image imagenEscalada = icono.getImage().getScaledInstance(160, 200, Image.SCALE_SMOOTH);
        etiquetaImagen.setIcon(new ImageIcon(imagenEscalada));
    }

    private String obtenerRutaImagen(String personaje) {
        switch (personaje) {
            case "Jinx":
                return "src/imagenes/jinx.png";
            case "Jhin":
                return "src/imagenes/jhin.png";
            case "Gangplank":
                return "src/imagenes/gangplank.png";
            case "Rumble":
                return "src/imagenes/rumble.png";
            case "Heimerdinger":
                return "src/imagenes/heimerdinger.png";
            case "Kog'Maw":
                return "src/imagenes/kogmaw.png";
            case "Aurelion Sol":
                return "src/imagenes/aurelionsol.png";
            case "Sylas":
                return "src/imagenes/sylas.png";
            case "Tristana":
                return "src/imagenes/tristana.png";
            case "Xerath":
                return "src/imagenes/xerath.png";
            case "Ziggs":
                return "src/imagenes/ziggs.png";
            case "Corki":
                return "src/imagenes/corki.png";
            case "Personalizado":
                return "src/imagenes/personalizado.png";
            case "Aleatorio":
                return "src/imagenes/aleatorio.png";
            default:
                return "src/imagenes/default.png";
        }
    }
}