package com.vaquierm.boredgames.boredgames.Sudoku;

/**
 * Created by Michael Vaquier on 2017-08-12.
 */

import android.util.Log;

import java.util.*;
import java.io.*;


class Sudoku
{
    /* SIZE is the size parameter of the Sudoku puzzle, and N is the square of the size.  For
     * a standard Sudoku puzzle, SIZE is 3 and N is 9. */
    private int SIZE, N;

    /* The grid contains all the numbers in the Sudoku puzzle.  Numbers which have
     * not yet been revealed are stored as 0. */
    private int Grid[][];


    public Sudoku(int size) {
        SIZE = size;
        N = size*size;
        Grid = new int[N][N];

        for (int i = 0; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                Grid[i][j] = 0;
            }
        }
    }

    public Sudoku(int[][] board)
    {
        N = board.length;
        SIZE = (int) Math.sqrt(N);

        Grid = board;
    }




    public boolean solve(int k /*current number of interest*/, int i /*row*/) {
        if ( k == N + 1 ) { //this means we placed one k on each layer and we have done every possible value of k
            return true;
        }
        if ( i == N ) {
            return this.solve(k+1, 0);
        }
        else {
            if ( !this.isValidRow(i, k) ) {
                return this.solve(k, i+1);
            }
            for ( int j = 0 ; j < N ; j++ ) {
                if (this.Grid[i][j] > 0) { //there is already smething on this cell
                    continue;
                }
                if ( !this.isValidColumn(j, k) ) {
                    continue;
                }
                else {
                    if ( !this.isValidSubMatrix(i, j, k) ) {
                        continue;
                    }
                    else {
                        this.Grid[i][j] = k;
                        if ( this.solve(k, i+1) ) { //if the previous choice of placement was good, and leads to a complete solution, we return true.
                            return true;
                        }
                        else { //otherwise we undo our placement and continue.
                            this.Grid[i][j] = 0;
                        }
                    }
                }
            }
            return false; //if we when through all possible positions on this layer without success we return false to let the upper calls to undo a change and keep looking.
        }
    }


    /*checks if k is already in row*/
    public boolean isValidColumn(int column, int k) {
        for ( int i = 0 ; i < N ; i++ ) {
            if ( this.Grid[i][column] == k ) {
                return false;
            }
        }
        return true;
    }

    /*checks if k is already in column*/
    public boolean isValidRow(int row, int k) {
        for ( int j = 0 ; j < N ; j++ ) {
            if ( this.Grid[row][j] == k ) {
                return false;
            }
        }
        return true;
    }

    /*checks if k can be place in the submatrix containing (column, row)*/
    public boolean isValidSubMatrix(int column, int row, int k) {
        int minIRange = ( column / SIZE ) * SIZE;
        int minJRange = ( row / SIZE ) * SIZE;
        int maxIRange = minIRange + SIZE;
        int maxJRange = minJRange + SIZE;
        for ( int i = minIRange ; i < maxIRange ; i++ ) {
            for ( int j = minJRange ; j < maxJRange ; j++ ) {
                if ( this.Grid[i][j] == k ) {
                    return false;
                }
            }
        }
        return true;
    }

    /* Checks if a given partial solution is valid vertically or not */
    public boolean isVertivalValid(int k) {
        for ( int i = 0 ; i < N ; i++ ) {
            boolean found = false;
            for ( int j = 0 ; j < N ; j++ ) {
                if ( this.Grid[i][j] == k ) {
                    if ( found ) {
                        return false; //if k was already found in row i and we found it again we break out
                    }
                    else {
                        found = true;
                    }
                }
            }
        }
        return true;
    }

    /* Checks if a given partial solution is valid horizontally or not */
    public boolean isHorizontalValid(int k) {
        for ( int j = 0 ; j < N ; j++ ) {
            boolean found = false;
            for ( int i = 0 ; i < N ; i++ ) {
                if ( this.Grid[i][j] == k ) {
                    if ( found ) {
                        return false; //if k was already found in column j and we found it again we break out
                    }
                    else {
                        found = true;
                    }
                }
            }
        }
        return true;
    }

    /* Checks if a given partial solution is valid for submatrices or not */
    public boolean isSubMatrixValid(int k) {
        for ( int i1 = 0 ; i1 < SIZE ; i1++ ) {
            for ( int j1 = 0 ; j1 < SIZE ; j1++ ) {
                boolean found = false;
                int maxIRange = i1 * SIZE + SIZE;
                int maxJRange = j1 * SIZE + SIZE;
                for ( int i = i1 * SIZE ; i < maxIRange ; i++ ) {
                    for ( int j = j1 * SIZE ; j < maxJRange ; j++ ) {
                        if ( this.Grid[i][j] == k ) {
                            if ( found ) {
                                return false;
                            }
                            else {
                                found = true;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean isSolved() {
        return (isValidState() && isFull());
    }

    public boolean isValidState() {
        for(int k = 1 ; k <= N ; k++) {
            if(!isHorizontalValid(k) || !isVertivalValid(k) || !isSubMatrixValid(k)) {
                return false;
            }
        }
        return true;
    }

    public boolean isFull() {
        for(int i = 0; i < Grid.length ; i++) {
            for(int j = 0 ; j < Grid[i].length ; j++) {
                if(Grid[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isEmpty() {
        for(int i = 0; i < Grid.length ; i++) {
            for(int j = 0 ; j < Grid[i].length ; j++) {
                if(Grid[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public int[][] getGrid() { return Grid; }

    public void setGrid(int[][] newGrid) { Grid = newGrid; }


}

