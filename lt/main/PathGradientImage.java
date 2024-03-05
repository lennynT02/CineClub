
import java.awt.*;

import javax.swing.*;

import org.kordamp.ikonli.fontawesome5.FontAwesomeRegular;
import org.jdesktop.swingx.JXTextField;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.swing.FontIcon;

public class PathGradientImage {

    public static JPanel CustomPanel(int arc) {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                if (!isOpaque()) {
                    super.paintComponent(g);
                    return;
                }

                int width = getWidth();
                int height = getHeight();
                Graphics2D graphics = (Graphics2D) g;

                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                graphics.setColor(getBackground());
                graphics.fillRoundRect(0, 0, width, height, 20, 20);

                setOpaque(false);
                super.paintComponent(g);
                setOpaque(true);
            }
        };
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(200, 400));

        JPanel panel2 = CustomPanel(20);
        panel2.setBackground(Color.decode("#f7f3f4"));
        panel2.setPreferredSize(new Dimension(200, 30));
        panel2.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel email = new JLabel(FontIcon.of(FontAwesomeSolid.LOCK, 20, Color.decode("#c3c0c1")));

        JXTextField emailField = new JXTextField();
        emailField.setPrompt("Email");

        emailField.setPreferredSize(new Dimension(150, 30));
        emailField.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        emailField.setBackground(Color.decode("#f7f3f4"));
        emailField.setForeground(Color.decode("#c3c0c1"));
        emailField.setFont(new Font("Arial", Font.PLAIN, 18));
        
        JButton button = new JButton("Login");
        button.setPreferredSize(new Dimension(150, 30));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 5);
        gbc.anchor = GridBagConstraints.CENTER;
        panel2.add(email, gbc);

        gbc.gridx = 1;
        panel2.add(emailField, gbc);

        panel.add(panel2);
        panel.add(button);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        button.requestFocusInWindow();
    }
}