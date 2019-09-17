package com.acme.calculator.model;

public class Board {

    private Cell[][] cells = new Cell[3][3];

    private State winner;
    private GameState state;
    private State currentTurn;

    private enum GameState { IN_PROGRESS, FINISHED };

    public Board() {
        restart();
    }

    /**
     *  Restart or start a new game, will clear the board and win status
     */
    public void restart() {
        clearCells();
        winner = null;
        currentTurn = State.op1State;
        state = GameState.IN_PROGRESS;
    }

    /**
     * Mark the current row for the player who's current turn it is.
     * Will perform no-op if the arguments are out of range or if that position is already played.
     * Will also perform a no-op if the game is already over.
     *
     * @param row 0..2
     * @param col 0..2
     * @return the player that moved or null if we did not move anything.
     *
     */
    public State mark(int row, int col ) {

        State stateThatMoved = null;

        if(isValid(row, col)) {

            cells[row][col].setValue(currentTurn);
            stateThatMoved = currentTurn;

            if(isWinningMoveByPlayer(currentTurn, row, col)) {
                state = GameState.FINISHED;
                winner = currentTurn;

            } else {
                // flip the current turn and continue
                flipCurrentTurn();
            }
        }

        return stateThatMoved;
    }

    public State getWinner() {
        return winner;
    }

    private void clearCells() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    private boolean isValid(int row, int col ) {
        if( state == GameState.FINISHED ) {
            return false;
        } else if( isOutOfBounds(row) || isOutOfBounds(col) ) {
            return false;
        } else if( isCellValueAlreadySet(row, col) ) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isOutOfBounds(int idx) {
        return idx < 0 || idx > 2;
    }

    private boolean isCellValueAlreadySet(int row, int col) {
        return cells[row][col].getValue() != null;
    }


    /**
     * Algorithm adapted from http://www.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe.html
     * @param state
     * @param currentRow
     * @param currentCol
     * @return true if <code>state</code> who just played the move at the <code>currentRow</code>, <code>currentCol</code>
     *              has a tic tac toe.
     */
    private boolean isWinningMoveByPlayer(State state, int currentRow, int currentCol) {

        return (cells[currentRow][0].getValue() == state         // 3-in-the-row
                && cells[currentRow][1].getValue() == state
                && cells[currentRow][2].getValue() == state
                || cells[0][currentCol].getValue() == state      // 3-in-the-column
                && cells[1][currentCol].getValue() == state
                && cells[2][currentCol].getValue() == state
                || currentRow == currentCol            // 3-in-the-diagonal
                && cells[0][0].getValue() == state
                && cells[1][1].getValue() == state
                && cells[2][2].getValue() == state
                || currentRow + currentCol == 2    // 3-in-the-opposite-diagonal
                && cells[0][2].getValue() == state
                && cells[1][1].getValue() == state
                && cells[2][0].getValue() == state);
    }

    private void flipCurrentTurn() {
        currentTurn = currentTurn == State.op1State ? State.op2State : State.op1State;
    }

}
