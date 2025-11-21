import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Main {
    // crash tuts yt for guide only

    public static void main(String[] args) {

        myFrame frame1 = new myFrame();
        PacMan pacmanGame = new PacMan();

        ImageIcon redg = new ImageIcon("resources/pinkGhost.png");
        JLabel l1 = new JLabel();

        l1.setIcon(redg);
        l1.setBounds(20, 20, 100, 100);

        pacmanGame.add(l1);
        frame1.add(pacmanGame);
        frame1.setVisible(true);

    }
}
