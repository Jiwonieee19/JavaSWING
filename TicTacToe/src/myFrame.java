import javax.swing.*;

public class myFrame extends JFrame {

    myFrame() {
        this.setTitle("TicTacToe");
        this.setSize(700, 700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        TicTacToe ttt = new TicTacToe();
        this.add(ttt);
        // this.pack();
    }
}
