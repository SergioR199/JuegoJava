package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import juego.Ganadores;

public class PanelGanadores extends PanelFondo implements ActionListener {

    private JButton botonVolver;
    private JTextArea areaTexto;

    public PanelGanadores() {
        super("/imagenes/ganadores.png");
        setLayout(new BorderLayout());


        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.setOpaque(false);

        botonVolver = new botonJugar("VOLVER");
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