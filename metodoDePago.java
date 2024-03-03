import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class metodoDePago {

    static JFrame efectivoFrame = new JFrame("Efectivo");
    static JFrame tarjetaFrame = new JFrame("Tarjeta");
    private JRadioButton efectivoRadioButton;
    private JRadioButton tarjetaRadioButton;
    private JButton pagarButton;
    private JButton regresarButton;
    public JPanel pagarPanel;


    public metodoDePago() {
        pagarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (efectivoRadioButton.isSelected()){
                    efectivoFrame.setContentPane(new Efectivo().efectivoPanel);
                    efectivoFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    efectivoFrame.setVisible(true);
                    efectivoFrame.pack();
                    efectivoFrame.setBounds(400,150,500,300);
                    ProcesoPagar.pagoframe.dispose();
                }else if (tarjetaRadioButton.isSelected()){
                    tarjetaFrame.setContentPane(new Tarjeta().tarjetaPanel);
                    tarjetaFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    tarjetaFrame.setVisible(true);
                    tarjetaFrame.pack();
                    tarjetaFrame.setBounds(400,150,500,300);
                    ProcesoPagar.pagoframe.dispose();


                }

            }
        });
    }
}
