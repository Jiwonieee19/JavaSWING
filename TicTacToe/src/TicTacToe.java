import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

public class TicTacToe extends JPanel implements ActionListener, MouseListener {

    private String[] template = { "XXX", "XXX", "XXX" };
    Image X;
    Image O;
    Point coordinates;
    LinkedList<String> remaining;

    TicTacToe() {
        setSize(600, 600);
        // setPreferredSize(new Dimension(600, 600));
        setBackground(Color.BLACK);
        addMouseListener(this);

        X = new ImageIcon(getClass().getResource("./X.png")).getImage();
        O = new ImageIcon(getClass().getResource("O.png")).getImage();
        coordinates = new Point();
        remaining = new LinkedList<String>();
        remaining.add("1st1st");
        remaining.add("1st2nd");
        remaining.add("1st3rd");
        remaining.add("2nd1st");
        remaining.add("2nd2nd");
        remaining.add("2nd3rd");
        remaining.add("3rd1st");
        remaining.add("3rd2nd");
        remaining.add("3rd3rd");
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

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getPoint());
        coordinates = e.getPoint();
        // 1ST ROW
        if (coordinates.x > 50 && coordinates.x < 230 && coordinates.y > 40 && coordinates.y < 220) {
            System.out.println("1st Row, 1st Column");
        } else if (coordinates.x > 250 && coordinates.x < 430 && coordinates.y > 40 && coordinates.y < 220) {
            System.out.println("1st Row, 2nd Column");
        } else if (coordinates.x > 450 && coordinates.x < 630 && coordinates.y > 40 && coordinates.y < 220) {
            System.out.println("1st Row, 3rd Column");
            // 2ND ROW
        } else if (coordinates.x > 50 && coordinates.x < 230 && coordinates.y > 240 && coordinates.y < 420) {
            System.out.println("2nd Row, 1st Column");
        } else if (coordinates.x > 250 && coordinates.x < 430 && coordinates.y > 240 && coordinates.y < 420) {
            System.out.println("2nd Row, 2nd Column");
        } else if (coordinates.x > 450 && coordinates.x < 630 && coordinates.y > 240 && coordinates.y < 420) {
            System.out.println("2nd Row, 3rd Column");
            // 3RD ROW
        } else if (coordinates.x > 50 && coordinates.x < 230 && coordinates.y > 440 && coordinates.y < 620) {
            System.out.println("3rd Row, 1st Column");
        } else if (coordinates.x > 250 && coordinates.x < 430 && coordinates.y > 440 && coordinates.y < 620) {
            System.out.println("3rd Row, 2nd Column");
        } else if (coordinates.x > 450 && coordinates.x < 630 && coordinates.y > 440 && coordinates.y < 620) {
            System.out.println("3rd Row, 3rd Column");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
