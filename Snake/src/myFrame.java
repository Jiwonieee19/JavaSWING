import javax.swing.*;

public class myFrame extends JFrame {
    int width = 600;
    int height = 900;

    myFrame() {
        this.setTitle("Snake");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        Snake s = new Snake();
        this.add(s);
    }
}
