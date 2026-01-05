import javax.swing.*;

public class myFrame extends JFrame {
    int columns = 20;
    int rows = 16;
    int pixel = 32;
    int width = columns * pixel;
    int height = rows * pixel;

    myFrame() {
        this.setTitle("Space Invaders");
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        SpaceInvaders si = new SpaceInvaders();
        si.requestFocus();
        this.add(si);
        this.pack();
    }
}
