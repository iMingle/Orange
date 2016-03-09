/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.servicesinthread.parallel;

import java.util.Arrays;

/**
 * 任务取消后子任务也取消,N皇后,在N*N的国际象棋棋盘上查找放置N个互相不会攻击的皇后的位置
 * 
 * @since 1.0
 * @author Mingle
 */
public class NQueens extends NullFJTask {
    static int boardSize; // fixed after initialization in main

    static final Result result = new Result();

    public static void main(String[] args) {
        try {
            boardSize = 8;
            FJTaskRunnerGroup tasks = new FJTaskRunnerGroup(4);
            int[] initialBoard = new int[0]; // start with empty board
            tasks.execute(new NQueens(initialBoard));
            int[] board = result.await();
            System.out.println(Arrays.toString(board));
        } catch (InterruptedException ie) {}
    }

    final int[] sofar; // initial configuration

    NQueens(int[] board) {
        this.sofar = board;
    }

    public void run() {
        if (!result.solved()) { // skip if already solved
            int row = sofar.length;

            if (row >= boardSize) // done
                result.set(sofar);
            else { // try all expansions
                for (int q = 0; q < boardSize; ++q) {
                    // Check if queen can be placed in column q of next row
                    boolean attacked = false;
                    for (int i = 0; i < row; ++i) {
                        int p = sofar[i];
                        if (q == p || q == p - (row - i) || q == p + (row - i)) {
                            attacked = true;
                            break;
                        }
                    }

                    // If so, fork to explore moves from new configuration
                    if (!attacked) {
                        // build extended board representation
                        int[] next = new int[row + 1];
                        for (int k = 0; k < row; ++k)
                            next[k] = sofar[k];
                        next[row] = q;
                        new NQueens(next).fork();
                    }
                }
            }
        }
    }
    
    // Boards are arrays where each cell represents a row,
    // and holds the column number of the queen in that row
    static class Result { // holder for ultimate result
        private int[] board = null; // non-null when solved

        synchronized boolean solved() {
            return board != null;
        }

        synchronized void set(int[] b) { // Support use by non-Tasks
            if (board == null) {
                board = b;
                notifyAll();
            }
        }

        synchronized int[] await() throws InterruptedException {
            while (board == null)
                wait();
            return board;
        }
    }
}
