package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import juego.Ganadores;
import juego.Sonidos;

/**
 * Clase para la pantalla que enseña el fichero con los ganadores de cada partida.
 */

public class PanelGanadores extends PanelFondo implements ActionListener {

    private JButton botonVolver;
    private JTextArea areaTexto;

    /**
     * Constructor de la pantalla de ganadores.
     * Inicializa la ventana
     * crea botones, configura el área de
     * texto que muestra los ganadores almacenados.
     */

    public PanelGanadores() {
        super("/imagenes/ganadores.png");
        setLayout(new BorderLayout());


        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.setOpaque(false);

        botonVolver = new botonEstilo("VOLVER");
        panelSuperior.add(botonVolver);

        add(panelSuperior, BorderLayout.NORTH);

        botonVolver.addActionListener(this);


        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        areaTexto.setMargin(new Insets(10, 10, 10, 10));

        areaTexto.setText(Ganadores.getGanadores());

        areaTexto.setFont(new Font("Serif", Font.PLAIN, 18));
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
        panelCentro.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
        panelCentro.add(scroll);

        add(panelCentro, BorderLayout.CENTER);
    }
    /**
     * Metodo que Gestiona los componentes gráficos.
     * Si se pulsa el botón volver, reproduce un sonido y cambia
     * la pantalla actual mostrando nuevamente el menú principal.
     *
     * @param e evento generado por la interacción del usuario
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this);
        Sonidos.reproducirEfecto("/audio/click.wav");

        if (e.getSource().equals(botonVolver)) {
            ventana.setContentPane(new PanelMenu());
            ventana.revalidate();
            ventana.repaint();
        }
    }
}