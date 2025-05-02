package tictactoegame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {

    int boardWidth = 600;
    int boardHeight = 650;  //50 pixels for the text panel(title) on top

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    
    JButton exitButton = new JButton();     //Alta buton ekle
    JButton returnButton = new JButton();

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;

    int turns = 0;

    TicTacToe() {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.DARK_GRAY);
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50)); //Arial, Times New Roman
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.CYAN);
        frame.add(boardPanel);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton cubicle = new JButton();
                board[r][c] = cubicle;
                boardPanel.add(cubicle);

                cubicle.setBackground(Color.DARK_GRAY);
                cubicle.setForeground(Color.white);
                cubicle.setFont(new Font("Arial", Font.BOLD, 120));
                cubicle.setFocusable(false);
                //cubicle.setText(currentPlayer);

                cubicle.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) {
                            return;
                        }
                        JButton cubicle = (JButton) e.getSource();
                        if (cubicle.getText() == "") {
                            cubicle.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if (!gameOver) {
                                if (currentPlayer == playerX) {
                                    currentPlayer = playerO;
                                } else {
                                    currentPlayer = playerX;
                                }
                                textLabel.setText(currentPlayer + "'s turn:");
                            }

                        }

                    }
                });

            }
        }

    }

    void checkWinner() {
        //horizontal
        for (int r = 0; r < 3; r++) {
            if (board[r][0].getText() == "") {
                continue;
            }

            if (board[r][0].getText() == board[r][1].getText()
                    && board[r][1].getText() == board[r][2].getText()) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[r][i]);
                }
                gameOver = true;
                return;
            }
        }
        //vertical
        for (int c = 0; c < 3; c++) {
            if (board[0][c].getText() == "") {
                continue;
            }

            if (board[0][c].getText() == board[1][c].getText()
                    && board[1][c].getText() == board[2][c].getText()) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[i][c]);
                }
                gameOver = true;
                return;
            }
        }
        //left diagnolly
        if (board[0][0].getText() == board[1][1].getText()
                && board[1][1].getText() == board[2][2].getText()
                && board[0][0].getText() != "") {
            for (int i = 0; i < 3; i++) {
                setWinner(board[i][i]);
            }
            gameOver = true;
            return;
        }
        //right diagnolly
        if (board[0][2].getText() == board[1][1].getText()
                && board[1][1].getText() == board[2][0].getText()
                && board[0][2].getText() != "") {
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver = true;
            return;
        }
        //tie
        if (turns == 9) {
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    setTie(board[r][c]);
                }
            }
            gameOver = true;
            return;
        }

    }

    void setWinner(JButton cubicle) {
        cubicle.setForeground(Color.green);
        cubicle.setBackground(Color.gray);
        textLabel.setText(currentPlayer + " is the winner!");
    }

    void setTie(JButton cubicle) {
        cubicle.setForeground(Color.orange);
        cubicle.setBackground(Color.gray);
        textLabel.setText("Tie!");
    }

}
