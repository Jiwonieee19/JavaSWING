import java.awt.*;
import java.util.HashSet;

import javax.swing.*;

public class PacMan extends JPanel {

    class Block {
        int x;
        int y;
        int width;
        int height;
        Image image;

        int Startx;
        int Starty;

        Block(Image image, int x, int y, int width, int height) {
            this.image = image;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.Startx = x;
            this.Starty = y;

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
            "OOOX X     X XOOO",
            "XXX X XXrXX X XXX",
            "O      bpo      O",
            "XXX X XXXXX X XXX",
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

    PacMan() {
        setPreferredSize(new Dimension(columnSize, rowSize));
        setBackground(Color.BLACK);
        setLayout(null);

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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(pacman.image, pacman.x, pacman.y, pacman.width, pacman.height, null);

        // gi sulod sa hashset tapos isa2hon ug pa gawas dris paint kuyawa
        for (Block ghost : ghosts) {
            g.drawImage(ghost.image, ghost.x, ghost.y, ghost.width, ghost.height, null);
        }

        for (Block food : foods) {
            g.drawImage(food.image, food.x, food.y, food.width, food.height, null);
        }

        for (Block wall : walls) {
            g.drawImage(wall.image, wall.x, wall.y, wall.width, wall.height, null);
        }
    }
}