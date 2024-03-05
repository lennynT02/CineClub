import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame=new JFrame("CINE CLUB ");
        frame.setContentPane(new form1().menuPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640,480);
        frame.pack();
        frame.setVisible(true);
    }
}