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
    private Image birdUpImg;
    private Image birdDownImg;
    private boolean gameStart;
    private int fallSpeed;
    private int obsWidth = pixel + 10;
    private int obsHeight = pixel * 3;
    private int obsX = width / 2;
    private int score = 0;
    private boolean gameOver = false;

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
        birdUpImg = new ImageIcon(getClass().getResource("./resources./birdUp.png")).getImage();
        birdDownImg = new ImageIcon(getClass().getResource("./resources./birdDown.png")).getImage();

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

        if (bird.y > height - pixel) {
            gameOver();
        }

        if (fallSpeed > 3 && gameStart) {
            bird.img = birdDownImg;
        }

        if (fallSpeed > 18) {
            fallSpeed = 18;
        }

        if (gameStart) {
            bird.y += fallSpeed;
            System.out.println("Free Fall Speed: " + fallSpeed);

            for (int i = 0; i < obstacles.size(); i++) {
                obstacles.get(i).x -= 6; // modagan tanan obstacle pa left

                if (Collision(bird, obstacles.get(i))) {
                    System.out.println("+ POINTS");
                    score++;
                } else if (WrongCollision(bird, obstacles.get(i))) {
                    gameOver();
                }

                if (obstacles.get(i).x < -(obsWidth * 6)) { // rather than < 0, this one hides the stutter/glitch on
                                                            // becoming the next 0 index of the linkedlist
                    obstacles.remove(i); // e remove obstacle if nalapas sa left frame
                }
            }
            System.out.println("Size ni linekdlist: " + obstacles.size());
        }

        fallSpeed += 2;
    }

    public boolean Collision(Blocks a, Blocks b) {

        if (a.x < b.x + b.width &&
                a.x + a.width > b.x &&
                a.y < b.y + b.height &&
                a.y + a.height > b.y) {
            return true;
        }

        return false;
    }

    public boolean WrongCollision(Blocks a, Blocks b) {

        if (a.x < b.x + b.width &&
                a.x + a.width > b.x) {
            return true;
        }

        return false;
    }

    public void gameOver() {
        gameOver = true;
        gameLoop.stop();
        System.out.println("GAME OVER");
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {

        // obstacle
        for (int i = 0; i < obstacles.size(); i++) {
            Blocks obs = obstacles.get(i);

            // wallS
            g.setColor(Color.GREEN);
            g.fillRect(obs.x, 0, obs.width, height);

            g.setColor(Color.BLACK);
            g.fillRect(obs.x, obs.y - pixel, obs.width, obs.height + (pixel * 2));
            // pixel here act as margin since the
            // collision for gameover is basing on the
            // bird area (widh and height) as a whole,
            // if it touches only the half, it wont be
            // a gameover
        }

        // bird
        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);

        // score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Roboto", Font.BOLD, 15));
        if (gameOver) {
            g.drawString("GAME OVER! TOTAL SCORE : " + score, 10, 20);
            g.drawString("PRESS ANY KEY TO RESTART", 30, height / 2);
        } else {
            g.drawString("SCORE : " + score, 10, 20);
        }
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

        if (gameOver) {
            gameOver = false;
            score = 0;
            fallSpeed = 0;
            bird.x = birdX;
            bird.y = birdY;
            bird.img = birdImg;
            loadObstacles();
            gameLoop.start();
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            bird.y -= 24;
            fallSpeed = 1;
            bird.img = birdUpImg;
            System.out.println("NI UP");
        }

        // create new obstacle every move, rather than every frame coz its much more
        // obstacle if we put in actionperformed
        random = new Random();
        int randomizeY = random.nextInt(height - (obsHeight * 3)) + obsHeight; // around 96 to height-96 ang bounds

        Blocks obsLast = obstacles.getLast();

        Blocks obs = new Blocks(null, obsLast.x + (obsWidth * 5), randomizeY, obsWidth, obsHeight);
        obstacles.add(obs);

        gameStart = true;
    }
}

// COOL LOGIC IMPLEMENTION ON COLLISION BY THIS SHITTY BRAIN, AND IF WRONG
// TARGET (WRONG HOLE), THEN GAME OVER HAHHAHA
