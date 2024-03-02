import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame paginaPrincipal = new JFrame("Página Principal");
        paginaPrincipal.setContentPane(new landingPage().landingPage);
        paginaPrincipal.setVisible(true);
        paginaPrincipal.pack();
        paginaPrincipal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        paginaPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH); // esto sirve para que ocupe toda la pantalla
    }




}