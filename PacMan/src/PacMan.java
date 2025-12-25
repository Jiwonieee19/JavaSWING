import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;

import javax.swing.*;

public class PacMan extends JPanel implements ActionListener, KeyListener {

    // TEMPLATE SIYA SA TANAN SPRITES
    class Block {
        int x;
        int y;
        int width;
        int height;
        Image image;

        int Startx;
        int Starty;

        char direction = ' ';
        int velocityX = 0;
        int velocityY = 0;

        Block(Image image, int x, int y, int width, int height) {
            this.image = image;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.Startx = x;
            this.Starty = y;

        }

        void updateDirection(char direction) {
            this.direction = direction;
            updateVelocity();
        }

        void updateVelocity() {
            if (this.direction == 'U') {
                this.velocityX = 0;
                this.velocityY = -tileSize / 4;
            } else if (this.direction == 'D') {
                this.velocityX = 0;
                this.velocityY = tileSize / 4;
            } else if (this.direction == 'L') {
                this.velocityX = -tileSize / 4;
                this.velocityY = 0;
            } else if (this.direction == 'R') {
                this.velocityX = tileSize / 4;
                this.velocityY = 0;
            }
        }
    }

    private int row = 22;
    private int column = 17;
    private int tileSize = 32;
    private int rowSize = tileSize;
    private int columnSize = tileSize * column;

    private Image redGhost;
    private Image blueGhost;
    private Image pinkGhost;
    private Image orangeGhost;
    private Image pacmanUp;
    private Image pacmanDown;
    private Image pacmanLeft;
    private Image pacmanRight;
    private Image wall;

    private String[] tileMap = {
            "XXXXXXXXXXXXXXXXX",
            "X       X       X",
            "X XX XX X XX XX X",
            "X               X",
            "X XX X XXX X XX X",
            "X    X     X    X",
            "XXXX XXX XXX XXXX",
            "OOOX         XOOO",
            "XXXX  XXrXX  XXXX",
            "O      bpo      O",
            "XXXX  XXXXXX  XXX",
            "OOOX X     X XOOO",
            "XXXX X XXX X XXXX",
            "X       X       X",
            "X XX XX X XX XX X",
            "X  X    P    X  X",
            "XX X X XXX X X XX",
            "X    X  X  X    X",
            "X XXXXX X XXXXX X",
            "X               X",
            "X     XXXXX     X",
            "XXXXXXXXXXXXXXXXX"
    };

    HashSet<Block> walls;
    HashSet<Block> ghosts;
    HashSet<Block> foods;
    Block pacman;

    Timer gameLoop;

    PacMan() {
        setPreferredSize(new Dimension(columnSize, rowSize));
        setBackground(Color.BLACK);
        setLayout(null);
        addKeyListener(this);
        setFocusable(true);

        redGhost = new ImageIcon(getClass().getResource("./resources./redGhost.png")).getImage();
        blueGhost = new ImageIcon(getClass().getResource("./resources./blueGhost.png")).getImage();
        pinkGhost = new ImageIcon(getClass().getResource("./resources./pinkGhost.png")).getImage();
        orangeGhost = new ImageIcon(getClass().getResource("./resources./orangeGhost.png")).getImage();

        wall = new ImageIcon(getClass().getResource("./resources./wall.png")).getImage();

        pacmanUp = new ImageIcon(getClass().getResource("./resources./pacmanUp.png")).getImage();
        pacmanDown = new ImageIcon(getClass().getResource("./resources./pacmanDown.png")).getImage();
        pacmanLeft = new ImageIcon(getClass().getResource("./resources./pacmanLeft.png")).getImage();
        pacmanRight = new ImageIcon(getClass().getResource("./resources./pacmanRight.png")).getImage();

        loadMap();
        System.out.println("" + foods.size() + '\n' + ghosts.size() + '\n' + walls.size());
        // GA PA TRIGGER SA ACTIONLISTENER
        gameLoop = new Timer(50, this); // 20fps (1000ml = 1sec / 50mlss)
        gameLoop.start();
    }

