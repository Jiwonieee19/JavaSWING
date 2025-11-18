import java.awt.*;
import javax.swing.*;

public class PacMan extends JPanel {
    private int row = 17;
    private int column = 21;
    private int tileSize = 32;
    private int rowSize = tileSize * row;
    private int columnSize = tileSize * column;

    // public ImageIcon redg = new ImageIcon("resources/pinkGhost.png");
    private Image redGhost;
    private Image blueGhost;
    private Image pinkGhost;
    private Image orangeGhost;
    private Image pacmanUp;
    private Image pacmanDown;
    private Image pacmanLeft;
    private Image pacmanRight;

    PacMan() {
        setPreferredSize(new Dimension(rowSize, columnSize));
        setBackground(Color.CYAN);

        redGhost = new ImageIcon(getClass().getResource("./resources./redGhost.png")).getImage();
    }
}
// cc ky magkuha kog sp toms jusko cc