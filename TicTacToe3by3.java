import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe3by3 {
    int boardWidth = 600;
    int boardHeight = 650;

    JFrame frame = new JFrame("Tic Tac Toe 3x3");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    JButton resetButton = new JButton();

    boolean gameOver = false;

    TicTacToe3by3() {
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
        textLabel.setText("Tic Tac Toe 3x3");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3,3));
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

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                JButton tile = new JButton();
                board[row][col] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white); //text color
                tile.setFont(new Font("Arial", Font.BOLD, 50));
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) {
                            return;
                        }
                        JButton tile = (JButton) e.getSource();
                        if (tile.getText().equals("")) {
                            if (currentPlayer == playerX) {
                                tile.setText(playerX);
                                tile.setForeground(Color.red);
                                tile.setBackground(Color.lightGray);
                            } else {
                                tile.setText(playerO);
                                tile.setForeground(Color.green);
                                tile.setBackground(Color.lightGray);
                            }
                            
                            checkWinner();
                            if (!gameOver) {
                                currentPlayer = currentPlayer == playerX ? playerO : playerX; 
                                // (currentPlayer == playerX) {
                                //     currentPlayer = playerO;
                                // } else {
                                //     currentPlayer = playerX;
                                // }
                                textLabel.setText(currentPlayer + "'s turn");
                            }  
                        
                        }
                    }
                });
            }
        }

    }

    void checkWinner() {
        // Check rows
        for (int row = 0; row < 3; row++) {
            if (board[row][0].getText().equals(currentPlayer) && 
                board[row][1].getText().equals(currentPlayer) && 
                board[row][2].getText().equals(currentPlayer)) {
                for (int i = 0; i < 3; i++) {
                    board[row][i].setBackground(Color.green);
                }
                gameOver = true;
                textLabel.setText(currentPlayer + " wins!");
                return;
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            if (board[0][col].getText().equals(currentPlayer) && 
                board[1][col].getText().equals(currentPlayer) && 
                board[2][col].getText().equals(currentPlayer)) {
                for (int i = 0; i < 3; i++) {
                    board[i][col].setBackground(Color.green);
                }
                gameOver = true;
                textLabel.setText(currentPlayer + " wins!");
                return;
            }
        }

        // Check diagonals
        if (board[0][0].getText().equals(currentPlayer) && 
            board[1][1].getText().equals(currentPlayer) && 
            board[2][2].getText().equals(currentPlayer)) {
            for (int i = 0; i < 3; i++) {
                board[i][i].setBackground(Color.green);
            }
            gameOver = true;
            textLabel.setText(currentPlayer + " wins!");
            return;
        }

        // Check anti-diagonals
        if (board[0][2].getText().equals(currentPlayer) && 
            board[1][1].getText().equals(currentPlayer) && 
            board[2][0].getText().equals(currentPlayer)) {
            for (int i = 0; i < 3; i++) {
                board[i][2-i].setBackground(Color.green);
            }
            gameOver = true;
            textLabel.setText(currentPlayer + " wins!");
            return;
        }

        // Check for tie
        boolean isTie = true;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col].getText().equals("")) {
                    isTie = false;
                    break;
                }
            }
        }

        if (isTie) {
            gameOver = true;
            textLabel.setText("Tie!");
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    board[row][col].setBackground(Color.red);
                }
            }
        }
    }
}
