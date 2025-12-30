import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.*;

public class Snake extends JPanel implements ActionListener, KeyListener {

    // template ni body, head, food
    class Components {
        // declaration
        int x, y, velocityX, velocityY, startX, startY, width, height;
        char direction, ogDirection = ' ';
        int prevX, prevY;

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

        void bodyDirection(int prevX, int prevY) {
            this.x = prevX;
            this.y = prevY;
        }

        // makes the movement by manipulating velocity value
        void updateMovement() {
            if (this.direction == 'R') {
                this.velocityX = 20;
                this.velocityY = 0; // if wla ni, mag dagan 2 ways ang head, since ma store ang prev velocityY if
                                    // dli na 0
            }
            if (this.direction == 'L') {
                this.velocityX = -20;
                this.velocityY = 0;
            }
            if (this.direction == 'U') {
                this.velocityY = -20;
                this.velocityX = 0;
            }
            if (this.direction == 'D') {
                this.velocityY = 20;
                this.velocityX = 0;
            }
        }

    }

    // declaration
    LinkedList<Components> bodies;
    Components head;
    Components food;
    Components bodyEaten;
    int foodWidthHeight = 10;
    int bodyWidthHeight = 20;
    Random random = new Random();
    Timer gameLoop;
    int score;

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
        gameLoop = new Timer(200, this);
        gameLoop.start();
    }

    // store the firstbody and head (duha2 pako if dria ba motubo iyang lawas or
    // not)
    public void loadBody() {
        bodies = new LinkedList<Components>();
        head = new Components(0, 0, bodyWidthHeight, bodyWidthHeight);
        food = new Components(random.nextInt(569), random.nextInt(839), 10, 10);

        Components firstBody = new Components(0, 0, bodyWidthHeight, bodyWidthHeight);
        bodies.add(firstBody);
        Components secondBody = new Components(0, 0, bodyWidthHeight, bodyWidthHeight);
        bodies.add(secondBody);
        // Components thirdBody = new Components(80, 10, bodyWidthHeight,
        // bodyWidthHeight);
        // bodies.add(thirdBody);
        // Components foBody = new Components(80, 10, bodyWidthHeight, bodyWidthHeight);
        // bodies.add(foBody);
        // THANKS TEST BODY'S AHAHAHAHA anyways dria na mo pop up ang nakaon na foods
        // after makalihok sa existing bodies base skong pag sabot skong function
    }

    // e call sa actionlistener pra ma update ang head, but need pa e update ang
    // bodys ha
    public void move() {

        head.x += head.velocityX;
        head.y += head.velocityY;

        if (collision(head, food)) {
            food.x = random.nextInt(569);
            food.y = random.nextInt(839);
            System.out.println(bodyEaten);
            System.out.println(score);
            bodyEaten = new Components(head.x, head.y, bodyWidthHeight, bodyWidthHeight);
            score += 10;
            bodies.add(bodyEaten);
        }

        if (head.x < 0 || head.x > 570 || head.y < 0 || head.y > 840) {
            gameLoop.stop();
        }

        // body
        if (head.velocityX == 0 && head.velocityY == 0) {
        } else {
            for (int i = 0; i < 2; i++) {
                bodies.get(i).bodyDirection(head.prevX, head.prevY);
                head.prevX = head.x;
                head.prevY = head.y;
                if (i == 1) {
                    for (int j = 1; j < bodies.size(); j++) {
                        bodies.get(j).bodyDirection(bodies.get(j - 1).prevX, bodies.get(j - 1).prevY);
                        bodies.get(j - 1).prevX = bodies.get(j - 1).x;
                        bodies.get(j - 1).prevY = bodies.get(j - 1).y;

                    }
                }
            }
        }
        // TANGINA NA SOLVE NKO NGA WALAY AI
    }

    public boolean collision(Components a, Components b) {
        if (a.x + a.width > b.x &&
                a.x < b.x + b.width &&
                a.y + a.height > b.y &&
                a.y < b.y + b.height)
            return true;
        return false;
    }

    // needed mani, especially ang super.paint
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    // painting the shi
    public void draw(Graphics g) {

        g.setColor(Color.YELLOW);
        g.fillOval(food.x, food.y, food.width, food.height);

        for (Components body : bodies) {
            g.setColor(Color.WHITE);
            g.fillRect(body.x, body.y, body.width, body.height);
            g.setColor(Color.RED);
            g.drawRect(body.x, body.y, body.width, body.height);
        }

        g.setColor(Color.RED);
        g.fillRect(head.x, head.y, head.width, head.height);
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
