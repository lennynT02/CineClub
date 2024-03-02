import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    private JButton PerfBoton;
    private JPanel Header;
    public JPanel Perfil;
    private JPanel Contenido;
    private JButton historialDeCompraButton;
    private JButton editarPerfilButton;
    private JLabel logoLabel;
    private JButton buscadorButton;
    private JButton menuButton;


    public Menu() {
        CardLayout cardLayout = new CardLayout();
        Contenido.setLayout(cardLayout);
        JPanel panelDefault = new JPanel();
        panelDefault.setBackground(Color.BLACK);
        Contenido.add(panelDefault, "Default");
        cardLayout.show(Contenido, "Default");

        editarPerfilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPerfil();
            }

        });
        historialDeCompraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarHistorial();
            }
        });
    }

        private void mostrarPerfil() {
            EdPerfil edperfil = new EdPerfil();
            Contenido.add(edperfil.getDato(), "Perfil");
            CardLayout cardLayout = (CardLayout) Contenido.getLayout();
            cardLayout.show(Contenido, "Perfil");
        }

    private void mostrarHistorial() {
        Historial historial = new Historial();
        Contenido.add(historial.getHistRes(), "Historial");
        CardLayout cardLayout = (CardLayout) Contenido.getLayout();
        cardLayout.show(Contenido, "Historial");
    }
}
