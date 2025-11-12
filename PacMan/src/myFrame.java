import javax.swing.JFrame;

public class myFrame extends JFrame {

    private int row = 17;
    private int column = 21;
    private int tileSize = 32;
    private int rowSize = tileSize * row;
    private int columnSize = tileSize * column;

    myFrame() {
        this.setTitle("PacMan");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(rowSize, columnSize);
        this.setLocationRelativeTo(null);
    }
}