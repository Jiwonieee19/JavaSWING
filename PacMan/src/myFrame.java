import javax.swing.JFrame;

public class myFrame extends JFrame {

    private int row = 22;
    private int column = 17;
    private int tileSize = 32;
    private int rowSize = tileSize * 23;
    private int columnSize = tileSize * column;

    myFrame() {
        this.setTitle("PacMan");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(columnSize, rowSize);
        // this.setLayout(null);
        this.setLocationRelativeTo(null);

        PacMan pacmanGame = new PacMan();
        this.add(pacmanGame);
    }
}