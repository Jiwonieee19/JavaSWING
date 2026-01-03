import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;

public class SpaceInvaders extends JPanel implements KeyListener {

    // a class that abstract the x y width and height of every component/img
    class Components {
        int x, y, width, height;
        Image img;
        boolean alive = true;
        boolean used = false;

        // a cleaner way to do is dw is to used inheritance instead of making a single
        // class for alien and bullets
        Components(Image img, int x, int y, int width, int height) {
            this.img = img;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;

        }
    }

    // board details
    private int columns = 20;
    private int rows = 16;
    private int pixel = 32;
    private int width = columns * pixel;
    private int height = rows * pixel;

    private Image alienImg;
    private Image cyanAlienImg;
    private Image magentaAlienImg;
    private Image yellowAlienImg;
    private Image shipImg;
    private ArrayList<Image> alienImgArr;

    // ship
    int shipWidth = pixel * 2;
    int shipHeight = pixel;
    int shipX = pixel * columns / 2 - pixel;
    int shipY = height - pixel * 2;
    Components ship;

    SpaceInvaders() {
        // setSize(width, height);
        setPreferredSize(new Dimension(width, height)); // this one will ignore the topbar of jframe
        setBackground(Color.BLUE);

        alienImg = new ImageIcon(getClass().getResource("./resources./alien.png")).getImage();
        cyanAlienImg = new ImageIcon(getClass().getResource("./resources./alien-cyan.png")).getImage();
        magentaAlienImg = new ImageIcon(getClass().getResource("./resources./alien-magenta.png")).getImage();
        yellowAlienImg = new ImageIcon(getClass().getResource("./resources./alien-yellow.png")).getImage();
        shipImg = new ImageIcon(getClass().getResource("./resources./ship.png")).getImage();

        alienImgArr = new ArrayList<Image>();
        alienImgArr.add(alienImg);
        alienImgArr.add(cyanAlienImg);
        alienImgArr.add(magentaAlienImg);
        alienImgArr.add(yellowAlienImg);

        ship = new Components(shipImg, shipX, shipY, shipWidth, shipHeight);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g); // calling the parents' paintcomponent which is siya itself
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(shipImg, shipX, shipY, shipWidth, shipHeight, null);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
