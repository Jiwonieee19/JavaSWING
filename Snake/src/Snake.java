import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Random;

import javax.swing.*;

public class Snake extends JPanel implements ActionListener, KeyListener {

    // template ni body, head, food
    class Components {
        // declaration
        int x, y, velocityX, velocityY, startX, startY, width, height;
        char direction, prevDirection;

        // initialization, pede rani e separate ug class
        Components(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.startX = x;
            this.startY = y;
            this.width = width;
            this.height = height;
        }

        // store direction then call updatemovement function
        void updateDirection(char direction) {
            this.direction = direction;
            updateMovement();
        }

        // makes the movement by manipulating velocity value
        void updateMovement() {
            if (this.direction == 'R') {
                this.velocityX = 6;
                this.velocityY = 0; // if wla ni, mag dagan 2 ways ang head, since ma store ang prev velocityY if
                                    // dli na 0
            }
            if (this.direction == 'L') {
                this.velocityX = -6;
                this.velocityY = 0;
            }
            if (this.direction == 'U') {
                this.velocityY = -6;
                this.velocityX = 0;
            }
            if (this.direction == 'D') {
                this.velocityY = 6;
                this.velocityX = 0;
            }
        }
    }

    // declaration
    HashSet<Components> bodies;
    Components head;
    int foodWidthHeight = 10;
    int bodyWidthHeight = 20;
    Random random = new Random();
    Timer gameLoop;

    // initialization
    Snake() {
        // inside here will typically what will just hapeen sa game, so make it less
        // asmuchaspossible
        setSize(600, 900);
        setBackground(Color.BLACK);
        setLayout(null);
        setFocusable(true);
        addKeyListener(this);

        loadBody();
        gameLoop = new Timer(50, this);
        gameLoop.start();
    }

    // store the firstbody and head (duha2 pako if dria ba motubo iyang lawas or
    // not)
    public void loadBody() {
        bodies = new HashSet<Components>();
        head = new Components(30, 10, bodyWidthHeight, bodyWidthHeight);

        Components firstBody = new Components(10, 10, bodyWidthHeight, bodyWidthHeight);
        bodies.add(firstBody);
    }

    // e call sa actionlistener pra ma update ang head, but need pa e update ang
    // bodys ha
    public void move() {
        head.x += head.velocityX;
        head.y += head.velocityY;
    }

    // needed mani, especially ang super.paint
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    // painting the shi
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(head.x, head.y, head.width, head.height);
        for (Components body : bodies) {
            g.setColor(Color.WHITE);
            g.fillRect(body.x, body.y, body.width, body.height);
        }
    }

    // action listener na ma pop ni timer
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

    // ACTIONS
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            head.updateDirection('R');
            System.out.println("RAYT");
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            head.updateDirection('L');
            System.out.println("LIP");
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            head.updateDirection('U');
            System.out.println("AP");
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            head.updateDirection('D');
            System.out.println("DAWN");
        }
    }
}
