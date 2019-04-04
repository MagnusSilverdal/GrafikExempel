import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

public class GrafikMedInteraktion extends Canvas{

    int x, y;
    int x1 = 400;
    int y1 = 300;
    double angle = 0;
    // Buffrad grafik för att få mindre flimmer. Funkar dåligt med repaint. Fixas med en egen tråd.
    BufferStrategy bs;
    int width = 800;
    int height = 600;
    Color color =new Color(0x00FFFF);

    public GrafikMedInteraktion() {
        setSize(width, height);
        JFrame frame = new JFrame("Grafik");
        frame.add(this);
        //frame.addKeyListener(new KL());
        frame.addMouseListener(new ML());
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
        g.setColor(color);
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
        GrafikMedInteraktion minGrafik = new GrafikMedInteraktion();
    }

    private class KL implements KeyListener {
        @Override
        public void keyTyped(KeyEvent keyEvent) {

        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            if (keyEvent.getKeyChar()=='a') {
                x1-=5;
            } else if (keyEvent.getKeyChar()=='d') {
                x1+=5;
            } else if (keyEvent.getKeyChar()=='w') {
                y1-=5;
            } else if (keyEvent.getKeyChar()=='s') {
                y1+=5;
            }
        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {

        }
    }

    private class ML extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            x1 = mouseEvent.getX();
            y1 = mouseEvent.getY();
            System.out.println(x1 + ", " + y1);
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

            x1 = mouseEvent.getX();
            y1 = mouseEvent.getY();
            System.out.println(x1 + ", " + y1);

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
            color = new Color(0xFF00FF);
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
            color = new Color(0x00FFFF);
        }
    }
}
