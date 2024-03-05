import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Informacion {

    String peliculaID;

    JPanel mainPanel = new JPanel(new GridBagLayout());
    GridBagConstraints mainGbc = new GridBagConstraints();

    JScrollPane scroll = new JScrollPane(mainPanel);

    JLabel imagen = new JLabel();

    JTextPane descripcionCom = new JTextPane();
    JTextPane descripcion = new JTextPane();

    RoundedButton reservar = new RoundedButton("RESERVAR",  Color.decode("#732626"), Color.decode("#d99748"),
    Color.decode("#ffffff"), Color.decode("#732626"), 35);

    public Informacion(String peliculaID) {
        this.peliculaID = peliculaID;
        // Panel principal
        mainPanel.setBackground(Color.BLACK);
        scroll.setBorder(null);
        try {
            Connection con = Login.con;
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM PELICULA WHERE ID_PEL = '" + peliculaID + "'";
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            // Imagen
            imagen = new JLabel(new imagen(rs.getString("CARATULA")).redimencionar(600, 700));
            mainGbc.gridx = 0;
            mainGbc.gridy = 0;
            mainGbc.weightx = 1;
            mainGbc.anchor = GridBagConstraints.CENTER;
            mainGbc.insets = new Insets(0, 0, 30, 0);
            mainPanel.add(imagen, mainGbc);

            // Descripion completa
            descripcionCom.setText("\t" + rs.getString("NOM_PEL") + "\n\n" + "GENERO: " + rs.getString("GEN_PEL") + "\n"
                    + "DURACION: " + rs.getString("DUR_PEL") + "\n" + "FECHA ESTRENO: " + rs.getString("FECHA_PEL")
                    + "\n" + "DIRECTOR: " + rs.getString("DIR_PEL") + "\n"
                    + "CALIFICACION: " + rs.getString("CALI_PEL"));
            descripcionCom.setFont(new Font("Arial", Font.PLAIN, 40));
            descripcionCom.setEditable(false);
            descripcionCom.setBackground(null);
            descripcionCom.setForeground(Color.WHITE);
            descripcionCom.setBorder(null);
            descripcionCom.setCaret(null);

            mainGbc.gridx = 1;
            mainGbc.gridy = 0;
            mainGbc.weightx = 1;
            mainGbc.anchor = GridBagConstraints.CENTER;
            mainGbc.insets = new Insets(0, 0, 30, 0);
            mainPanel.add(descripcionCom, mainGbc);

            // Descripion
            descripcion.setText(rs.getString("DES_PEL"));
            descripcion.setPreferredSize(new Dimension( (int) (Login.screenSize.getWidth()) - 100, 200));
            descripcion.setFont(new Font("Arial", Font.PLAIN, 30));
            descripcion.setEditable(false);
            descripcion.setBackground(null);
            descripcion.setForeground(Color.WHITE);
            descripcion.setBorder(null);
            descripcion.setCaret(null);
            
            mainGbc.gridx = 0;
            mainGbc.gridy = 1;
            mainGbc.gridwidth = 2;
            mainGbc.weightx = 1;
            mainGbc.anchor = GridBagConstraints.CENTER;
            mainGbc.insets = new Insets(0, 0, 30, 0);
            mainPanel.add(descripcion, mainGbc);

            // Reservar
            reservar.setPreferredSize(new Dimension( 300, 70));
            reservar.setFont(new Font("Arial", Font.BOLD, 30));
            mainGbc.gridx = 0;
            mainGbc.gridy = 2;
            mainGbc.gridwidth = 2;
            mainGbc.weightx = 1;
            mainGbc.anchor = GridBagConstraints.CENTER;
            mainGbc.insets = new Insets(0, 0, 30, 0);
            mainPanel.add(reservar, mainGbc);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //
        reservar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame reservar = new JFrame();
                reservar.setMinimumSize(new Dimension(800, 600));
                reservar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                reservar.setContentPane(new Reservar(imagen).mainPanel);
                reservar.setLocationRelativeTo(null);
                reservar.setExtendedState(JFrame.MAXIMIZED_BOTH);
                reservar.setUndecorated(false);
                reservar.pack();
                reservar.setVisible(true);
            }
        });
    }
}
