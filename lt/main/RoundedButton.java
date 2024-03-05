import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;

public class RoundedButton extends JButton {
    
    private static Shape shape;
    
    public Color backgroundColor, hoverColor, borderColor, textColor;
    private int borderSize;
    
    public RoundedButton(String text, Color backgroundColor, Color hoverColor, Color texColor, Color borderColor,
    int borderSize) {
            super(text);
            setBackground(backgroundColor);
            this.backgroundColor = backgroundColor;
            setForeground(texColor);
            this.borderColor = borderColor;
            this.borderSize = borderSize;
            setContentAreaFilled(false);
            setFocusPainted(false);
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    setBackground(hoverColor);
                    setForeground(Color.WHITE);
                    RoundedButton.this.borderColor = hoverColor;
                    repaint();
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    setBackground(backgroundColor);
                    setForeground(texColor);
                    RoundedButton.this.borderColor = borderColor;
                    repaint();
                }
            });
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(getBackground());
            g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1, this.borderSize, this.borderSize);
            super.paintComponent(g);
        }
        
        @Override
        protected void paintBorder(Graphics g) {
            g.setColor(borderColor);
            g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, this.borderSize, this.borderSize);
        }
        
        @Override
        public boolean contains(int x, int y) {
            if (shape == null || !shape.getBounds().equals(getBounds())) {
                shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            }
            return shape.contains(x, y);
        }
        public static void main(String[] args) {
            JFrame frame2 = new JFrame();
            frame2.setMinimumSize(new Dimension(800, 600));
            frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame2.setLocationRelativeTo(null);
            JPanel panel = new JPanel();
            RoundedButton boton = new RoundedButton("Mi botón", new Color(0,255,255), new Color(0, 0, 0),
                    new Color(0, 0, 0), new Color(0, 255, 255), 20);
            boton.setMargin(new Insets(10, 10, 10, 10));
            panel.add(boton);
            frame2.add(panel);
            frame2.pack();
            frame2.setVisible(true);
        }
    }