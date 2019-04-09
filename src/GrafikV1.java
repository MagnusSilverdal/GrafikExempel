import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Ett enkelt exempel på hur grafik kan användas i Java. Genom att definiera om metoden paint(Graphics)
 * som används för att rita ut innehållet i ett fönster kan vi få java att rita som vi vill.
 */
public class GrafikV1 extends Canvas {
    // variabel för att läsa in en bild
    BufferedImage img = null;

    public GrafikV1() {
        // Läs in bilden från en fil
        try {
            img = ImageIO.read(new File("supermario.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setSize(800,600);
        JFrame frame = new JFrame("Grafik version 1");
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Genom att sno metoden paint som redan finns och definiera om den kan vi rita på
     * @param g
     */
    public void paint(Graphics g) {
        // Välj en färg och rita en oval
        g.setColor(new Color(0x5500FFFF));
        g.fillOval(200,200,300,150);
        // Rita ut en bild
        g.drawImage(img,400,200,null);
        // en egen metod för att rita ut en viss form med en given färg och storlek på angiven plats
        drawBox(100,100, g);
        drawBox(300,200,g);
    }

    /**
     * Om vi vill rita ut samma objekt på flera platser är det enklast att skapa en metod för det
     * @param x objectets x-koordinat
     * @param y objektets y-koordinat
     * @param g referens till vart i minnet vi vill rita
     */
    private void drawBox(int x, int y, Graphics g) {
        g.setColor(new Color(0x55FF00FF));
        g.drawRect(x,y,300,200);
    }

    /**
     * Här startar programmet och en ny instans av grafiken startas
     * @param args
     */
    public static void main(String[] args) {
        GrafikV1 minGrafik = new GrafikV1();
    }
}
