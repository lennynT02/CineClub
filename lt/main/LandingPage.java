import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import org.kordamp.ikonli.fontawesome5.FontAwesomeRegular;
import org.kordamp.ikonli.swing.FontIcon;

public class LandingPage {

    // Panel principal
    JPanel mainPanel = new JPanel(new BorderLayout());

    // Barra de navegación
    JPanel barraPanel = new JPanel(new GridBagLayout());
    GridBagConstraints barraGbc = new GridBagConstraints();

    //Logo
    imagen logo = new imagen("LandingPage/cine.png");
    JLabel logoLabel = new JLabel(logo.redimencionar(60, 60));
    JLabel cine = new JLabel("CINE CLUB");

    //Perfil

    JLabel perfilLabel = new JLabel(FontIcon.of(FontAwesomeRegular.USER, 18, Color.decode("#c3c0c1")));


    // Panel dinámico
    static JPanel dinamicPanel = new JPanel();

    public LandingPage(Cliente cliente) {

        // Barra de navegación
        barraPanel.setBackground(new Color(0, 0, 0));
        barraPanel.setPreferredSize(new Dimension((int)Login.screenSize.getWidth(), 80));

        // Logo
        barraGbc.gridx = 0;
        barraGbc.gridy = 0;
        barraGbc.weightx = 1;
        barraGbc.anchor = GridBagConstraints.WEST;
        barraGbc.insets = new Insets(0, 30, 0, 0);
        barraPanel.add(logoLabel, barraGbc);

        cine.setFont(new Font("Arial", Font.BOLD, 20));
        cine.setForeground(Color.WHITE);
        barraGbc.insets = new Insets(0, 100, 0, 0);
        barraPanel.add(cine, barraGbc);
        
        // Perfil
        barraGbc.gridx = 1;
        barraGbc.gridy = 0;
        barraGbc.anchor = GridBagConstraints.EAST;
        barraGbc.insets = new Insets(0, 0, 0, 30);
        barraPanel.add(perfilLabel, barraGbc);

        mainPanel.add(barraPanel, BorderLayout.NORTH);
        
        dinamicPanel.setBackground(new Color(0, 0, 0));
        dinamicPanel.setPreferredSize(new Dimension(((int)Login.screenSize.getWidth()),  ((int)Login.screenSize.getHeight())-80));
        mainPanel.add(dinamicPanel, BorderLayout.CENTER);

        logoLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                mostrarPanel(new Peliculas().scroll);
            }
        });

        perfilLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                if (cliente.isAdmin()) {
                    // Muestra el panel del administrador
                    mostrarPanel(new PerfilAdmi(cliente).scroll);
                } else {
                    // Muestra el panel del cliente
                    mostrarPanel(new Perfil(cliente).scroll);
                }
            }
        });
    }

    public static void mostrarPanel(JScrollPane p){
        p.setPreferredSize(new Dimension(((int)Login.screenSize.getWidth()),  ((int)Login.screenSize.getHeight())-80));
        dinamicPanel.removeAll();
        dinamicPanel.add(p);
        dinamicPanel.revalidate();
        dinamicPanel.repaint();
    }

    public static void peliInformation(String id){
        LandingPage.mostrarPanel(new Informacion(id).scroll);
    }
}
