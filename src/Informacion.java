import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;

public class Informacion {
    private JPanel Header;
    private JButton PerfBoton;
    private JLabel logoLabel;
    private JButton buscadorButton;
    private JButton menuButton;
    private JTextField GenerotextField;
    private JTextField DuracionTextField;
    private JTextField FechaEstrenoTextField;
    private JTextField DirectorTextField;
    private JTextArea areaDescripcion;
    private JLabel ImagenLabel;
    private JTextField tituloPeliculaField;
    private JLabel GENEROLabel;
    private JLabel DuracionLabel;
    private JLabel FechaEstrenoLabel;
    private JLabel DirectorLabel;
    private JLabel DescripcionField;
    public JPanel peliculasInfo;
    private JButton informaciónDeLaReservaciónButton;
    private JTextArea TitulotextArea;
    private JProgressBar progressBar1;

    public Informacion(){
        GenerotextField.setEditable(false); //solo lectura
        DuracionTextField.setEditable(false);
        FechaEstrenoTextField.setEditable(false);
        DirectorTextField.setEditable(false);
        TitulotextArea.setEditable(false);
        areaDescripcion.setEditable(false);
        //Prueba de cargar imagenes
        ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource("imgPel.jpg"));
        Image img = icono.getImage();
        int maxAncho = 200;
        int maxAlto = 300;

        Image newImg = img.getScaledInstance(maxAncho, maxAlto, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(newImg);
        ImagenLabel.setIcon(iconoEscalado);
        ImagenLabel.setSize(maxAncho, maxAlto);

        int calificacion=2; //Probando funcionamiento ProgressBar
        progressBar1.setMinimum(0);
        progressBar1.setMaximum(5);
        progressBar1.setValue(calificacion);
        progressBar1.setForeground(Color.GREEN);



    }
}
