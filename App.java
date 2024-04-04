import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class App {
    int boardWidth = 600;
    int boardHeight = 650;

    JFrame frame = new JFrame("Tic Tac Toe");
    JLabel textLabel = new JLabel();
    JPanel menuPanel = new JPanel();
    JButton firstGame = new JButton("Tic Tac Toe 3x3");
    JButton secGame = new JButton("Tic Tac Toe 10x10");
    JButton thirdGame = new JButton("Tic Tac Toe 3x3 Simple AI");

    public App() {
        gameMenu();
        playGame();
    }
    public static void main(String[] args) throws Exception {
        new App();
    }

    public void playGame() {
        firstGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TicTacToe3by3();
                frame.dispose();
            }
        });

        secGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TicTacToe10by10();
                frame.dispose();
            }
        });
        thirdGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TicTacToe3by3AI();
                frame.dispose();
            }
        });
    }

    public void gameMenu() {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 100));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic Tac Toe");
        textLabel.setOpaque(true);

        firstGame.setBackground(Color.gray);
        firstGame.setFont(new Font("Arial", Font.BOLD, 50));
        firstGame.setFocusable(false);
        firstGame.setForeground(Color.white);
        secGame.setBackground(Color.darkGray);
        secGame.setFont(new Font("Arial", Font.BOLD, 50));
        secGame.setFocusable(false);
        secGame .setForeground(Color.white);
        thirdGame.setBackground(Color.gray);
        thirdGame.setFont(new Font("Arial", Font.BOLD, 25));
        thirdGame.setFocusable(false);
        thirdGame.setForeground(Color.white);

        menuPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        menuPanel.add(firstGame, gbc);
        gbc.gridy++;
        menuPanel.add(secGame, gbc);
        gbc.gridy++;
        menuPanel.add(thirdGame, gbc);

        frame.add(textLabel, BorderLayout.NORTH);
        frame.add(menuPanel, BorderLayout.CENTER);

    }
}
