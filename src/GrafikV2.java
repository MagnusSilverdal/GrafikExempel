import javax.swing.*;
import java.awt.*;

/**
 * Exempel på en enkel animation. Med repaint() körs paint om och om igen. För att saker ska röra sig så uppdateras
 * några variabler i varje varv. (x och y)
 */
public class GrafikV2 extends Canvas {
    int x, y;
    int x1 = 400;
    int y1 = 300;
    double angle = 0;
    // Buffrad grafik för att få mindre flimmer. Funkar dåligt, kanske en uppdatering?
    Image image;
    Graphics dbg;
    int width = 800;
    int height = 600;


    public GrafikV2() {
        setSize(width, height);
        JFrame frame = new JFrame("Grafik version 2");
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void paint(Graphics g) {
        if (image == null) {
            // skapa en andra skärm i minnet som vi kan rita till
            image = createImage(width, height);
            if (image == null) {
                System.out.println("image is still null!");
                return;
            } else {
                dbg = image.getGraphics();
            }
        }

        // Uppdatera koordinaterna
        update();
        // Rita ut den nya bilden
        draw(dbg);
        g.drawImage(image, 0, 0, null);
        // Borde inte behövas...
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Ger en animation. När vi är klara körs paint igen
        repaint();
    }

    public void draw(Graphics g) {
        dbg.setColor(Color.WHITE);
        dbg.fillRect(0, 0, width, height);
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
        GrafikV2 minGrafik = new GrafikV2();
    }
}