package com.vaquierm.boredgames.boredgames.Sudoku;

import android.util.Log;

/**
 * Created by Michael Vaquier on 2017-08-17.
 */

public class SudokuGenerator {

    private final static String TAG = SudokuGenerator.class.getName();

    private static int minimumHintsOnRowColumn = 4;

    private static int minimumTotalHints = 45;

    private static Difficulty difficulty = Difficulty.EASY;



    public enum Difficulty { EASY, MEDIUM, HARD }

    public static Sudoku generateSudokuPuzzle(Difficulty d) {
        setDifficulty(d);
        Sudoku sudoku = new Sudoku(3);
        sudoku.solve(1, 0);
        shuffleBoard(sudoku);

        int[][] grid = sudoku.getGrid();
        for(int i = 0 ; i < grid.length ; i++) { //TODO change pattern if easy mode
            grid = sudoku.getGrid();
            if(!enoughTotalHints(sudoku)) {
                break;
            }
            for (int j = 0 ; j < grid[i].length ; j++) {
                if(!enoughHorizontalHints(sudoku, i)) {
                    break;
                }
                else if(!enoughVerticalHints(sudoku, j)) {
                    continue;
                }

                grid = sudoku.getGrid();
                int current = grid[i][j];

                for(int k = 1 ; k <= grid.length ; k++) {
                    grid = sudoku.getGrid();
                    int[][] saveBoard;
                    if(k == current || !sudoku.isValidRow(i , k) || !sudoku.isValidColumn(j, k) || !sudoku.isValidSubMatrix(i, j, k)) {
                        if(k == grid.length) {
                            grid[i][j] = 0;
                            sudoku.setGrid(grid);
                        }
                        continue;
                    }
                    saveBoard = saveBoard(sudoku);
                    grid[i][j] = k;
                    if(sudoku.solve(1, 0)) {
                        sudoku.setGrid(saveBoard);
                        grid = saveBoard;
                        break;
                    }
                    grid[i][j] = current;
                    if(k == grid.length) {
                        saveBoard = saveBoard(sudoku);
                        grid[i][j] = 0;
                        if(sudoku.solve(1,0)) {
                            saveBoard[i][j] = 0;
                            sudoku.setGrid(saveBoard);
                        }
                        grid[i][j] = current;
                    }
                }
            }
        }
        shuffleBoard(sudoku);
        return sudoku;
    }



    public static void setDifficulty(Difficulty d) {
        Log.d(TAG, "entering .setDifficulty(" + d + ")");
        difficulty = d;
        switch (d) {
            case EASY:
                minimumHintsOnRowColumn = 4;
                minimumTotalHints = 45;
                break;
            case MEDIUM:
                minimumHintsOnRowColumn = 3;
                minimumTotalHints = 37;
                break;
            case HARD:
                minimumHintsOnRowColumn = 2;
                minimumTotalHints = 30;
                break;
            default:
                break;
        }
    }

    private static int[][] saveBoard(Sudoku sudoku) {
        int[][] grid = sudoku.getGrid();
        int[][] save = new int[grid.length][grid.length];
        for(int i = 0 ; i < grid.length ; i++) {
            for(int j = 0 ; j < grid[i].length ; j++) {
                save[i][j] = grid[i][j];
            }
        }
        return save;
    }

    private static boolean enoughTotalHints(Sudoku sudoku) {
        int[][] grid = sudoku.getGrid();
        int counter = 0;
        for(int i = 0 ; i < grid.length ; i++) {
            for (int j = 0 ; j < grid[i].length ; j++) {
                if(grid[i][j] != 0) {
                    counter++;
                }
            }
        }
        if(counter > minimumTotalHints) {
            return true;
        }
        return false;
    }

    private static boolean enoughHorizontalHints(Sudoku sudoku, int row) {
        int[][] grid = sudoku.getGrid();
        int counter = 0;
        for(int j = 0 ; j < grid[row].length ; j++) {
            if (grid[row][j] != 0) {
                counter++;
            }
        }
        if(counter > minimumHintsOnRowColumn) {
            return true;
        }
        return false;
    }

    private static boolean enoughVerticalHints(Sudoku sudoku, int col) {
        int[][] grid = sudoku.getGrid();
        int counter = 0;
        for(int i = 0 ; i < grid.length ; i++) {
            if (grid[i][col] != 0) {
                counter++;
            }
        }
        if(counter > minimumHintsOnRowColumn) {
            return true;
        }
        return false;
    }

    public static void shuffleBoard(Sudoku sudoku) {
        Log.d(TAG, "entering shuffleBoard");
        for(int i = 0; i <= 10; i++) {
            swapRows(sudoku);
            rotateBoard(sudoku);
            swapTwoNumbers(sudoku);
        }
    }


    private static void rotateBoard(Sudoku sudoku) {
        int[][] oldGrid = sudoku.getGrid();
        int[][] newGrid = new int[oldGrid.length][oldGrid.length];

        for(int i = 0 ; i < oldGrid.length ; i++) {
            for(int j = 0 ; j < oldGrid[i].length ; j++) {
                newGrid[oldGrid.length - 1 - j][i] = oldGrid[i][j];
            }
        }
        sudoku.setGrid(newGrid);
    }

    private static void swapRows(Sudoku sudoku) {
        int[][] grid = sudoku.getGrid();
        int[] temp;
        double random = Math.random();
        if(random < 0.33) {
            temp = grid[0];
            grid[0] = grid[1];
            grid[1] = temp;
        }
        else if(random < 0.66) {
            temp = grid[0];
            grid[0] = grid[2];
            grid[2] = temp;
        }
        else {
            temp = grid[1];
            grid[1] = grid[2];
            grid[2] = temp;
        }
        random = Math.random();
        if(random < 0.33) {
            temp = grid[3];
            grid[3] = grid[4];
            grid[4] = temp;
        }
        else if(random < 0.66) {
            temp = grid[3];
            grid[3] = grid[5];
            grid[5] = temp;
        }
        else {
            temp = grid[4];
            grid[4] = grid[5];
            grid[5] = temp;
        }
        random = Math.random();
        if(random < 0.33) {
            temp = grid[6];
            grid[6] = grid[7];
            grid[7] = temp;
        }
        else if(random < 0.66) {
            temp = grid[6];
            grid[6] = grid[8];
            grid[8] = temp;
        }
        else {
            temp = grid[7];
            grid[7] = grid[8];
            grid[8] = temp;
        }
    }

    private static void swapTwoNumbers(Sudoku sudoku) {
        int[][] grid = sudoku.getGrid();
        int a = (int) Math.round(Math.random()*8) + 1;
        int b = (int) Math.round(Math.random()*8) + 1;
        if(a == b) {
            if(b == 1) {
                b = 2;
            }
            else if(b == 9) {
                b = 8;
            }
            else {
                b++;
            }
        }
        for(int i = 0 ; i < grid.length ; i++) {
            for(int j = 0 ; j < grid[i].length ; j++) {
                if(grid[i][j] == a) {
                    grid[i][j] = b;
                }
                else if(grid[i][j] == b) {
                    grid[i][j] = a;
                }
            }
        }
    }

}
