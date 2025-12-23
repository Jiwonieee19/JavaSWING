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
    private int rowSize = tileSize * 23;
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
        setBackground(Color.WHITE);
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

        // JLabel tryy = new JLabel();
        // ImageIcon tryIcon = new ImageIcon("wall.png");
        // tryy.setIcon(tryIcon);
        // this.add(tryy);
        loadMap();
    }

    public void loadMap() {
        walls = new HashSet<Block>();
        ghosts = new HashSet<Block>();
        foods = new HashSet<Block>();

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column; c++) {
                String mapRow = tileMap[r];
                char mapColumn = mapRow.charAt(c);

                if (mapColumn == 'X') {
                    JLabel l1 = new JLabel();
                    l1.setIcon(new ImageIcon("wall.png"));
                    int posX = c * tileSize;
                    int posY = r * tileSize;
                    l1.setBounds(posX, posY, tileSize, tileSize);
                    this.add(l1);
                }
            }
        }
    }
}