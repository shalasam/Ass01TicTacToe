import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeFrame extends JFrame {
    private TicTacToeButton[][] board;
    private boolean xTurn = true;
    private static final int SIZE = 3;

    public TicTacToeFrame() {
        setTitle("Tic Tac Toe Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout());

        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(SIZE, SIZE));
        board = new TicTacToeButton[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = new TicTacToeButton(i, j);
                board[i][j].setFont(new Font("Arial", Font.BOLD, 40));
                board[i][j].addActionListener(new ButtonClickListener());
                gamePanel.add(board[i][j]);
            }
        }

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> System.exit(0));

        add(gamePanel, BorderLayout.CENTER);
        add(quitButton, BorderLayout.SOUTH);
        setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            TicTacToeButton button = (TicTacToeButton) e.getSource();
            if (!button.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Invalid move! Square already taken.");
                return;
            }

            button.setText(xTurn ? "X" : "O");
            if (checkWin()) {
                JOptionPane.showMessageDialog(null, (xTurn ? "X" : "O") + " wins!");
                resetBoard();
            } else if (isBoardFull()) {
                JOptionPane.showMessageDialog(null, "It's a tie!");
                resetBoard();
            }
            xTurn = !xTurn;
        }
    }

    private boolean checkWin() {
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0].getText().equals(board[i][1].getText()) && board[i][1].getText().equals(board[i][2].getText()) && !board[i][0].getText().equals(""))
                return true;
            if (board[0][i].getText().equals(board[1][i].getText()) && board[1][i].getText().equals(board[2][i].getText()) && !board[0][i].getText().equals(""))
                return true;
        }
        if (board[0][0].getText().equals(board[1][1].getText()) && board[1][1].getText().equals(board[2][2].getText()) && !board[0][0].getText().equals(""))
            return true;
        if (board[0][2].getText().equals(board[1][1].getText()) && board[1][1].getText().equals(board[2][0].getText()) && !board[0][2].getText().equals(""))
            return true;
        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j].getText().equals(""))
                    return false;
            }
        }
        return true;
    }

    private void resetBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j].setText("");
            }
        }
        xTurn = true;
    }
}

class TicTacToeButton extends JButton {
    private final int row, col;

    public TicTacToeButton(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

class TicTacToeRunner {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToeFrame::new);
    }
}
