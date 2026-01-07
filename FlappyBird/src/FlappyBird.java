import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {

    class Blocks {
        int x;
        int y;
        int width;
        int height;
        Image img;

        Blocks(Image img, int x, int y, int width, int height) {
            this.img = img;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }

    private int width = 300;
    private int height = 450;
    private int pixel = 20;
    private int birdX = width / 7;
    private int birdY = height / 2 - pixel;
    private Image birdImg;
    private boolean gameStart;
    private int fallSpeed;

    Blocks bird;

    Timer gameLoop;

    FlappyBird() {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        birdImg = new ImageIcon(getClass().getResource("./resources./bird.png")).getImage();
        bird = new Blocks(birdImg, birdX, birdY, pixel, pixel);
        gameStart = false;
        // laodMap();
        gameLoop = new Timer(100, this);
        gameLoop.start();
    }

    public void laodMap() {
    }

    public void move() {

        if (fallSpeed > 10) {
            fallSpeed = 10;
        }

        if (gameStart) {
            bird.y += fallSpeed;
            System.out.println(fallSpeed);
        }

        fallSpeed++;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            bird.y -= 35;
            fallSpeed = 1;
            System.out.println("NAABOT DIRI");
        }

        gameStart = true;
    }
}
