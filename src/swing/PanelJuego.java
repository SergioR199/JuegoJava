package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import juego.Main;
import juego.Personajes;
import juego.Sonidos;

/**
 * Panel donde se crean los equipos y se eligen los personajes.
 * Tiene botones arriba para navegar, un área de texto en el centro
 * y los botones de personajes abajo en cuadrícula.
 */
public class PanelJuego extends PanelFondo implements ActionListener {

    private JButton botonVolver;
    private JButton botonCrearEquipos;
    private JTextArea areaTexto;
    private JPanel panelPersonajes;
    private int numEquipos;
    private int indiceActual;
    private boolean creandoEquipos = false;

    /**
     * Constructor que crea el panel con todos sus componentes.
     * Pone los botones arriba, el área de texto en el centro
     * y la cuadrícula de personajes abajo.
     */
    public PanelJuego() {
        super("/imagenes/Juego.png");
        setLayout(new BorderLayout(10, 10));

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.setOpaque(false);

        botonVolver = new botonEstilo("VOLVER");
        botonCrearEquipos = new botonEstilo("CREAR EQUIPOS");

        panelSuperior.add(botonVolver);
        panelSuperior.add(Box.createHorizontalStrut(15));
        panelSuperior.add(botonCrearEquipos);

        add(panelSuperior, BorderLayout.NORTH);

        botonVolver.addActionListener(this);
        botonCrearEquipos.addActionListener(this);

        // Área de texto del centro
        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        areaTexto.setMargin(new Insets(12, 12, 12, 12));
        areaTexto.setFont(new Font("Serif", Font.PLAIN, 18));
        areaTexto.setForeground(Color.BLACK);
        areaTexto.setBackground(new Color(230, 210, 140));
        areaTexto.setBorder(BorderFactory.createLineBorder(new Color(212, 175, 55), 3));
        areaTexto.setText("Pulsa 'CREAR EQUIPOS' para empezar la partida.");

        JScrollPane scroll = new JScrollPane(areaTexto);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setPreferredSize(new Dimension(700, 260));

        JPanel panelTexto = new JPanel(new GridBagLayout());
        panelTexto.setOpaque(false);
        panelTexto.add(scroll);

        add(panelTexto, BorderLayout.CENTER);

        panelPersonajes = new JPanel(new GridLayout(7, 2, 10, 10));
        panelPersonajes.setOpaque(false);
        panelPersonajes.setBorder(BorderFactory.createEmptyBorder(10, 80, 20, 80));

        String[] personajes = {
                "Jinx", "Jhin", "Gangplank", "Rumble", "Heimerdinger", "Kog'Maw", "Aurelion Sol",
                "Sylas", "Tristana", "Xerath", "Ziggs", "Corki", "Personalizado", "Aleatorio"
        };

        for (String nombre : personajes) {
            JButton boton = new botonEstilo(nombre.toUpperCase());
            boton.setActionCommand(nombre);
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    seleccionarPersonaje(e.getActionCommand());
                }
            });
            panelPersonajes.add(boton);
        }

        add(panelPersonajes, BorderLayout.SOUTH);
    }

    /**
     * Se ejecuta cuando el usuario pulsa un personaje.
     * Pide el identificador, crea el personaje y lo guarda en el array.
     * Si ya están todos, muestra el resumen final.
     *
     * @param nombrePersonaje El nombre del personaje que se pulsó
     */
    private void seleccionarPersonaje(String nombrePersonaje) {
        if (!creandoEquipos) {
            areaTexto.setText("Primero pulsa 'CREAR EQUIPOS'.");
            return;
        }

        int opcion = convertirPersonajeAOpcion(nombrePersonaje);
        String identificador = Main.pedirIdentificador(Main.equipos);

        if (identificador == null) {
            areaTexto.setText("Creación cancelada por el usuario.");
            return;
        }

        Main.equipos[indiceActual] = Main.crearPersonaje(opcion, identificador);

        areaTexto.setText("Jugador " + (indiceActual + 1) + " creado correctamente.\n\n");
        areaTexto.append(Main.equipos[indiceActual].getNombre()
                + " | ID: " + Main.equipos[indiceActual].getIdentificador()
                + " | Tipo: " + Main.obtenerRaza(Main.equipos[indiceActual])
                + " | Vidas: " + Main.equipos[indiceActual].getVidas()
                + " | Misiles/ronda: " + Main.equipos[indiceActual].getMisilesRonda());

        indiceActual++;

        if (indiceActual >= numEquipos) {
            creandoEquipos = false;
            areaTexto.append("\n\nEquipos creados correctamente.\n\n");
            areaTexto.append(Main.mostrarEstadoRonda(Main.equipos));

            SwingUtilities.getWindowAncestor(this).dispose();

            Ventana ventana = new Ventana();
            ventana.setContentPane(new PanelBatalla());
            ventana.revalidate();
        } else {
            areaTexto.append("\n\nAhora elige personaje para el jugador " + (indiceActual + 1) + ".");
        }
    }

    /**
     * Convierte el nombre del personaje en el número que usa Main.crearPersonaje().
     *
     * @param personaje Nombre del personaje
     * @return Número del 1 al 14
     */
    private int convertirPersonajeAOpcion(String personaje) {
        switch (personaje) {
            case "Jinx": return 1;
            case "Jhin": return 2;
            case "Gangplank": return 3;
            case "Rumble": return 4;
            case "Heimerdinger": return 5;
            case "Kog'Maw": return 6;
            case "Aurelion Sol": return 7;
            case "Sylas": return 8;
            case "Tristana": return 9;
            case "Xerath": return 10;
            case "Ziggs": return 11;
            case "Corki": return 12;
            case "Personalizado": return 13;
            case "Aleatorio": return 14;
            default: return 1;
        }
    }

    /**
     * Se ejecuta cuando pulsas VOLVER o CREAR EQUIPOS.
     * VOLVER te lleva al menú y CREAR EQUIPOS pide cuántos equipos quieres.
     *
     * @param e El evento del botón
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this);
        Sonidos.reproducirEfecto("/audio/click.wav");

        if (e.getSource().equals(botonVolver)) {
            ventana.setContentPane(new PanelMenu());
            ventana.revalidate();
            ventana.repaint();

        } else if (e.getSource().equals(botonCrearEquipos)) {
            String entrada = JOptionPane.showInputDialog(this,
                    "¿Cuántos equipos quieres crear? (mínimo 3):");

            if (entrada == null) {
                areaTexto.setText("Creación de equipos cancelada.");
                return;
            }

            try {
                numEquipos = Integer.parseInt(entrada);

                if (numEquipos < 3) {
                    areaTexto.setText("Debes introducir un número mínimo de 3.");
                    return;
                }

                Main.equipos = new Personajes[numEquipos];
                indiceActual = 0;
                creandoEquipos = true;

                areaTexto.setText("Elige personaje para el jugador 1.");

            } catch (NumberFormatException ex) {
                areaTexto.setText("Has introducido un valor no válido.");
            }
        }
    }
}