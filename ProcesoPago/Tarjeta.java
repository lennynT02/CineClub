import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tarjeta {

    static JFrame tarjetaPago = new JFrame("Pago realizado");
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JRadioButton VISARadioButton;
    private JRadioButton masterCardRadioButton;
    private JRadioButton paypalRadioButton;
    public JPanel tarjetaPanel;
    private JButton pagarButton;
    private JButton regresarButton;

    public Tarjeta() {
        pagarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tarjetaPago.setContentPane(new TarjetaPago().TarjetaPagoPanel);
                tarjetaPago.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                tarjetaPago.setVisible(true);
                tarjetaPago.pack();
                tarjetaPago.setBounds(400,150,400,300);
                metodoDePago.tarjetaFrame.dispose();


            }
        });
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProcesoPagar.pagoframe.setVisible(true);
                metodoDePago.tarjetaFrame.dispose();
            }
        });
    }
}
