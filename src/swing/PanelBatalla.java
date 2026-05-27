package swing;

import juego.Main;
import juego.Personajes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panel encargado del combate.
 * Usa el método turnoEquipo() de Main para ejecutar los turnos.
 */
public class PanelBatalla extends PanelFondo implements ActionListener {

    private JTextArea areaTexto;

    private JButton botonAtacar;
    private JButton botonDefender;
    private JButton botonTerminar;
    private JButton botonSiguiente;
    private JButton botonVolver;

    private int ronda = 1;
    private int indiceTurno = 0;

    private String[] acciones = new String[200];
    private int numAcciones = 0;

    public PanelBatalla() {
        super("/imagenes/Juego.png");

        setLayout(new BorderLayout(10, 10));

        // ================= PANEL SUPERIOR =================
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.setOpaque(false);

        botonVolver = new botonEstilo("VOLVER");
        botonVolver.addActionListener(this);

        panelSuperior.add(botonVolver);

        add(panelSuperior, BorderLayout.NORTH);

        // ================= AREA TEXTO =================
        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        areaTexto.setFont(new Font("Serif", Font.PLAIN, 18));
        areaTexto.setMargin(new Insets(10, 10, 10, 10));
        areaTexto.setBackground(new Color(230, 210, 140));

        JScrollPane scroll = new JScrollPane(areaTexto);

        add(scroll, BorderLayout.CENTER);

        // ================= PANEL BOTONES =================
        JPanel panelBotones = new JPanel(new GridLayout(1, 4, 10, 10));
        panelBotones.setOpaque(false);

        botonAtacar = new botonEstilo("ATACAR");
        botonDefender = new botonEstilo("DEFENDER");
        botonTerminar = new botonEstilo("TERMINAR TURNO");
        botonSiguiente = new botonEstilo("SIGUIENTE TURNO");

        botonAtacar.addActionListener(this);
        botonDefender.addActionListener(this);
        botonTerminar.addActionListener(this);
        botonSiguiente.addActionListener(this);

        panelBotones.add(botonAtacar);
        panelBotones.add(botonDefender);
        panelBotones.add(botonTerminar);
        panelBotones.add(botonSiguiente);

        add(panelBotones, BorderLayout.SOUTH);

        iniciarRonda();
    }

    /**
     * Inicia la ronda mostrando el estado de todos los personajes.
     */
    private void iniciarRonda() {
        areaTexto.setText("===== RONDA " + ronda + " =====\n\n");
        areaTexto.append(Main.mostrarEstadoRonda(Main.equipos));
        mostrarTurnoActual();
    }

    /**
     * Muestra qué personaje está jugando.
     */
    private void mostrarTurnoActual() {

        while (indiceTurno < Main.equipos.length
                && Main.equipos[indiceTurno].getVidas() <= 0) {
            indiceTurno++;
        }

        if (indiceTurno >= Main.equipos.length) {
            terminarRonda();
            return;
        }

        Personajes actual = Main.equipos[indiceTurno];

        areaTexto.append("\n----------------------------------\n");
        areaTexto.append("Turno de: " + actual.getNombre() + "\n");
        areaTexto.append("Vidas: " + actual.getVidas() + "\n");
        areaTexto.append("Misiles: " + actual.getMisilesRonda() + "\n");
    }

    /**
     * Ejecuta una acción usando turnoEquipo().
     */
    private void ejecutarTurno() {

        // Scanner vacío porque turnoEquipo obliga a recibir Scanner.
        java.util.Scanner sc = new java.util.Scanner(System.in);

        numAcciones = Main.turnoEquipo(
                Main.equipos,
                indiceTurno,
                sc,
                acciones,
                numAcciones,
                ronda
        );

        mostrarAcciones();

        indiceTurno++;
        mostrarTurnoActual();
    }

    /**
     * Muestra las acciones realizadas.
     */
    private void mostrarAcciones() {

        areaTexto.append("\n===== ACCIONES =====\n");

        for (int i = 0; i < numAcciones; i++) {
            areaTexto.append(acciones[i] + "\n");
        }
    }

    /**
     * Finaliza la ronda.
     */
    private void terminarRonda() {

        for (Personajes p : Main.equipos) {
            if (p.getVidas() > 0) {
                p.reiniciarMisilesRonda();
            }
        }

        areaTexto.append("\n===== FIN DE RONDA =====\n\n");
        areaTexto.append(Main.mostrarEstadoRonda(Main.equipos));

        if (Main.equiposVivos(Main.equipos) <= 1) {
            areaTexto.append("\n\n¡PARTIDA TERMINADA!");

            botonAtacar.setEnabled(false);
            botonDefender.setEnabled(false);
            botonTerminar.setEnabled(false);
            botonSiguiente.setEnabled(false);

            return;
        }

        ronda++;
        indiceTurno = 0;

        areaTexto.append("\n\nPulsa SIGUIENTE TURNO para comenzar la nueva ronda.\n");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this);

        if (e.getSource() == botonVolver) {
            ventana.setContentPane(new PanelMenu());
            ventana.revalidate();
            ventana.repaint();
        }

        if (Main.equipos == null) {
            areaTexto.setText("Primero debes crear los equipos.");
            return;
        }

        // ================= ATACAR =================
        if (e.getSource() == botonAtacar) {
            ejecutarTurno();
        }

        // ================= DEFENDER =================
        if (e.getSource() == botonDefender) {
            ejecutarTurno();
        }

        // ================= TERMINAR =================
        if (e.getSource() == botonTerminar) {
            indiceTurno++;
            mostrarTurnoActual();
        }

        // ================= SIGUIENTE =================
        if (e.getSource() == botonSiguiente) {

            if (indiceTurno >= Main.equipos.length) {
                iniciarRonda();
            } else {
                mostrarTurnoActual();
            }
        }
    }
}