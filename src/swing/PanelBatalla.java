package swing;

import juego.Ganadores;
import juego.Main;
import juego.Personajes;
import juego.Sonidos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase en la que transcurre todo el juego.
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
    private int inicioAccionesRonda = 0;

    private boolean esperandoNuevaRonda = false;
    private boolean partidaTerminada = false;
    private boolean[] participaEnRonda;

    /**
     * Metodo para la configuracion del panel de batalla.
     */

    public PanelBatalla() {
        super("/imagenes/Juego.png");

        setLayout(new BorderLayout(10, 10));

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.setOpaque(false);

        botonVolver = new botonEstilo("VOLVER");
        botonVolver.addActionListener(this);
        panelSuperior.add(botonVolver);

        add(panelSuperior, BorderLayout.NORTH);

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        areaTexto.setFont(new Font("Serif", Font.PLAIN, 18));
        areaTexto.setMargin(new Insets(10, 10, 10, 10));
        areaTexto.setBackground(new Color(230, 210, 140));

        JScrollPane scroll = new JScrollPane(areaTexto);
        add(scroll, BorderLayout.CENTER);

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
     * Metodo para iniciar una nueva ronda cuando acabe la anterior.
     */

    private void iniciarRonda() {
        esperandoNuevaRonda = false;
        inicioAccionesRonda = numAcciones;

        participaEnRonda = new boolean[Main.equipos.length];
        for (int i = 0; i < Main.equipos.length; i++) {
            participaEnRonda[i] = Main.equipos[i] != null && Main.equipos[i].getVidas() > 0;
        }

        areaTexto.append("\n===== RONDA " + ronda + " =====\n\n");
        areaTexto.append(Main.mostrarEstadoRonda(Main.equipos));
        mostrarTurnoActual();
    }

    /**
     * Metodo que te enseña el turno en el que te encuentras
     */
    private void mostrarTurnoActual() {
        while (indiceTurno < Main.equipos.length && !participaEnRonda[indiceTurno]) {
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
     * Metodo para ejecutar el turno actual.
     *
     * @param accion
     */

    private void ejecutarTurno(String accion) {
        if (esperandoNuevaRonda || partidaTerminada) return;

        Personajes actual = Main.equipos[indiceTurno];
        int accionesAntes = numAcciones;
        Main.numMensajesPasivas = 0;

        numAcciones = Main.turnoEquipo(
                Main.equipos, indiceTurno, accion, acciones, numAcciones, ronda
        );

        if (numAcciones == accionesAntes && !accion.equals("TERMINAR")) return;

        mostrarNuevasAcciones(accionesAntes);
        mostrarMensajesPasivas();

        if (accion.equals("TERMINAR") || actual.getMisilesRonda() <= 0) {
            indiceTurno++;
        }

        mostrarTurnoActual();
    }

    /**
     * Metodo que muestra un mensaje cuando se usa una pasiva.
     */

    private void mostrarMensajesPasivas() {
        for (int i = 0; i < Main.numMensajesPasivas; i++) {
            areaTexto.append(" ⚡ " + Main.mensajesPasivas[i] + "\n");
        }
        Main.numMensajesPasivas = 0;
    }

    /**
     * Metodo que enseña las acciones nuevas.
     *
     * @param desde
     */

    private void mostrarNuevasAcciones(int desde) {
        for (int i = desde; i < numAcciones; i++) {
            areaTexto.append("\n" + acciones[i] + "\n");
        }
    }

    /**
     * Metodo para mostrar las acciones efectuadas en la ronda
     */

    private void mostrarAccionesDeLaRonda() {
        areaTexto.append("\n===== ACCIONES DE LA RONDA =====\n");

        if (inicioAccionesRonda == numAcciones) {
            areaTexto.append("No se ha realizado ninguna acción en esta ronda.\n");
            return;
        }

        for (int i = inicioAccionesRonda; i < numAcciones; i++) {
            areaTexto.append(acciones[i] + "\n");
        }
    }

    /**
     * Metodo para terminar una ronda cuando se acaben las acciones.
     */

    private void terminarRonda() {
        areaTexto.append("\n===== FIN DE RONDA =====\n\n");
        areaTexto.append(Main.mostrarEstadoRonda(Main.equipos));
        areaTexto.append("\n");

        mostrarAccionesDeLaRonda();

        if (Main.equiposVivos(Main.equipos) <= 1) {
            partidaTerminada = true;
            mostrarGanadorYGuardar();

            botonAtacar.setEnabled(false);
            botonDefender.setEnabled(false);
            botonTerminar.setEnabled(false);
            botonSiguiente.setEnabled(false);
            return;
        }

        for (Personajes p : Main.equipos) {
            if (p.getVidas() > 0) {
                p.reiniciarMisilesRonda();
            }
        }

        ronda++;
        indiceTurno = 0;
        esperandoNuevaRonda = true;

        areaTexto.append("\nPulsa SIGUIENTE TURNO para comenzar la nueva ronda.\n");
    }

    /**
     * Metodo para enseñar los ganadores al acabar la partida.
     */
    private void mostrarGanadorYGuardar() {

        for (Personajes p : Main.equipos) {
            if (p.getVidas() > 0) {
                areaTexto.append("\n¡PARTIDA TERMINADA!\n");
                areaTexto.append("GANADOR: " + p.getNombre() + " [" + p.getIdentificador() + "]\n");
                Ganadores.anunciarGanador(Main.equipos, Main.numeroPartida);
                Main.numeroPartida++;
                return;
            }
        }

        areaTexto.append("\n¡PARTIDA TERMINADA!\n");
        areaTexto.append("RESULTADO: EMPATE\n");
        Ganadores.anunciarGanador(Main.equipos, Main.numeroPartida);
        Main.numeroPartida++;
    }

    /**
     * Metodo para la interfaz
     *
     * @param e Evento generado por la interacción del usuario
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this);
        Sonidos.reproducirEfecto("/audio/click.wav");

        if (e.getSource() == botonVolver) {
            ventana.setContentPane(new PanelMenu());
            ventana.revalidate();
            ventana.repaint();
            return;
        }

        if (Main.equipos == null) {
            areaTexto.setText("Primero debes crear los equipos.");
            return;
        }

        if (e.getSource() == botonAtacar) {
            ejecutarTurno("ATACAR");
        }

        if (e.getSource() == botonDefender) {
            ejecutarTurno("DEFENDER");
        }

        if (e.getSource() == botonTerminar) {
            ejecutarTurno("TERMINAR");
        }

        if (e.getSource() == botonSiguiente) {
            if (esperandoNuevaRonda && !partidaTerminada) {
                iniciarRonda();
            }
        }
    }
}