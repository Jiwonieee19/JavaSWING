import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

public class TicTacToe extends JPanel implements MouseListener {

    // template class for every move of player/bot and its coordinates
    class Components {
        int x, y;
        Image image;
        int width = 180, height = 180;

        Components(Image image, int x, int y) {
            this.image = image;
            this.x = x;
            this.y = y;
        }
    }

    // Variable initialization, and declaration for some;
    private String[] template = { "XXX", "XXX", "XXX" };
    Image X;
    Image O;
    Point coordinates;
    LinkedList<String> remaining;
    HashSet<Components> xplays;
    HashSet<Components> oplays;
    Random random;
    char[] gameBoxTracker = new char[] { '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    boolean gameWinner = false;
    boolean gameTie = false;
    char winner;

    // constructor ofc
    TicTacToe() {
        setSize(600, 600);
        // setPreferredSize(new Dimension(600, 600));
        setBackground(Color.BLACK);
        addMouseListener(this);

        X = new ImageIcon(getClass().getResource("./X.png")).getImage();
        O = new ImageIcon(getClass().getResource("./O.png")).getImage();
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
        xplays = new HashSet<Components>();
        oplays = new HashSet<Components>();
        random = new Random();
    }

    // actually this just store the choice to a class called components
    public void paintChoice() {
        Components xplay = new Components(X, coordinates.x, coordinates.y);
        xplays.add(xplay);
        System.out.println("exist try");
    }

    // if available, the choice is valid then remove that box from the choices
    public boolean exist(String remains) {
        for (int i = 0; i < remaining.size(); i++) {
            System.out.println(remaining.get(i));
            if (remains.equals(remaining.get(i))) {
                remaining.remove(i);
                System.out.println("ABOT DIRI");
                return true;
            }
        }
        return false;
    }

    // randomize action from random, chooses from remaining choices
    public void paintAIChoice() {
        int x = 99;
        int y = 99;
        int dice = random.nextInt(remaining.size());
        String check = remaining.get(dice);
        if (check.equalsIgnoreCase("1st1st")) {
            x = 50;
            y = 40;
            gameBoxTracker[0] = 'O';
        }
        if (check.equalsIgnoreCase("1st2nd")) {
            x = 250;
            y = 40;
            gameBoxTracker[1] = 'O';
        }
        if (check.equalsIgnoreCase("1st3rd")) {
            x = 450;
            y = 40;
            gameBoxTracker[2] = 'O';
        }
        if (check.equalsIgnoreCase("2nd1st")) {
            x = 50;
            y = 240;
            gameBoxTracker[3] = 'O';
        }
        if (check.equalsIgnoreCase("2nd2nd")) {
            x = 250;
            y = 240;
            gameBoxTracker[4] = 'O';
        }
        if (check.equalsIgnoreCase("2nd3rd")) {
            x = 450;
            y = 240;
            gameBoxTracker[5] = 'O';
        }
        if (check.equalsIgnoreCase("3rd1st")) {
            x = 50;
            y = 440;
            gameBoxTracker[6] = 'O';
        }
        if (check.equalsIgnoreCase("3rd2nd")) {
            x = 250;
            y = 440;
            gameBoxTracker[7] = 'O';
        }
        if (check.equalsIgnoreCase("3rd3rd")) {
            x = 450;
            y = 440;
            gameBoxTracker[8] = 'O';
        }

        Components oplay = new Components(O, x, y);
        oplays.add(oplay);
        remaining.remove(dice);
        System.out.println(remaining.size());
    }

    // check if naay nadaog or nahumana
    public void gameWinnerChecker() {
        int tie = 0;
        if (gameBoxTracker[0] == gameBoxTracker[1] && gameBoxTracker[1] == gameBoxTracker[2]) {
            gameWinner = true;
            winner = gameBoxTracker[0];
            tie++;
            System.out.println("HELLU, NA CHANGE: " + gameBoxTracker[0]);
        }
        if (gameBoxTracker[3] == gameBoxTracker[4] && gameBoxTracker[4] == gameBoxTracker[5]) {
            gameWinner = true;
            winner = gameBoxTracker[3];
            tie++;
        }
        if (gameBoxTracker[6] == gameBoxTracker[7] && gameBoxTracker[7] == gameBoxTracker[8]) {
            gameWinner = true;
            winner = gameBoxTracker[6];
            tie++;
        }

        if (gameBoxTracker[0] == gameBoxTracker[3] && gameBoxTracker[3] == gameBoxTracker[6]) {
            gameWinner = true;
            winner = gameBoxTracker[0];
            tie++;
        }
        if (gameBoxTracker[1] == gameBoxTracker[4] && gameBoxTracker[4] == gameBoxTracker[7]) {
            gameWinner = true;
            winner = gameBoxTracker[1];
            tie++;
        }
        if (gameBoxTracker[2] == gameBoxTracker[5] && gameBoxTracker[5] == gameBoxTracker[8]) {
            gameWinner = true;
            winner = gameBoxTracker[2];
            tie++;
        }

        if (gameBoxTracker[0] == gameBoxTracker[4] && gameBoxTracker[4] == gameBoxTracker[8]) {
            gameWinner = true;
            winner = gameBoxTracker[0];
            tie++;
        }
        if (gameBoxTracker[2] == gameBoxTracker[4] && gameBoxTracker[4] == gameBoxTracker[6]) {
            gameWinner = true;
            winner = gameBoxTracker[2];
            tie++;
        }

        if (tie == 2) {
            gameTie = true;
        }

        // checker if the game sill dont have a winner after consuming 8 boxes
        int availableBox = 0;
        for (int k = 0; k < gameBoxTracker.length; k++) {
            if (gameBoxTracker[k] != 'X' && gameBoxTracker[k] != 'O') {
                availableBox++;
                System.out.println("NAA SULOD SA GAMEBOX NOT EQUAL: " + availableBox);
            }
        }
        if (availableBox == 1) {
            gameTie = true;
            System.out.println("NAKASULOD");
        }

    }

    // paints
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    // painting the real stuff
    public void draw(Graphics g) {
        // the white boxes
        for (int i = 0; i < template.length; i++) {
            for (int j = 0; j < template[i].length(); j++) {
                g.setColor(Color.WHITE);
                g.drawRect((j * 200) + 50, (i * 200) + 40, 180, 180);
            }
        }

        for (Components xo : xplays) {
            g.drawImage(X, xo.x, xo.y, xo.width, xo.height, null);
        }

        for (Components xo : oplays) {
            g.drawImage(O, xo.x, xo.y, xo.width, xo.height, null);
        }

        g.setFont(new Font("Arial", Font.BOLD, 20));
        if (gameTie && !gameWinner) {
            g.drawString("GAME OVER: IT'S A TIE", 25, 25);
        } else if (gameWinner) {
            String strwinner = "";
            strwinner = (winner == 'X') ? "PLAYER" : "BOT";
            g.drawString("GAME OVER, WINNER: " + strwinner, 25, 25);
        }
    }

    // ofc function for the mouse action
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getPoint());
        coordinates = e.getPoint();
        // 1ST ROW
        if (coordinates.x > 50 && coordinates.x < 230 && coordinates.y > 40 && coordinates.y < 220) {
            System.out.println("1st Row, 1st Column");
            if (exist("1st1st")) {
                coordinates.x = 50;
                coordinates.y = 40;
                paintChoice();
                gameBoxTracker[0] = 'X';
                paintAIChoice();
            }
        } else if (coordinates.x > 250 && coordinates.x < 430 && coordinates.y > 40 && coordinates.y < 220) {
            System.out.println("1st Row, 2nd Column");
            if (exist("1st2nd")) {
                coordinates.x = 250;
                coordinates.y = 40;
                paintChoice();
                gameBoxTracker[1] = 'X';
                paintAIChoice();
            }
        } else if (coordinates.x > 450 && coordinates.x < 630 && coordinates.y > 40 && coordinates.y < 220) {
            System.out.println("1st Row, 3rd Column");
            if (exist("1st3rd")) {
                coordinates.x = 450;
                coordinates.y = 40;
                paintChoice();
                gameBoxTracker[2] = 'X';
                paintAIChoice();
            }
            // 2ND ROW
        } else if (coordinates.x > 50 && coordinates.x < 230 && coordinates.y > 240 && coordinates.y < 420) {
            System.out.println("2nd Row, 1st Column");
            if (exist("2nd1st")) {
                coordinates.x = 50;
                coordinates.y = 240;
                paintChoice();
                gameBoxTracker[3] = 'X';
                paintAIChoice();
            }
        } else if (coordinates.x > 250 && coordinates.x < 430 && coordinates.y > 240 && coordinates.y < 420) {
            System.out.println("2nd Row, 2nd Column");
            if (exist("2nd2nd")) {
                coordinates.x = 250;
                coordinates.y = 240;
                paintChoice();
                gameBoxTracker[4] = 'X';
                paintAIChoice();
            }
        } else if (coordinates.x > 450 && coordinates.x < 630 && coordinates.y > 240 && coordinates.y < 420) {
            System.out.println("2nd Row, 3rd Column");
            if (exist("2nd3rd")) {
                coordinates.x = 450;
                coordinates.y = 240;
                paintChoice();
                gameBoxTracker[5] = 'X';
                paintAIChoice();
            }
            // 3RD ROW
        } else if (coordinates.x > 50 && coordinates.x < 230 && coordinates.y > 440 && coordinates.y < 620) {
            System.out.println("3rd Row, 1st Column");
            if (exist("3rd1st")) {
                coordinates.x = 50;
                coordinates.y = 440;
                paintChoice();
                gameBoxTracker[6] = 'X';
                paintAIChoice();
            }
        } else if (coordinates.x > 250 && coordinates.x < 430 && coordinates.y > 440 && coordinates.y < 620) {
            System.out.println("3rd Row, 2nd Column");
            if (exist("3rd2nd")) {
                coordinates.x = 250;
                coordinates.y = 440;
                paintChoice();
                gameBoxTracker[7] = 'X';
                paintAIChoice();
            }
        } else if (coordinates.x > 450 && coordinates.x < 630 && coordinates.y > 440 && coordinates.y < 620) {
            System.out.println("3rd Row, 3rd Column");
            if (exist("3rd3rd")) {
                coordinates.x = 450;
                coordinates.y = 440;
                paintChoice();
                gameBoxTracker[8] = 'X';
                paintAIChoice();
            }
        }

        gameWinnerChecker();
        if (gameWinner || gameTie) {
            this.removeMouseListener(this);
        }
        repaint();
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
}
