import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Efectivo {
    private JTextPane textoEfectivo;
    private JButton verDetallesDeLaButton;
    public JPanel efectivoPanel;
    private JButton regresarButton;

    public Efectivo() {
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProcesoPagar.pagoframe.setVisible(true);
                metodoDePago.efectivoFrame.dispose();
            }
        });
    }
}
