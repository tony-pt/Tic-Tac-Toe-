package eecs1022.lab8.tictactoe.model;

public class Game {
    private String playerX;
    private String playerO;
    private String status;
    private char[][] board;
    private boolean turnX;
    private String winner;
    private boolean gameOver;

    public Game(String playerX, String playerO) {
        this.playerX = playerX;
        this.playerO = playerO;
        turnX = true;
        board = new char[3][3];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = '_';
            }
        }
    }

    public String getCurrentPlayer() {
        if (gameOver && status == null) {
            if (winner != null) {
                status = "Game is over with " + winner + " being the winner.";
            } else {
                status = "Game is over with a tie between " + playerX + " and " + playerO + ".";
            }
        }
        if (gameOver) {
            return null;
        }
        if (turnX) {
            return playerX;
        } else {
            return playerO;
        }
    }

    public String getStatus() {
        if (status != null) {
            return status;
        }
        return getCurrentPlayer() + "'s turn to play...";
    }

    public char[][] getBoard() {
        return board;
    }

    public void setWhoPlaysFirst(char c) {
        if (c == 'o') {
            this.turnX = false;
        } else {
            this.turnX = true;
        }
    }

    public void move(int i, int j) {
        if (gameOver) {
            if (winner != null) {
                status = "Error: game is already over with a winner.";
            } else {
                status = "Error: game is already over with a tie.";
            }
        } else if (i < 1 || i > 3) {
            status = "Error: row " + i + " is invalid.";
        } else if (j < 1 || j > 3) {
            status = "Error: col " + j + " is invalid.";
        } else if (board[i - 1][j - 1] != '_') {
            status = "Error: slot @ (" + i + ", " + j + ") is already occupied.";
        } else {
            if (turnX) {
                board[i - 1][j - 1] = 'x';
            } else {
                board[i - 1][j - 1] = 'o';
            }
            String checkOver = checkGameOver();
            if (checkOver != null) {
                if (checkOver.equals("win")) {
                    if (turnX) {
                        winner = playerX;
                    } else {
                        winner = playerO;
                    }
                    gameOver = true;
                } else if (checkOver.equals("tie")) {
                    gameOver = true;
                }
            }
            turnX = !turnX;
            status = null;
        }
    }

    private String checkGameOver() {
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] != '_') {
                if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                    return "win";
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            if (board[0][i] != '_') {
                if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                    return "win";
                }
            }
        }
        if (board[0][0] != '_') {
            if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
                return "win";
            }
        }
        if (board[0][2] != '_') {
            if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
                return "win";
            }
        }
        boolean tie = true;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '_') {
                    tie = false;
                }
            }
        }
        if (tie) {
            return "tie";
        }
        return null;
    }

}

