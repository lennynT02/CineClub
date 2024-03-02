import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class FechaHora {
    private JPanel Header;
    private JButton PerfBoton;
    private JLabel logoLabel;
    private JButton buscadorButton;
    private JButton menuButton;
    private JCalendar JCalendar1;  //necesaria libreria de jcalendar
    private JButton a1515Button;
    private JButton a1920Button;
    private JButton a1755Button;
    private JButton CONTINUARButton;
    private JButton REGRESARButton;
    public JPanel elegirHorario;
    private JLabel imagenLabel;
    private JLabel DetallesLabel;
    private void createUIComponents() {
        JCalendar1 = new JCalendar();
        Calendar fechaActual = Calendar.getInstance();
        JCalendar1.setMinSelectableDate(fechaActual.getTime());
    }

    public FechaHora(){
    ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource("imgPel.jpg"));
    Image img = icono.getImage();
    int maxAncho = 200;
    int maxAlto = 300;
        a1515Button.addActionListener(e -> {
            a1515Button.setSelected(true);
            a1920Button.setSelected(false);
            a1755Button.setSelected(false);
        });

        a1920Button.addActionListener(e -> {
            a1515Button.setSelected(false);
            a1920Button.setSelected(true);
            a1755Button.setSelected(false);
        });

        a1755Button.addActionListener(e -> {
            a1515Button.setSelected(false);
            a1920Button.setSelected(false);
            a1755Button.setSelected(true);
        });

    Image newImg = img.getScaledInstance(maxAncho, maxAlto, Image.SCALE_SMOOTH);
    ImageIcon iconoEscalado = new ImageIcon(newImg);
    imagenLabel.setIcon(iconoEscalado);
    imagenLabel.setSize(maxAncho, maxAlto);
        CONTINUARButton.addActionListener(e -> {
            //Validar que se ingresen datos
            Date fechaSeleccionada = JCalendar1.getDate();
            if (fechaSeleccionada == null) {
                JOptionPane.showMessageDialog(null, "Por favor, escoja una fecha.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!a1515Button.isSelected() && !a1920Button.isSelected() && !a1755Button.isSelected()) {
                //mensaje de errorr
                JOptionPane.showMessageDialog(null, "Por favor, escoja una hora.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

        });

    }
}
