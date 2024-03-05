import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class imagen { 
    String path;
    public imagen(String path){
        this.path = path;
    }
    public String getPath(){
        return this.path;
    }
    public void setPath(String path){
        this.path = path;
    }
    public BufferedImage recortePersonalizado(){
        BufferedImage result = null;
        try {
            // Cargar las imágenes
            BufferedImage img = ImageIO.read(imagen.class.getResourceAsStream(this.path));
            BufferedImage img2 = ImageIO.read(imagen.class.getResourceAsStream("marco.png"));
            BufferedImage mask = ImageIO.read(imagen.class.getResourceAsStream("mask.png"));

            // Escalar las imágenes
            Image escala = img.getScaledInstance(142, 210, Image.SCALE_SMOOTH);            
            Image escala2 = img2.getScaledInstance(142, 210, Image.SCALE_SMOOTH);

            // Crear la imagen resultante de las dimensiones
            BufferedImage imagen = new BufferedImage(142, 210, BufferedImage.TYPE_INT_ARGB);
            Graphics2D gr2d = imagen.createGraphics();
            gr2d.drawImage(escala, 0, 0, null);
            gr2d.dispose();

            BufferedImage marco = new BufferedImage(142, 210, BufferedImage.TYPE_INT_ARGB);
            Graphics2D gr2d2 = marco.createGraphics();
            gr2d2.drawImage(escala2, 0, 0, null);
            gr2d2.dispose();

            // Crear la imagen resultante del corte segun la máscara
            BufferedImage corte = new BufferedImage(mask.getWidth(), mask.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = corte.createGraphics();
            g2d.drawImage(mask, 0, 0, null);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP));
            g2d.drawImage(imagen, 0, 0, null);
            g2d.dispose();
            
            result = new BufferedImage(mask.getWidth(), mask.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d2 = result.createGraphics();
            g2d2.drawImage(corte, 0, 0, null);
            g2d2.drawImage(marco,0,0, null);
            g2d2.dispose();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ImageIcon redimencionar(int width, int height){
        BufferedImage result = null;
        try {
            // Cargar la imagen
            BufferedImage img = ImageIO.read(imagen.class.getResourceAsStream(this.path));

            // Escalar la imagen
            Image escala = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            // Crear la imagen resultante de las dimensiones
            result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D gr2d = result.createGraphics();
            gr2d.drawImage(escala, 0, 0, null);
            gr2d.dispose();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result == null ? null : new ImageIcon(result);
    }
}