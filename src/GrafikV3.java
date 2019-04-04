import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class GrafikV3 extends Canvas {
    int x, y;
    int x1 = 400;
    int y1 = 300;
    double angle = 0;
    // Buffrad grafik för att få mindre flimmer. Funkar dåligt med repaint. Fixas med en egen tråd.
    BufferStrategy bs;
    int width = 800;
    int height = 600;


    public GrafikV3() {
        setSize(width, height);
        JFrame frame = new JFrame("Grafik");
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void paint(Graphics g) {
        bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(2);
            return;
        }
        // Uppdatera koordinaterna
        update();
        // Rita ut den nya bilden
        draw(g);
        g.dispose();
        bs.show();
        // Ger en animation. När vi är klara körs paint igen
        repaint();
    }

    public void draw(Graphics g) {
        drawHouse(400, 400, g);
        drawHouse(460, 400, g);
        drawHouse(520, 400, g);
        drawHouse(x, y, g);
        g.setColor(new Color(0x00FF00));
        g.fillOval(x1, y1, 40, 40);
    }

    // Uppdatera x och y-koordinaterna. Ger en cirkel
    private void update() {
        x = 200 + (int) (100 * Math.cos(angle));
        y = 200 + (int) (100 * Math.sin(angle));
        angle += 2 * Math.PI / 720;
    }

    // Rita ett litet hus på koordinaterna (x,y)
    private void drawHouse(int x, int y, Graphics g) {
        g.setColor(new Color(0xAA1111));
        g.fillRect(x, y, 50, 50);
        g.setColor(new Color(0x444444));
        int[] xcoords = {x, x + 25, x + 50};
        int[] ycoords = {y, y - 50, y};
        g.fillPolygon(xcoords, ycoords, 3);
    }

    public static void main(String[] args) {
        GrafikV3 minGrafik = new GrafikV3();
    }

}
