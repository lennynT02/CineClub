import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.toedter.calendar.JCalendar;
import javax.swing.*;

public class Reservar {
    JPanel mainPanel = new JPanel(new BorderLayout());
    GridBagConstraints mainGbc = new GridBagConstraints();

    static JPanel dinamicPanel = new JPanel();

    JPanel fecha = new JPanel(new GridBagLayout());
    GridBagConstraints fechaGbc = new GridBagConstraints();

    JLabel imagen = new JLabel();

    JCalendar calendario = new JCalendar();

    JTextPane hora1 = new JTextPane();
    JTextPane hora2 = new JTextPane();
    JTextPane hora3 = new JTextPane();

    JPanel asientos = new JPanel(new GridBagLayout());
    GridBagConstraints asientosGbc = new GridBagConstraints();

    JPanel boletos = new JPanel(new GridBagLayout());
    GridBagConstraints boletosGbc = new GridBagConstraints();

    JPanel pago = new JPanel(new GridBagLayout());
    GridBagConstraints pagoGbc = new GridBagConstraints();

    JPanel tarjeta = new JPanel(new GridBagLayout());
    GridBagConstraints tarjetaGbc = new GridBagConstraints();

    JPanel detalles = new JPanel(new GridBagLayout());
    GridBagConstraints detallesGbc = new GridBagConstraints();

    RoundedButton reservar1 = new RoundedButton("RESERVAR", Color.decode("#732626"), Color.decode("#d99748"),
            Color.decode("#ffffff"), Color.decode("#732626"), 35);

