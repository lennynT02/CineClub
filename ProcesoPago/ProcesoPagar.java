import javax.swing.*;

public class ProcesoPagar {
       static JFrame pagoframe = new JFrame("Método de Pago");
    public static void main(String[] args) {

        pagoframe.setContentPane(new metodoDePago().pagarPanel);
        pagoframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pagoframe.pack();
        pagoframe.setBounds(400,150,500,500);
        pagoframe.setVisible(true);


    }
}