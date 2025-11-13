import javax.swing.JLabel;

public class Main {
    // crash tuts yt for guide only

    public static void main(String[] args) {

        myFrame frame1 = new myFrame();
        PacMan pacmanGame = new PacMan();

        frame1.add(pacmanGame);
        frame1.setVisible(true);

    }
}
