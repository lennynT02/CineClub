import java.awt.*;
import javax.swing.*;

public class Perfil {

    JPanel mainPanel = new JPanel();
    JScrollPane scroll = new JScrollPane(mainPanel);

    // Datos del usuario
    JLabel lblNombre = new JLabel("Nombre: ");
    JLabel lblApellido = new JLabel("Apellido: ");
    JLabel lblCorreo = new JLabel("Correo: ");

    

    public Perfil(Cliente cliente) {
        mainPanel.setLayout(new GridLayout(3, 1)); // Configura el layout del panel principal
        mainPanel.setBackground(Color.BLACK); // Configura el color de fondo del panel principal
        // Agrega las etiquetas al panel principal

        lblNombre.setFont(new Font("Arial", Font.BOLD, 20));
        lblNombre.setForeground(Color.WHITE);
        lblApellido.setFont(new Font("Arial", Font.BOLD, 20));
        lblApellido.setForeground(Color.WHITE);
        lblCorreo.setFont(new Font("Arial", Font.BOLD, 20));
        lblCorreo.setForeground(Color.WHITE);
        

        mainPanel.add(lblNombre);
        mainPanel.add(lblApellido);
        mainPanel.add(lblCorreo);

        // Configura el scroll pane para usar el panel principal
        scroll.setViewportView(mainPanel);

        // Usa los datos del objeto Cliente para actualizar las etiquetas
        lblNombre.setText("Nombre: " + cliente.getNombre());
        lblApellido.setText("Apellido: " + cliente.getApellido());
        lblCorreo.setText("Correo: " + cliente.getCorreo());
    }
}