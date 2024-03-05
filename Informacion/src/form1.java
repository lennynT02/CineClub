import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class form1 {
    private JFrame frame;
    public JPanel menuPrincipal;
    private JButton informacionButton;
    private JTextArea descripcionTextArea;
    private JTextField informacionTextField;
    private JTextPane detalles;
    private JLabel reservacion;
    private JLabel calificacion;
    private JButton menuButton;
    private JButton buscadorButton;
    private JButton miPerfilButton;

    public form1() {

        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear y mostrar la nueva ventana al hacer clic en menuButton
                JFrame menuFrame = new JFrame("Menú");
                menuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                menuFrame.setSize(400, 300);
                menuFrame.setLocationRelativeTo(null); // Para centrar la ventana
                menuFrame.setVisible(true);
            }
        });
        buscadorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear y mostrar la nueva ventana al hacer clic en menuButton
                JFrame buscadorFrame = new JFrame("Buscador");
                buscadorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                buscadorFrame.setSize(400, 300);
                buscadorFrame.setLocationRelativeTo(null); // Para centrar la ventana
                buscadorFrame.setVisible(true);
            }
        });
        miPerfilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear y mostrar la nueva ventana al hacer clic en menuButton
                JFrame perfilFrame = new JFrame("Perfil");
                perfilFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                perfilFrame.setSize(400, 300);
                perfilFrame.setLocationRelativeTo(null); // Para centrar la ventana
                perfilFrame.setVisible(true);
            }
        });
    }
}


