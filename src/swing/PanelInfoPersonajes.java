package swing;

import juego.InformacionJuego;

import javax.swing.*;
import java.awt.*;

public class PanelInfoPersonajes extends PanelFondo{

    private JButton botonAtras;
    private JComboBox<String> comboPersonajes;
    private JTextArea areaTexto;
    private JLabel etiquetaImagen;

    public PanelInfoPersonajes() {
        super("/imagenes/infoPerson3.png");
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setOpaque(false);
        botonAtras = new botonEstilo("VOLVER");
        panelSuperior.add(botonAtras, BorderLayout.WEST);



        add(panelSuperior, BorderLayout.NORTH);

        String[] personajes = {
                "Jinx", "Jhin", "Gangplank", "Rumble", "Heimerdinger",
                "Kog'Maw", "Aurelion Sol", "Sylas", "Tristana",
                "Xerath", "Ziggs", "Corki", "Personalizado", "Aleatorio"
        };

        JPanel panelCentral = new JPanel();
        panelCentral.setOpaque(false);
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(80, 0, 0, 0));

        comboPersonajes = new JComboBox<>(personajes);
        comboPersonajes.addActionListener(e -> actualizarPersonaje());
        comboPersonajes.setFont(new Font("Serif", Font.BOLD, 16));
        comboPersonajes.setPreferredSize(new Dimension(150, 40));
        comboPersonajes.setMaximumSize(new Dimension(150, 40));

        comboPersonajes.setBackground(new Color(230, 210, 140));
        comboPersonajes.setForeground(Color.black);
        comboPersonajes.setBorder(
                BorderFactory.createLineBorder(
                        new Color(212, 175, 55),
                        3
                )
        );
        comboPersonajes.setFocusable(false);

        JPanel panelCombo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelCombo.add(comboPersonajes);
        panelCombo.setOpaque(false);


        JPanel panelInfo = new JPanel(new BorderLayout(0, 0));
        panelInfo.setPreferredSize(new Dimension(950, 320));
        panelInfo.setMaximumSize(new Dimension(950, 320));
        panelInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelInfo.setOpaque(false);

        etiquetaImagen = new JLabel();
        etiquetaImagen.setOpaque(false);
        etiquetaImagen.setPreferredSize(new Dimension(520,295));
        etiquetaImagen.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaImagen.setVerticalAlignment(SwingConstants.CENTER);
        etiquetaImagen.setBorder(
                BorderFactory.createLineBorder(
                        new Color(212, 175, 55),
                        5
                )
        );

        areaTexto = new JTextArea();
        areaTexto.setAlignmentX(Component.CENTER_ALIGNMENT);
        areaTexto.setEditable(false);
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        areaTexto.setMargin(new Insets(13, 13, 13, 13));
        areaTexto.setText("Selecciona un personaje para ver su información.");
        areaTexto.setFont(new Font("Serif", Font.PLAIN, 20));
        areaTexto.setForeground(Color.black);
        areaTexto.setBackground(new Color(230, 210, 140));
        areaTexto.setBorder(
                BorderFactory.createLineBorder(
                        new Color(212, 175, 55),
                        3
                )
        );


        JScrollPane scrollTexto = new JScrollPane(areaTexto);

        panelInfo.add(etiquetaImagen, BorderLayout.WEST);
        panelInfo.add(scrollTexto, BorderLayout.CENTER);

        JPanel panelInfoCentrado = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelInfoCentrado.add(panelInfo);
        panelInfoCentrado.setOpaque(false);

        panelCentral.add(panelCombo);
        panelCentral.add(Box.createVerticalStrut(15));
        panelCentral.add(panelInfoCentrado);
        panelCentral.setOpaque(false);
        add(panelCentral, BorderLayout.CENTER);

        botonAtras.addActionListener(e -> {
            JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this);
            ventana.setContentPane(new PanelMenu());
            ventana.revalidate();
            ventana.repaint();
        });
        botonAtras.setPreferredSize(new Dimension(140, 35));



        actualizarPersonaje();
    }

    private void actualizarPersonaje() {
        String personajeSeleccionado = (String) comboPersonajes.getSelectedItem();
        areaTexto.setText(InformacionJuego.getInfoPersonaje(personajeSeleccionado));
        areaTexto.setCaretPosition(0);

        String ruta = obtenerRutaImagen(personajeSeleccionado);
        ImageIcon icono = new ImageIcon(getClass().getResource(ruta));
        Image imagenEscalada = icono.getImage().getScaledInstance(510,295, Image.SCALE_SMOOTH);
        etiquetaImagen.setIcon(new ImageIcon(imagenEscalada));
    }

    private String obtenerRutaImagen(String personaje) {
        switch (personaje) {
            case "Jinx":
                return "/imagenes/jinx.png";
            case "Jhin":
                return "/imagenes/jhin.png";
            case "Gangplank":
                return "/imagenes/gangplank.png";
            case "Rumble":
                return "/imagenes/rumble.png";
            case "Heimerdinger":
                return "/imagenes/heimerdinger.png";
            case "Kog'Maw":
                return "/imagenes/kogmaw.png";
            case "Aurelion Sol":
                return "/imagenes/aurelionsol.png";
            case "Sylas":
                return "/imagenes/sylas.png";
            case "Tristana":
                return "/imagenes/tristana.png";
            case "Xerath":
                return "/imagenes/xerath.png";
            case "Ziggs":
                return "/imagenes/ziggs.png";
            case "Corki":
                return "/imagenes/corki.png";
            case "Personalizado":
                return "/imagenes/personalizado.png";
            case "Aleatorio":
                return "/imagenes/aleatorio.png";
            default:
                return "/imagenes/default.png";
        }
    }
}