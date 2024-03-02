import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Asientos {
    private JPanel Header;
    private JButton PerfBoton;
    private JLabel logoLabel;
    private JButton buscadorButton;
    private JButton menuButton;
    private JButton a1Button;
    private JButton a5Button;
    private JButton a3Button;
    private JButton a2Button;
    private JButton a4Button;
    private JButton b1Button;
    private JButton b2Button;
    private JButton b3Button;
    private JButton b4Button;
    private JButton b5Button;
    private JButton c1Button;
    private JButton c2Button;
    private JButton c3Button;
    private JButton c4Button;
    private JButton c5Button;
    private JButton d2Button;
    private JButton d1Button;
    private JButton d3Button;
    private JButton d4Button;
    private JButton d5Button;
    private JButton reservarButton;
    private JButton regresarButton;
    public JPanel reseAsientos;
    private JPanel asientoPanel;
    private List<JButton> asientos = new ArrayList<>();


    public Asientos() {
        asientos.add(a1Button);
        asientos.add(a2Button);
        asientos.add(a3Button);
        asientos.add(a4Button);
        asientos.add(a5Button);
        asientos.add(b1Button);
        asientos.add(b2Button);
        asientos.add(b3Button);
        asientos.add(b4Button);
        asientos.add(b5Button);
        asientos.add(c1Button);
        asientos.add(c2Button);
        asientos.add(c3Button);
        asientos.add(c4Button);
        asientos.add(c5Button);
        asientos.add(d1Button);
        asientos.add(d2Button);
        asientos.add(d3Button);
        asientos.add(d4Button);
        asientos.add(d5Button);
        JButton[][] asientosArray = {{a1Button, a2Button, a3Button, a4Button, a5Button},
                {b1Button, b2Button, b3Button, b4Button, b5Button},
                {c1Button, c2Button, c3Button, c4Button, c5Button},
                {d1Button, d2Button, d3Button, d4Button, d5Button}};

        for (JButton[] fila : asientosArray) {
            for (JButton asiento : fila) {
                asiento.addActionListener(e -> {
                    asiento.setSelected(!asiento.isSelected());
                });
                asientos.add(asiento);
            }
        }
        //Verificar que exista una seleccion en los asientos para continuar
        reservarButton.addActionListener(e -> {
            boolean verifSeleccionado = false;

            for (JButton asiento : asientos) {
                if (asiento.isSelected()) {
                    verifSeleccionado= true;
                    break;
                }
            }

            if (!verifSeleccionado) {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione al menos un asiento.", "Error", JOptionPane.ERROR_MESSAGE);
            }//Mesaje de error si no existee selección
        });
    }
}
