package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import juego.InformacionJuego;

public class PanelReglas extends PanelFondo implements ActionListener {

    private JButton botonVolver;
    private JTextArea areaTexto;

    public PanelReglas() {
        super("/imagenes/reglas.png");
        setLayout(new BorderLayout());


        botonVolver = new botonJugar("VOLVER");

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

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this);

        if (e.getSource().equals(botonVolver)) {
            ventana.setContentPane(new PanelMenu());
            ventana.revalidate();
            ventana.repaint();
        }
    }
}