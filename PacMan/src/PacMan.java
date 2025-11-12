import java.awt.*;
import javax.swing.*;

public class PacMan extends JPanel {
    private int row = 17;
    private int column = 21;
    private int tileSize = 32;
    private int rowSize = tileSize * row;
    private int columnSize = tileSize * column;

    PacMan() {
        setPreferredSize(new Dimension(rowSize, columnSize));
        setBackground(Color.CYAN);
    }
}
