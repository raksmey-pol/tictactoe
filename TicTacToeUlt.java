import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;;

public class TicTacToeUlt {
    int boardWidth = 700;
    int boardHeight = 750;

    Color color[] = {Color.darkGray, Color.gray, Color.lightGray};
    int colorIndex = 0;

    JFrame frame = new JFrame("Tic Tac Toe Ultimate");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JLabel boardDivider = new JLabel();

    JButton[][] board = new JButton[3][3];
    // JButton[][] metaBoard = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    JButton resetButton = new JButton();

    JButton backButton = new JButton();

    boolean gameOver = false;


    public TicTacToeUlt(){
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic Tac Toe Ultimate");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(9,3));
        boardPanel.setBackground(Color.darkGray);

        frame.add(boardPanel);


        
        for (int outRow = 0; outRow <9; outRow++) {
            board3x3();
        }
    }

    public void board3x3(){
        int i = 0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                i++;
                board[row][col] = new JButton();
                board[row][col].setFont(new Font("Arial", Font.BOLD, 10));
                board[row][col].setFocusable(false);
                board[row][col].setBackground(color[colorIndex]);
                board[row][col].setForeground(Color.white);
                board[row][col].setBorder(new LineBorder(Color.white));
                board[row][col].setText(row + " " + i);
                boardPanel.add(board[row][col]);
            }
        }
        return;
    }
}

