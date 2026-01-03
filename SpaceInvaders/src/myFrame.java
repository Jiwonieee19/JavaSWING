import javax.swing.*;

public class myFrame extends JFrame {
    int width = 0;
    int height = 0;

    myFrame() {
        this.setTitle("Space Invaders");
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        SpaceInvaders si = new SpaceInvaders();
        this.add(si);
    }
}
