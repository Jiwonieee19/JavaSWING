import javax.swing.*;
import java.awt.*;

public class TicTacToe extends JPanel {

    private String[] template = { "XXX", "XXX", "XXX" };
    Image X;
    Image O;

    TicTacToe() {
        setSize(600, 600);
        // setPreferredSize(new Dimension(600, 600));
        setBackground(Color.BLACK);

        X = new ImageIcon(getClass().getResource("./")).getImage();
        O = new ImageIcon(getClass().getResource("./")).getImage();
        // loadTemplate();
    }

    public void loadTemplate() {
        for (int i = 0; i < template.length; i++) {
            for (int j = 0; j < template[i].length(); j++) {

            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        for (int i = 0; i < template.length; i++) {
            for (int j = 0; j < template[i].length(); j++) {
                g.setColor(Color.WHITE);
                g.drawRect((j * 200) + 50, (i * 200) + 40, 180, 180);
            }
        }
    }
}
