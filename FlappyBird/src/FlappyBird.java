import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
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
    private int obsWidth = pixel + 10;
    private int obsHeight = pixel * 3;
    private int obsX = width / 2;

    Random random;
    Blocks bird;
    LinkedList<Blocks> obstacles;

    Timer gameLoop;

    FlappyBird() {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        birdImg = new ImageIcon(getClass().getResource("./resources./bird.png")).getImage();
        bird = new Blocks(birdImg, birdX, birdY, pixel, pixel);
        gameStart = false;
        loadObstacles();
        gameLoop = new Timer(100, this);
        gameLoop.start();
    }

    public void loadObstacles() {

        obstacles = new LinkedList<Blocks>();
        random = new Random();

        int randomizeY = random.nextInt(height - (obsHeight * 3)) + obsHeight; // around 96 to height-96 ang bounds

        Blocks First = new Blocks(null, obsX, randomizeY, obsWidth, obsHeight);
        Blocks Second = new Blocks(null, obsX + (obsWidth * 5), random.nextInt(height - (obsHeight * 3)) + obsHeight,
                obsWidth, obsHeight);

        obstacles.add(First);
        obstacles.add(Second);
    }

    public void move() {

        if (fallSpeed > 18) {
            fallSpeed = 18;
        }

        if (gameStart) {
            bird.y += fallSpeed;
            System.out.println("Free Fall Speed: " + fallSpeed);

            for (int i = 0; i < obstacles.size(); i++) {
                obstacles.get(i).x -= 6; // modagan tanan obstacle pa left

                if (obstacles.get(i).x < -(obsWidth * 6)) { // rather than < 0, this one hides the stutter/glitch on
                                                            // becoming the next 0 index of the linkedlist
                    obstacles.remove(i); // e remove obstacle if nalapas sa left frame
                }
            }
            System.out.println("Size ni linekdlist: " + obstacles.size());
        }

        fallSpeed += 2;
    }

    public void loopObstacle() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {

        // obstacle
        for (int i = 0; i < obstacles.size(); i++) {
            Blocks obs = obstacles.get(i);
            g.fillRect(obs.x, obs.y, obs.width, obs.height);
        }

        // bird
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
            bird.y -= 24;
            fallSpeed = 1;
            System.out.println("NI UP");
        }

        random = new Random();
        int randomizeY = random.nextInt(height - (obsHeight * 3)) + obsHeight; // around 96 to height-96 ang bounds

        Blocks obsLast = obstacles.getLast();

        Blocks obs = new Blocks(null, obsLast.x + (obsWidth * 5), randomizeY, obsWidth, obsHeight);
        obstacles.add(obs);

        gameStart = true;
    }
}
