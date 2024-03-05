import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.filechooser.FileNameExtensionFilter;

public class PerfilAdmi {
    JPanel mainPanel = new JPanel();
    JScrollPane scroll = new JScrollPane(mainPanel);
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("Opciones");
    JMenuItem verInfoItem = new JMenuItem("Ver información");
    JMenuItem agregarPeliculaItem = new JMenuItem("Agregar película");

    public PerfilAdmi(Cliente cliente) {
        // Configura el menú
        menu.add(verInfoItem);
        menu.add(agregarPeliculaItem);
        menuBar.add(menu);

        // Configura el panel principal para usar el menú
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(menuBar, BorderLayout.NORTH);

        // Configura el scroll pane para usar el panel principal
        scroll.setViewportView(mainPanel);

        // Configura los listeners de los items del menú
        verInfoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Muestra la información del cliente
                JOptionPane.showMessageDialog(null, "Nombre: " + cliente.getNombre() + "\nApellido: "
                        + cliente.getApellido() + "\nCorreo: " + cliente.getCorreo());
            }
        });

        agregarPeliculaItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Muestra un formulario para agregar una película
                JTextField idField = new JTextField();
                JTextField nombreField = new JTextField();
                JTextField generoField = new JTextField();
                JTextField duracionField = new JTextField();
                JTextField descripcionField = new JTextField();
                JTextField fechaField = new JTextField();
                JTextField directorField = new JTextField();
                JTextField calificacionField = new JTextField();
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("PNG Images", "png"));

                Object[] message = {
                        "ID:", idField,
                        "Nombre:", nombreField,
                        "Género:", generoField,
                        "Duración:", duracionField,
                        "Descripción:", descripcionField,
                        "Fecha:", fechaField,
                        "Director:", directorField,
                        "Calificación:", calificacionField,
                        "Carátula:", fileChooser
                };

                int option = JOptionPane.showConfirmDialog(null, message, "Agregar película",
                        JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    try {

                        // Crea una conexión a la base de datos
                        Connection conn = DriverManager.getConnection(
                                "jdbc:sqlserver://localhost;databaseName=CINECLUB;user=sa;password=miespositaT02");

                        // Crea una sentencia SQL
                        String sql = "INSERT INTO PELICULA (ID_PEL, NOM_PEL, GEN_PEL, DUR_PEL, DES_PEL, FECHA_PEL, DIR_PEL, CARATULA, CALI_PEL) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement stmt = conn.prepareStatement(sql);

                        try {
                            // Obtiene la ruta del archivo seleccionado
                            File selectedFile = fileChooser.getSelectedFile();

                            // Define la ruta de destino en tu classpath
                            Path destPath = Paths.get(System.getProperty("user.dir"), "lt", "main", "resource","Login",
                                    selectedFile.getName());

                            // Copia el archivo
                            Files.copy(selectedFile.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);

                            // ...

                            // Usa la ruta de destino en lugar de la ruta original al guardar en la base de
                            // datos
                            stmt.setString(8, destPath.toString());

                            // ...
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                        // Establece los valores de los parámetros
                        stmt.setString(1, idField.getText());
                        stmt.setString(2, nombreField.getText());
                        stmt.setString(3, generoField.getText());
                        stmt.setInt(4, Integer.parseInt(duracionField.getText()));
                        stmt.setString(5, descripcionField.getText());
                        stmt.setDate(6, Date.valueOf(fechaField.getText()));
                        stmt.setString(7, directorField.getText());
                        stmt.setString(8, fileChooser.getSelectedFile().getPath());
                        stmt.setFloat(9, Float.parseFloat(calificacionField.getText()));

                        // Ejecuta la sentencia
                        stmt.executeUpdate();

                        // Cierra la conexión
                        conn.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
}