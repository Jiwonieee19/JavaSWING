import java.awt.*;
import javax.swing.*;

public class PacMan extends JPanel {
    private int row = 17;
    private int column = 21;
    private int tileSize = 32;
    private int rowSize = tileSize * row;
    private int columnSize = tileSize * column;

    // public ImageIcon redg = new ImageIcon("resources/pinkGhost.png");
    // tryy imageicon
    private ImageIcon redGhost;
    private Image blueGhost;
    private Image pinkGhost;
    private Image orangeGhost;
    private Image pacmanUp;
    private Image pacmanDown;
    private Image pacmanLeft;
    private Image pacmanRight;
    private Image wall;

    PacMan() {
        setPreferredSize(new Dimension(rowSize, columnSize));
        setBackground(Color.CYAN);

        // redGhost = new
        // ImageIcon(getClass().getResource("./resources./redGhost.png")).getImage();
        redGhost = new ImageIcon("resources/redGhost.png");
        blueGhost = new ImageIcon(getClass().getResource("./resources./redGhost.png")).getImage();
        pinkGhost = new ImageIcon(getClass().getResource("./resources./redGhost.png")).getImage();
        orangeGhost = new ImageIcon(getClass().getResource("./resources./redGhost.png")).getImage();
        wall = new ImageIcon(getClass().getResource("./resources./redGhost.png")).getImage();

        pacmanUp = new ImageIcon(getClass().getResource("./resources./redGhost.png")).getImage();
        pacmanDown = new ImageIcon(getClass().getResource("./resources./redGhost.png")).getImage();
        pacmanLeft = new ImageIcon(getClass().getResource("./resources./redGhost.png")).getImage();
        pacmanRight = new ImageIcon(getClass().getResource("./resources./redGhost.png")).getImage();

    }
}