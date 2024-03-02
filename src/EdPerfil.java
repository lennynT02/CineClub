import javax.swing.*;
import java.awt.*;

public class EdPerfil {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JPasswordField passwordField1;
    private JLabel Nombre;
    public JPanel Dato;
    private JLabel Apellido;
    private JLabel Cont;
    private JLabel Cor;
    public JPanel getDato() {
        Dato.setPreferredSize(new Dimension(500, 300));
        return Dato;
    }
}
