import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumeroEntradas {
    private JPanel Header;
    private JButton PerfBoton;
    private JLabel logoLabel;
    private JButton buscadorButton;
    private JButton menuButton;
    public JPanel TotalEntradas;
    private JSlider slider1;
    private JLabel fuLabel;
    private JLabel CosLabel;
    private JButton IRAPAGARButton;
    private JButton REGRESARButton;
    private JLabel TotalLabel;
    private JTextField informacionDePeliculaTextField;
    private JLabel ImagenLabel;
    private JPanel numero;
    private JSeparator Separador;
    private JLabel numEntra;
    private int valorSeleccionado;
    private double total;
    public NumeroEntradas() {

        slider1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                valorSeleccionado = slider1.getValue();
                 total = 7.20 * valorSeleccionado;
                TotalLabel.setText("Total: $" + total);
                numEntra.setText(Integer.toString(valorSeleccionado));
            }
        });
        IRAPAGARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (valorSeleccionado != 0) {  //Obtener el valor total al salir y cantidad de entradas
                    double totalCine=getTotalPagar();
                    int cantFinal=getValorSeleccionado();
                } else { //Si no se escoge valor
                    JOptionPane.showMessageDialog(null, "Por favor seleccione su número de entradas", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    public double getTotalPagar() {
        return total;
    }
    public int getValorSeleccionado() {
        return valorSeleccionado;
    }

}
