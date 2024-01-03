import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Minesweeper implements MouseListener {

    JFrame frame;

    Buttons[][] board = new Buttons[10][10];

    int openButton;

    public Minesweeper() {
        openButton = 0;
        frame = new JFrame("MINESWEEPER");                  // Frame name
        frame.setSize(800, 800);                    // Frame size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(10, 10));      // Frame layout with Grid

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {

                Buttons b = new Buttons(row, col);
                frame.add(b);
                b.addMouseListener(this);
                board[row][col] = b;

            }
        }

        generateMine();
        updateCount();
        //printMine();

        frame.setVisible(true);

    }

    // Random mine generation method

    public void generateMine() {
        int i = 0;
        while (i < 10) {                            // Number of Mines
            int randRow = (int) (Math.random() * board.length);
            int randCol = (int) (Math.random() * board[0].length);

            while (board[randRow][randCol].isMine()) {
                randRow = (int) (Math.random() * board.length);
                randCol = (int) (Math.random() * board[0].length);
            }
            board[randRow][randCol].setMine(true);
            i++;
        }
    }

    // Method of displaying icons on the screen

    public void print() {
        for (Buttons[] buttons : board) {
            for (int col = 0; col < board[0].length; col++) {
                if (buttons[col].isMine()) {
                    buttons[col].setIcon(new ImageIcon("src/mine.png"));
                } else {
                    buttons[col].setText(buttons[col].getCount() + "");
                    buttons[col].setEnabled(false);
                }
            }
        }
    }

    // Mine display method on the screen only for control!!

    public void printMine() {
        for (Buttons[] buttons : board)
            for (int col = 0; col < board[0].length; col++) {
                if (buttons[col].isMine()) {
                    buttons[col].setIcon(new ImageIcon("src/mine.png"));
                }
            }
    }

    // Method to update the number of mines in the game

    public void updateCount() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col].isMine()) {
                    counting(row, col);
                }
            }
        }
    }

    // Method of counting mines in the game

    public void counting(int row, int col) {
        for (int i = row - 1; i <= row + 1; i++) {
            for (int k = col - 1; k <= col + 1; k++) {
                try {
                    int value = board[i][k].getCount();
                    board[i][k].setCount(++value);
                } catch (Exception e) {

                }
            }
        }
    }

    // Method to open the clicked area

    public void open(int r, int c) {
        if (r >= 0 && r < board.length && c >= 0 && c < board[0].length && board[r][c].getText().length() <= 0 && board[r][c].isEnabled() != false) {
            if (board[r][c].getCount() != 0) {
                board[r][c].setText(board[r][c].getCount() + "");
                board[r][c].setEnabled(false);
                openButton++;
            } else {
                openButton++;
                board[r][c].setEnabled(false);
                open(r - 1, c);
                open(r + 1, c);
                open(r, c - 1);
                open(r, c + 1);
            }
        }
    }

    // Mouse click method -> 1 for right click 3for left click

    @Override
    public void mouseClicked(MouseEvent e) {
        Buttons b = (Buttons) e.getComponent();
        if (e.getButton() == 1) {
            //System.out.println("left click");
            if (b.isMine()) {
                JOptionPane.showMessageDialog(frame, "GAME OVER!");
                print();
            } else {
                open(b.getRow(), b.getCol());
                if (openButton == (board.length * board[0].length) - 10) {
                    JOptionPane.showMessageDialog(frame, "You are the  WINNER CONGRATULATIONS!!");
                    print();
                }
            }
        } else if (e.getButton() == 3) {
            //System.out.println("right click");
            if (!b.isFlag()) {
                b.setIcon(new ImageIcon("src/flag.png"));
                b.setFlag(true);
            } else {
                b.setIcon(null);
                b.setFlag(false);
            }

        }

    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {

    }

    @Override
    public void mousePressed(MouseEvent arg0) {

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {

    }

}