import java.awt.*;
import javax.swing.*;

public class PacMan extends JPanel {
    private int row = 17;
    private int column = 21;
    private int tileSize = 32;
    private int rowSize = tileSize * row;
    private int columnSize = tileSize * column;

    private ImageIcon redGhost;
    private ImageIcon blueGhost;
    private ImageIcon pinkGhost;
    private ImageIcon orangeGhost;
    private ImageIcon pacmanUp;
    private ImageIcon pacmanDown;
    private ImageIcon pacmanLeft;
    private ImageIcon pacmanRight;
    private ImageIcon wall;

    PacMan() {
        setPreferredSize(new Dimension(rowSize, columnSize));
        setBackground(Color.CYAN);

        redGhost = new ImageIcon("resources/redGhost.png");
        blueGhost = new ImageIcon("resources/blueGhost.png");
        pinkGhost = new ImageIcon("resources/pinkGhost.png");
        orangeGhost = new ImageIcon("resources/orangeGhost.png");

        pacmanUp = new ImageIcon("resources/pacmanUp.png");
        pacmanDown = new ImageIcon("resources/pacmanDown.png");
        pacmanLeft = new ImageIcon("resources/pacmanLeft.png");
        pacmanRight = new ImageIcon("resources/pacmanRight.png");

        wall = new ImageIcon("resources/wall.png");

    }
}
// cc