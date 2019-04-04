import javax.swing.*;
import java.awt.*;

/**
 * Ett enkelt exempel på hur grafik kan användas i Java. Genom att definiera om metoden paint(Graphics)
 * som används för att rita ut innehållet i ett fönster kan vi få java att rita som vi vill.
 */
public class GrafikV1 extends Canvas {

    public GrafikV1() {
        setSize(800,600);
        JFrame frame = new JFrame("Grafik version 1");
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void paint(Graphics g) {
        drawBox(100,100, g);
        drawBox(300,200,g);
        g.setColor(new Color(0x5500FFFF));
        g.fillOval(200,200,300,150);
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
