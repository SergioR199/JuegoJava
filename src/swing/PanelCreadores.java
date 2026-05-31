package swing;

import juego.InformacionJuego;
import juego.Sonidos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Clase para dar informacion sobre creadores
 */
public class PanelCreadores extends PanelFondo implements ActionListener {

    private JButton botonVolver;
    private JTextArea areaTexto;

    /**
     * Metodo para la interfaz
     */

    public PanelCreadores() {
        super("/imagenes/infoGene.png");
        setLayout(new BorderLayout());


        botonVolver = new botonEstilo("VOLVER");

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.setOpaque(false);
        panelSuperior.add(botonVolver);

        add(panelSuperior, BorderLayout.NORTH);


        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        areaTexto.setMargin(new Insets(10, 10, 10, 10));

        areaTexto.setText(InformacionJuego.getInformacionGeneral());


        areaTexto.setFont(new Font("Serif", Font.PLAIN, 20));
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

        botonVolver.addActionListener(this);
    }

    /**
     * Metodo para los sonidos y esteticas.
     *
     * @param e the event to be processed
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