import java.awt.*;
import javax.swing.JFrame;

public class myFrame extends JFrame {
    myFrame() {
        this.setTitle("PacMan");
        this.setSize(400, 320);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}