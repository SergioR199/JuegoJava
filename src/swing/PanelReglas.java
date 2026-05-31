package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import juego.InformacionJuego;
import juego.Sonidos;
/**
 * Clase encargada de mostrar las reglas del juego.
 */

public class PanelReglas extends PanelFondo implements ActionListener {

    private JButton botonVolver;
    private JTextArea areaTexto;
    /**
     * Constructor de la pantalla de reglas.
     *
     * Inicializa todos los componentes gráficos
     * crea el botón para volver al menú principal y configura
     * el área de texto donde se muestran las reglas del juego.
     */

    public PanelReglas() {
        super("/imagenes/reglas.png");
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
        areaTexto.setMargin(new Insets(13, 13, 13, 13));

        areaTexto.setText(InformacionJuego.getReglas());
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


        JPanel panelCentro = new JPanel(new GridBagLayout());
        panelCentro.setOpaque(false);

        scroll.setPreferredSize(new Dimension(650, 350));
        panelCentro.add(scroll);

        add(panelCentro, BorderLayout.CENTER);

        botonVolver.addActionListener(this);
    }
    /**
     * Gestiona los eventos producidos por los componentes gráficos.
     *
     * Si el usuario pulsa el botón volver, reproduce un sonido
     * y regresa a la pantalla principal del menú.
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