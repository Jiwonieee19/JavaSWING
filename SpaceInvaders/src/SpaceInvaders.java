import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class SpaceInvaders extends JPanel implements ActionListener, KeyListener {

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

    // alien
    ArrayList<Components> aliensArr;
    int alienWidth = pixel * 2;
    int alienHeight = pixel;
    int alienX = pixel * 1;
    int alienY = pixel * 1;
    int alienVelocityX = 2; // naay bug, dli halata if 1 lang iyang movement, so wla nako gi fix pra maak
                            // proceed sa other game
    boolean touchs = false;

    int alienRows = 2;
    int alienColumns = 3;
    int alienCount = 0;

    // bullets
    ArrayList<Components> bulletsArr;
    int bulletWidth = pixel / 8;
    int bulletHeight = pixel / 2;
    int bulletVelocityY = -10;

    int score = 0;
    boolean gameOver = false;
    Timer gameLoop;
    Random random;

    SpaceInvaders() {
        // setSize(width, height);
        setPreferredSize(new Dimension(width, height)); // this one will ignore the topbar of jframe
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

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
        aliensArr = new ArrayList<Components>();
        random = new Random();
        bulletsArr = new ArrayList<Components>();

        gameLoop = new Timer(1000 / 60, this);
        loadAlien();
        gameLoop.start();
    }

    public void loadAlien() {
        for (int i = 0; i < alienRows; i++) {
            for (int j = 0; j < alienColumns; j++) {
                Image alienImgPick = alienImgArr.get(random.nextInt(alienImgArr.size()));
                // Components alien = new Components(alienImgPick, alienX, alienY, alienWidth,
                // alienHeight); //my ways
                // alienX += alienWidth;
                Components alien = new Components(alienImgPick,
                        alienX + (j * alienWidth), alienY + (i * alienHeight),
                        alienWidth, alienHeight); // tuts ways
                aliensArr.add(alien);
            }
            // alienY += alienHeight + 10;
            // alienX = pixel;
        }
        alienCount = aliensArr.size();
    }

    public void move() {
        // for (Components alien : aliensArr) { // my way
        // alien.x += 2;
        // }

        for (int i = 0; i < aliensArr.size(); i++) { // tuts way
            Components alien = aliensArr.get(i);

            if (alien.alive) {
                alien.x += alienVelocityX;

                if (alien.x + alien.width >= width || alien.x <= 0) {
                    // alienVelocityX *= -1; // THIS 1 LOGIC LINE IS SO COOL!
                    // alien.x += alienVelocityX * 2; // to let the alien who touch the border, go
                    // // back on the formation
                    touchs = true; // fixed HAHAHA

                    // for (int j = 0; j < aliensArr.size(); j++) {
                    // aliensArr.get(j).y += alienHeight;
                    // }
                }
            }

            if (alien.y + alienHeight > ship.y && alien.alive) {
                gameOver = true;
            }
        }

        if (touchs) { // FIX THE BUG THAT IS VERY OBVIOUS IF THE VELOCITY WAS HIGH

            alienVelocityX *= -1; // its becoz wla pa nahuman ang forloop gi change na ang
            // velocity if magbangga sa corner, here gina pahuman sa then before e change
            // pra dli gubot ang formation

            for (int j = 0; j < aliensArr.size(); j++) {
                aliensArr.get(j).y += alienHeight;
            }

            touchs = false;
        }

        for (int i = 0; i < bulletsArr.size(); i++) {
            Components bullet = bulletsArr.get(i);
            bullet.y += bulletVelocityY;

            // check if bullet hits
            for (int j = 0; j < aliensArr.size(); j++) {
                Components alien = aliensArr.get(j);
                if (Collision(bullet, alien) && alien.alive && !bullet.used) {
                    alien.alive = false;
                    bullet.used = true;
                    alienCount--;
                    score += 10;
                }
            }
        }

        // clear useless bullets
        while (bulletsArr.size() > 0 && (bulletsArr.get(0).used || bulletsArr.get(0).y < 0)) {
            bulletsArr.remove(0); // not the best way, shouldhv use linkedlist in the first place
        }

        // next level
        if (alienCount == 0) {
            score += 1000;
            alienColumns = Math.min(alienColumns + 1, 6);
            alienRows = Math.min(alienRows + 1, 8);
            alienVelocityX = Math.min(alienVelocityX + 3, 13);
            aliensArr.clear();
            bulletsArr.clear();
            alienVelocityX = 10; // pra kada clear, permi pa right ang first moves
            loadAlien();
        }

    }

    public boolean Collision(Components a, Components b) {
        boolean res = false;
        if (a.x < b.x + b.width &&
                a.x + a.width > b.x &&
                a.y < b.y + b.height &&
                a.y + a.height > b.y)
            res = true;
        return res;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g); // calling the parents' paintcomponent which is siya itself
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(shipImg, ship.x, ship.y, shipWidth, shipHeight, null);

        // for (Components alien : aliensArr) { //my ways
        // g.drawImage(alien.img, alien.x, alien.y, alien.width, alien.height, null);
        // }

        for (int i = 0; i < aliensArr.size(); i++) {
            Components alien = aliensArr.get(i);
            if (alien.alive) {
                g.drawImage(alien.img, alien.x, alien.y, alien.width, alien.height, null);
            }
        }

        g.setColor(Color.WHITE);
        for (int i = 0; i < bulletsArr.size(); i++) {
            Components bullet = bulletsArr.get(i);
            if (!bullet.used) {
                g.fillRect(bullet.x, bullet.y, bulletWidth, bulletHeight);
            }
        }

        g.setFont(new Font("Arial", Font.BOLD, 20));
        if (gameOver) {
            g.drawString("TOTAL SCORE: " + score, 20, 30);
        } else {
            g.drawString("SCORE: " + score, 20, 30);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (gameOver) { // if gameover, tas nag press siyag anykeys after nag stop
            ship.x = shipX;
            aliensArr.clear();
            bulletsArr.clear();
            alienCount = 0;
            alienVelocityX = 2;
            alienColumns = 3;
            alienRows = 2;
            score = 0;
            gameOver = false;
            loadAlien();
            gameLoop.start();
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            ship.x -= pixel;
            if (ship.x < 0)
                ship.x += pixel;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            ship.x += pixel;
            if (ship.x > width - shipWidth) {
                ship.x -= pixel;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            // Components bullet = new Components(null, ship.x + 30, ship.y, bulletWidth,
            // bulletHeight);
            Components bullet = new Components(null, ship.x + (shipWidth / 2) - (bulletWidth / 2),
                    ship.y, bulletWidth, bulletHeight);
            // Components bullet = new Components(null, ship.x + shipWidth * 15 / 32,
            // ship.y, bulletWidth, bulletHeight); // tuts way
            bulletsArr.add(bullet);
        }

        System.out.println("Bullets Used: " + bulletsArr.size());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if (gameOver) {
            gameLoop.stop();
        }
    }
}