    public void loadMap() {
        walls = new HashSet<Block>();
        ghosts = new HashSet<Block>();
        foods = new HashSet<Block>();

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column; c++) {
                String mapRow = tileMap[r];
                char mapColumn = mapRow.charAt(c);

                int posX = c * tileSize;
                int posY = r * tileSize;

                // GI STORE EACH CATEGORY SA HASHSET, THEN FOR EACH LOOP LATUR PAG DISPLAY
                if (mapColumn == 'X') { // wall
                    Block wallBlock = new Block(wall, posX, posY, tileSize, tileSize);
                    walls.add(wallBlock);
                } else if (mapColumn == ' ') { // food
                    Block foodBlock = new Block(null, posX + 14, posY + 14, 4, 4);
                    foods.add(foodBlock);
                } else if (mapColumn == 'b') { // blue G
                    Block ghostBlock = new Block(blueGhost, posX, posY, tileSize, tileSize);
                    ghosts.add(ghostBlock);
                } else if (mapColumn == 'r') { // red G
                    Block ghostBlock = new Block(redGhost, posX, posY, tileSize, tileSize);
                    ghosts.add(ghostBlock);
                } else if (mapColumn == 'p') { // pink G
                    Block ghostBlock = new Block(pinkGhost, posX, posY, tileSize, tileSize);
                    ghosts.add(ghostBlock);
                } else if (mapColumn == 'o') { // orange G
                    Block ghostBlock = new Block(orangeGhost, posX, posY, tileSize, tileSize);
                    ghosts.add(ghostBlock);
                } else if (mapColumn == 'P') { // Pacman
                    pacman = new Block(pacmanLeft, posX, posY, tileSize, tileSize);
                }
            }
        }
    }

    // MAO NI E REPAINT KADA TRIGGER SA ACTIONLISTENER
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    // CREATING THE GAME ITSELF, BUT GINA PAINT NIYA TANAN EVERY 50MLS
    public void draw(Graphics g) {

        g.drawImage(pacman.image, pacman.x, pacman.y, pacman.width, pacman.height, null);

        // gi sulod sa hashset tapos isa2hon ug pa gawas dris paint kuyawa, DIRIA GI
        // DISPLAY USING GRAPHICS
        for (Block ghost : ghosts) {
            g.drawImage(ghost.image, ghost.x, ghost.y, ghost.width, ghost.height, null);
        }

        for (Block wall : walls) {
            g.drawImage(wall.image, wall.x, wall.y, wall.width, wall.height, null);
        }

        g.setColor(Color.WHITE);
        for (Block food : foods) {
            g.fillRect(food.x, food.y, food.width, food.height);
        }
    }

    public void move() {
        pacman.x += pacman.velocityX;
        pacman.y += pacman.velocityY;

        // if true si collision method, dli mo move si pacman
        for (Block wall : walls) {
            if (collision(pacman, wall)) {
                pacman.x -= pacman.velocityX;
                pacman.y -= pacman.velocityY;
                break;
            }
        }
    }

    // this damn formula needs to be studied
    // public boolean collision(Block a, Block b) { //this is a work of sorcery!
    // return a.x < b.x + b.width &&
    // a.x + a.width > b.x &&
    // a.y < b.y + b.height &&
    // a.y + a.height > b.y;
    // }

    public boolean collision(Block a, Block b) { // work but a lot to fix, its mine btw
        boolean res = false;
        if ((a.x < b.x && a.width + a.x > b.x) &&
                (b.y < a.y && b.height + b.y > a.y) ||
                (a.y < b.y && a.height + a.y > b.y) &&
                        (b.x < a.x && b.width + b.x > a.x)) {
            res = true;
        }
        return res;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move(); // if naa dri, ang keys kay murag naka hold, same as pacman game talaga, since
                // the this.direction now contains the last key nga gi pressed
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
        System.out.println("keyEcent: " + e.getKeyCode());
        char direction = ' ';
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            direction = 'L';
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            direction = 'R';
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            direction = 'U';
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            direction = 'D';
            // pacman.updateDirection('D'); // in ani nga approach ang sa yt
        }
        // SEND UG PARAMETER TO COMPARE SA DIRECTION INSIDE CLASS BLOCK, PARA MA
        // DETERMINE KNSA NGA BLOCK MO MOVE
        pacman.updateDirection(direction);
        // move(); //if naa dri, kada click pa siya mo move, since mag restart to ' '
        // ang char direction if dria
    }

}