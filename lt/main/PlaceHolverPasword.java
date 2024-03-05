
import java.awt.*;

import javax.swing.*;

public class PlaceHolverPasword extends JPasswordField {
    private String placeholder;

    public PlaceHolverPasword(String placeholder) {
        this.placeholder = placeholder;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (this.placeholder == null || this.placeholder.length() == 0 || getPassword().length > 0) {
            return;
        }
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.decode("#c2e0f1"));
        FontMetrics fm = g2.getFontMetrics();
        int x = getInsets().left;
        int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
        g2.drawString(placeholder, x, y);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(200, 400));

        PlaceHolverPasword password = new PlaceHolverPasword("Password");
        password.setPreferredSize(new Dimension(150, 30));
        password.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        password.setFont(new Font("Arial", Font.PLAIN, 18));
        password.setBackground(Color.decode("#f7f3f4"));

        panel.add(password);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

    }
}
