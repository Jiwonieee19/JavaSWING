import javax.swing.JFrame;

public class myFrame extends JFrame {

    private int row = 22;
    private int column = 17;
    private int tileSize = 32;
    private int rowSize = tileSize * 23 + 5;
    private int columnSize = tileSize * column + 14;

    myFrame() {
        this.setTitle("PacMan");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(columnSize, rowSize);
        this.setLocationRelativeTo(null);

        PacMan pacmanGame = new PacMan();
        this.add(pacmanGame);
        pacmanGame.requestFocus();
    }
}