    public Reservar(JLabel imagen) {
        this.imagen = imagen;
        JLabel imagen2 = new JLabel(imagen.getIcon());
        dinamicPanel.setBackground(new Color(0, 0, 0));
        dinamicPanel.setPreferredSize(new Dimension(800, 600));

        // Fecha
        fecha.setBackground(new Color(0, 0, 0));

        fechaGbc.gridx = 0;
        fechaGbc.gridy = 0;
        fechaGbc.gridheight = 4;
        fechaGbc.anchor = GridBagConstraints.CENTER;
        fechaGbc.insets = new Insets(30, 30, 30, 30);
        fecha.add(imagen2, fechaGbc);

        fechaGbc.gridx = 1;
        fechaGbc.gridy = 4;
        fechaGbc.gridheight = 1;
        fecha.add(calendario, fechaGbc);

        hora1.setPreferredSize(new Dimension(200, 50));
        hora1.setBackground(Color.decode("#c3c0c1"));
        hora1.setFont(new Font("Arial", Font.BOLD, 20));
        hora1.setText("12:00 PM");
        hora1.setEditable(false);
        hora1.setFocusable(false);
        hora1.setBorder(null);
        hora1.setCaret(null);

        fechaGbc.gridx = 1;
        fechaGbc.gridy = 1;
        fecha.add(hora1, fechaGbc);

        hora2.setPreferredSize(new Dimension(200, 50));
        hora2.setBackground(Color.decode("#c3c0c1"));
        hora2.setFont(new Font("Arial", Font.BOLD, 20));
        hora2.setText("3:00 PM");
        hora2.setEditable(false);
        hora2.setFocusable(false);
        hora2.setBorder(null);
        hora2.setCaret(null);

        fechaGbc.gridy = 2;
        fecha.add(hora2, fechaGbc);

        hora3.setPreferredSize(new Dimension(200, 50));
        hora3.setBackground(Color.decode("#c3c0c1"));
        hora3.setFont(new Font("Arial", Font.BOLD, 20));
        hora3.setText("6:00 PM");
        hora3.setEditable(false);
        hora3.setFocusable(false);
        hora3.setBorder(null);
        hora3.setCaret(null);

        fechaGbc.gridy = 3;
        fecha.add(hora3, fechaGbc);

        reservar1.setPreferredSize(new Dimension(200, 50));
        fechaGbc.gridx = 0;
        fechaGbc.gridy = 4;
        fecha.add(reservar1, fechaGbc);

        dinamicPanel.add(fecha);
        mainPanel.add(dinamicPanel, BorderLayout.CENTER);

        reservar1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                asientos.setPreferredSize(
                        new Dimension((int) Login.screenSize.getWidth(), ((int) Login.screenSize.getHeight())));
                dinamicPanel.removeAll();
                dinamicPanel.add(asientos);
                dinamicPanel.revalidate();
                dinamicPanel.repaint();

            }
        });
        // Asientos
        asientos.setBackground(new Color(0, 0, 0));
        asientosGbc.insets = new Insets(5, 5, 5, 5);

        // Crear botones de asientos
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JButton asiento = new JButton(i + "-" + j);
                asientosGbc.gridx = j;
                asientosGbc.gridy = i;
                asientos.add(asiento, asientosGbc);
            }
        }

        // Botón para retroceder al panel de fecha
        RoundedButton btnRetroceder = new RoundedButton("Retroceder", Color.decode("#732626"), Color.decode("#d99748"),
                Color.decode("#ffffff"), Color.decode("#732626"), 35);
        btnRetroceder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dinamicPanel.removeAll();
                dinamicPanel.add(fecha);
                dinamicPanel.revalidate();
                dinamicPanel.repaint();
            }
        });

        asientosGbc.gridx = 0;
        asientosGbc.gridy = 10;
        asientos.add(btnRetroceder, asientosGbc);

        // Botón para avanzar al panel de boletos
        RoundedButton btnAvanzar = new RoundedButton("Avanzar", Color.decode("#732626"), Color.decode("#d99748"),
                Color.decode("#ffffff"), Color.decode("#732626"), 35);
        btnAvanzar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dinamicPanel.removeAll();
                dinamicPanel.add(boletos);
                dinamicPanel.revalidate();
                dinamicPanel.repaint();
            }
        });

        asientosGbc.gridx = 1;
        asientos.add(btnAvanzar, asientosGbc);

        // Boletos
        boletos.setBackground(new Color(0, 0, 0));
        boletosGbc.insets = new Insets(5, 5, 5, 5);

        // Etiqueta para mostrar el total de asientos seleccionados
        JLabel lblTotalAsientos = new JLabel();
        lblTotalAsientos.setForeground(Color.WHITE);
        boletosGbc.gridx = 0;
        boletosGbc.gridy = 0;
        boletos.add(lblTotalAsientos, boletosGbc);

        // Etiqueta para mostrar el costo total
        JLabel lblCostoTotal = new JLabel();
        lblCostoTotal.setForeground(Color.WHITE);
        boletosGbc.gridy = 1;
        boletos.add(lblCostoTotal, boletosGbc);

        // Botón para ir al panel de pago
        RoundedButton btnIrAPago = new RoundedButton("Ir a Pago", Color.decode("#732626"), Color.decode("#d99748"),
                Color.decode("#ffffff"), Color.decode("#732626"), 35);
        btnIrAPago.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dinamicPanel.removeAll();
                dinamicPanel.add(pago);
                dinamicPanel.revalidate();
                dinamicPanel.repaint();
            }
        });

        boletosGbc.gridy = 2;
        boletos.add(btnIrAPago, boletosGbc);

        // Botón para regresar al panel de asientos
        RoundedButton btnRegresarAAsientos = new RoundedButton("Regresar a Asientos", Color.decode("#732626"), Color.decode("#d99748"),
                Color.decode("#ffffff"), Color.decode("#732626"), 35);
        btnRegresarAAsientos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dinamicPanel.removeAll();
                dinamicPanel.add(asientos);
                dinamicPanel.revalidate();
                dinamicPanel.repaint();
            }
        });

        boletosGbc.gridy = 3;
        boletos.add(btnRegresarAAsientos, boletosGbc);

        // Pago
        pago.setBackground(new Color(0, 0, 0));
        pagoGbc.insets = new Insets(5, 5, 5, 5);

        // Checkbox para seleccionar el método de pago
        JCheckBox chkEfectivo = new JCheckBox("Efectivo");
        JCheckBox chkTarjeta = new JCheckBox("Tarjeta");
        chkEfectivo.setForeground(Color.WHITE);
        chkTarjeta.setForeground(Color.WHITE);
        pagoGbc.gridx = 0;
        pagoGbc.gridy = 0;
        pago.add(chkEfectivo, pagoGbc);
        pagoGbc.gridy = 1;
        pago.add(chkTarjeta, pagoGbc);

        // Botón para confirmar la selección
        RoundedButton btnConfirmar = new RoundedButton("Confirmar", Color.decode("#732626"), Color.decode("#d99748"),
                Color.decode("#ffffff"), Color.decode("#732626"), 35);
        btnConfirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (chkEfectivo.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Acércate a cualquiera de nuestras ventanillas y cancela.\n\n"
                            + "Recuerda ir 30 minutos antes de que comience la película.\n\n"
                            + "SU PAGO A REALIZAR ES DE: $7.20");
                } else if (chkTarjeta.isSelected()) {
                    dinamicPanel.removeAll();
                    dinamicPanel.add(tarjeta);
                    dinamicPanel.revalidate();
                    dinamicPanel.repaint();
                }
            }
        });

        pagoGbc.gridy = 2;
        pago.add(btnConfirmar, pagoGbc);

        // Tarjeta
        tarjeta.setBackground(new Color(0, 0, 0));
        tarjetaGbc.insets = new Insets(5, 5, 5, 5);

        // Campos del formulario de pago
        JTextField txtNumeroTarjeta = new JTextField("xxxx – xxxx – xxxx – xxxx");
        JTextField txtValidacion = new JTextField("MM/AA");
        JTextField txtTitular = new JTextField("Sr./Sra. Nombre Apellido");
        JTextField txtCVV = new JTextField("XXX");
        JTextField txtEmisor = new JTextField();

        tarjetaGbc.gridx = 0;
        tarjetaGbc.gridy = 0;
        tarjeta.add(txtNumeroTarjeta, tarjetaGbc);
        tarjetaGbc.gridy = 1;
        tarjeta.add(txtValidacion, tarjetaGbc);
        tarjetaGbc.gridy = 2;
        tarjeta.add(txtTitular, tarjetaGbc);
        tarjetaGbc.gridy = 3;
        tarjeta.add(txtCVV, tarjetaGbc);
        tarjetaGbc.gridy = 4;
        tarjeta.add(txtEmisor, tarjetaGbc);

        // Checkbox para seleccionar el tipo de tarjeta
        JCheckBox chkVisa = new JCheckBox("Visa");
        JCheckBox chkMastercard = new JCheckBox("Mastercard");
        JCheckBox chkAmex = new JCheckBox("Amex");
        chkVisa.setForeground(Color.WHITE);
        chkMastercard.setForeground(Color.WHITE);
        chkAmex.setForeground(Color.WHITE);
        tarjetaGbc.gridy = 5;
        tarjeta.add(chkVisa, tarjetaGbc);
        tarjetaGbc.gridy = 6;
        tarjeta.add(chkMastercard, tarjetaGbc);
        tarjetaGbc.gridy = 7;
        tarjeta.add(chkAmex, tarjetaGbc);

        // Botón para confirmar el pago
        RoundedButton btnPagar = new RoundedButton("Pagar", Color.decode("#732626"), Color.decode("#d99748"),
                Color.decode("#ffffff"), Color.decode("#732626"), 35);
        btnPagar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Su pago se realizó con éxito\n\n"
                        + "SU PAGO FUE DE: $7.20");
                dinamicPanel.removeAll();
                dinamicPanel.add(detalles);
                dinamicPanel.revalidate();
                dinamicPanel.repaint();
            }
        });

        tarjetaGbc.gridy = 8;
        tarjeta.add(btnPagar, tarjetaGbc);

        // Detalles
        detalles.setBackground(new Color(0, 0, 0));
        detallesGbc.insets = new Insets(5, 5, 5, 5);

         // Reemplaza con la ruta a la imagen de la película
        detallesGbc.gridx = 0;
        detallesGbc.gridy = 0;
        detalles.add(imagen, detallesGbc);

        // Etiqueta para mostrar la cantidad de boletos comprados
        JLabel lblCantidadBoletos = new JLabel();
        lblCantidadBoletos.setForeground(Color.WHITE);
        detallesGbc.gridy = 1;
        detalles.add(lblCantidadBoletos, detallesGbc);

        // Etiqueta para mostrar el precio
        JLabel lblPrecio = new JLabel("Precio: $7.20");
        lblPrecio.setForeground(Color.WHITE);
        detallesGbc.gridy = 2;
        detalles.add(lblPrecio, detallesGbc);
    }
}