import javax.swing.JFrame;

public class myFrame extends JFrame {
    int width = 300;
    int height = 450;

    myFrame() {
        setTitle("Flappy Bird");
        setSize(width, height);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FlappyBird fb = new FlappyBird();
        fb.requestFocus();
        this.add(fb);
        // this.pack();
    }
}
