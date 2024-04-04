import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe10by10 {
    int boardWidth = 600;
    int boardHeight = 650;
    int size = 10;
    int moveCounter = 0;

    JFrame frame = new JFrame("Tic Tac Toe 10x10");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[size][size];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    JButton resetButton = new JButton();

    boolean gameOver = false;

    TicTacToe10by10() {
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
        textLabel.setText("Tic Tac Toe 10x10");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(size,size));
        boardPanel.setBackground(Color.darkGray);
        
        resetButton.setText("Left Click for Reset | Right Click for Menu");
        resetButton.setFont(new Font("Arial", Font.BOLD, 20));
        resetButton.setFocusable(false);
        resetButton.setBackground(Color.darkGray);
        resetButton.setForeground(Color.white);
        frame.add(resetButton, BorderLayout.SOUTH);

        frame.add(boardPanel);

        resetButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    for (int row = 0; row < 3; row++) {
                        for (int col = 0; col < 3; col++) {
                            board[row][col].setText("");
                            board[row][col].setBackground(Color.darkGray);
                        }
                    }
                    currentPlayer = playerX;
                    textLabel.setText("Tic Tac Toe 3x3");
                    gameOver = false;;
                    System.out.println("Left Click");
                }
                else if(SwingUtilities.isRightMouseButton(e)) {

                            new App();
                            frame.dispose();
                    System.out.println("Right Click");
                }
            }
        });

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                JButton tile = new JButton();
                board[row][col] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white); //text color
                tile.setFont(new Font("Arial", Font.BOLD, 30));
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (((JButton)e.getSource()).getText() == "" && !gameOver) {
                            if (currentPlayer == playerX) {
                                ((JButton)e.getSource()).setForeground(Color.red);
                                ((JButton)e.getSource()).setBackground(Color.lightGray);
                            } else {
                                ((JButton)e.getSource()).setForeground(Color.green);
                                ((JButton)e.getSource()).setBackground(Color.lightGray);
                            }
                            ((JButton)e.getSource()).setText(currentPlayer);
                            moveCounter++;
                            checkWinner();
                            currentPlayer = currentPlayer == playerX ? playerO : playerX; 
                            textLabel.setText(currentPlayer + "'s turn");
                            if (moveCounter == size * size && !gameOver) {
                                textLabel.setText("It's a draw!");
                                for (int row = 0; row < size; row++) {
                                    for (int col = 0; col < size; col++) {
                                        board[row][col].setBackground(Color.red);
                                    }
                                }
                                gameOver = true;
                            }
                        }
                    }
                });


            }
        }
    }
    public void checkWinner() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                String symbol = board[i][j].getText();
                if (symbol != "") {
                    // Check horizontal
                    if (j + 4 < size && checkSequence(symbol, i, j, 0, 1)){
                        gameOver = true;
                        textLabel.setText("Player " + symbol + " wins!");
                        for (int k = 0; k < 5; k++) {
                            board[i][j + k].setBackground(Color.green);
                        }
                        return;
                    };
                    // Check vertical
                    if (i + 4 < size && checkSequence(symbol, i, j, 1, 0)){
                        gameOver = true;
                        textLabel.setText("Player " + symbol + " wins!");
                        for (int k = 0; k < 5; k++) {
                            board[i + k][j].setBackground(Color.green);
                        }
                        return;
                    };
                    // Check diagonal
                    if (i + 4 < size && j + 4 < size && checkSequence(symbol, i, j, 1, 1)){
                        gameOver = true;
                        textLabel.setText("Player " + symbol + " wins!");
                        for (int k = 0; k < 5; k++) {
                            board[i + k][j + k].setBackground(Color.green);
                        }
                        return;
                    };
                    // Check anti-diagonal
                    if (i + 4 < size && j - 4 >= 0 && checkSequence(symbol, i, j, 1, -1)){
                        gameOver = true;
                        textLabel.setText("Player " + symbol + " wins!");
                        for (int k = 0; k < 5; k++) {
                            board[i + k][j - k].setBackground(Color.green);
                        }
                        return;
                    };
                }
            }
        }
        return; // No winner yet
    }

    private boolean checkSequence(String symbol, int row, int col, int deltaRow, int deltaCol) {
        for (int k = 1; k < 5; k++) {
            if (board[row + k * deltaRow][col + k * deltaCol].getText() != (symbol)) {
                return false;
            }
        }
        return true;
    }
}