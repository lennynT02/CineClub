import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

public class Peliculas {

    public static String usuario = "";

    JPanel mainPanel = new JPanel(new GridBagLayout());
    GridBagConstraints mainGbc = new GridBagConstraints();
    JScrollPane scroll = new JScrollPane(mainPanel);

    JLabel bienvenida = new JLabel("Bienvenido " + usuario);
    JLabel principal = new JLabel(new imagen("Kpa4.png").redimencionar((int) (Login.screenSize.getWidth()) - 100, 500));
    JPanel principalPanel = Login.CustomPanel(15);

    JButton perfil = new JButton("Perfil");

    JPanel peliculas = new JPanel();

    public Peliculas() {
        mainPanel.setBackground(Color.BLACK);
        scroll.setBorder(null);

        // Bienvenida
        bienvenida.setFont(new Font("Arial", Font.BOLD, 20));
        bienvenida.setForeground(Color.WHITE);
        mainGbc.gridx = 0;
        mainGbc.gridy = 0;
        mainGbc.weightx = 1;
        mainGbc.anchor = GridBagConstraints.WEST;
        mainGbc.insets = new Insets(0, 30, 30, 0);
        mainPanel.add(bienvenida, mainGbc);

        // Pelicula principal
        principalPanel.add(principal);
        mainGbc.gridx = 0;
        mainGbc.gridy = 1;
        mainGbc.weightx = 1;
        mainGbc.anchor = GridBagConstraints.CENTER;
        mainGbc.insets = new Insets(0, 0, 50, 0);
        mainPanel.add(principalPanel, mainGbc);

        // Peliculas
        ResultSet rs;
        try {
            Connection con = Login.con;
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM PELICULA";
            rs = stmt.executeQuery(query);
            peliculas.setLayout(new BoxLayout(peliculas, BoxLayout.Y_AXIS));
            peliculas.setBackground(Color.BLACK);
            for (int i = 0; i < 8; i++) {
                if (i % 4 == 0) {
                    JPanel panel = new JPanel(new GridBagLayout());
                    panel.setBackground(Color.BLACK);
                    peliculas.add(panel);
                }

                GridBagConstraints gbc = new GridBagConstraints();
                JPanel componente = new JPanel(new GridBagLayout());
                GridBagConstraints gbc2 = new GridBagConstraints();
                componente.setPreferredSize(new Dimension(420, 552));
                componente.setBackground(Color.BLACK);
                if (rs.next()) {
                    String path = rs.getString("CARATULA");
                    JLabel imagen = new JLabel(new imagen(path).redimencionar(400, 532));
                    JLabel titulo = new JLabel(rs.getString("NOM_PEL"));
                    final String idPel = rs.getString("ID_PEL");

                    gbc2.gridx = 0;
                    gbc2.gridy = 0;
                    gbc2.weightx = 1;
                    gbc2.anchor = GridBagConstraints.CENTER;
                    gbc2.insets = new Insets(0, 0, 10, 0);
                    componente.add(imagen, gbc2);

                    titulo.setFont(new Font("Arial", Font.BOLD, 18));
                    titulo.setForeground(Color.WHITE);
                    gbc2.gridy = 1;
                    gbc2.insets = new Insets(0, 0, 30, 0);
                    componente.add(titulo, gbc2);

                    // Agrega un ActionListener al componente
                    componente.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            LandingPage.peliInformation(idPel);
                        }
                    });
                    gbc.gridx = i % 4;
                    gbc.gridy = 0;
                    gbc.insets = new Insets(5, 5, 5, 5);
                    ((JPanel) peliculas.getComponent(peliculas.getComponentCount() - 1)).add(componente, gbc);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mainGbc.gridx = 0;
        mainGbc.gridy = 2;
        mainGbc.weightx = 1;
        mainGbc.anchor = GridBagConstraints.CENTER;
        mainGbc.insets = new Insets(0, 0, 0, 0);
        mainPanel.add(peliculas, mainGbc);

        mainGbc.gridx = 0;
        mainGbc.gridy = 3;
        mainGbc.weightx = 1;
        mainGbc.anchor = GridBagConstraints.CENTER;
        mainGbc.insets = new Insets(50, 0, 50, 0);
        mainPanel.add(perfil, mainGbc);

    }
}
