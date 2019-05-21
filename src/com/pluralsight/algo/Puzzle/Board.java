
package com.pluralsight.algo.Puzzle;

import java.util.LinkedList;
import java.util.List;

public class Board {

  private final int[][] blocks;
  private final int n;
  private int blankRow;
  private int blankCol;


  public Board(int[][] blocks) {

    if (blocks == null) {
      throw new NullPointerException();
    }

    this.blocks = copyOf(blocks);
    n = blocks.length;
    blankRow = -1;
    blankCol = -1;
    initBoardBlankRow(n);

  }


  private int[][] copyOf(int[][] matrix) {

    int[][] clone = new int[matrix.length][];

    for (int row = 0; row < matrix.length; row++) {
      clone[row] = matrix[row].clone();
    }

    return clone;
  }

  private void initBoardBlankRow(int length) {
    for (int row = 0; row < n; row++) {
      for (int col = 0; col < n; col++) {
        if (blocks[row][col] == 0) {
          blankRow = row;
          blankCol = col;
          return;
        }
      }
    }
  }

  private void exch(int[][] p, int rowA, int colA, int rowB, int colB) {
    int temp = p[rowA][colA];
    p[rowA][rowB] = p[rowB][colB];
    p[rowB][colB] = temp;
  }

  private int manhattan(int row, int col) {
    int destinationValue = blocks[row][col] - 1;
    int destinationRow = destinationValue / n;
    int destinationCol = destinationValue % n;
    return Math.abs(destinationRow - row) + Math.abs(destinationCol - col);
  }


  // board dimension n
  public int dimension() {
    return n;
  }

  // number of blocks out of place
  public int hamming() {
    int result = 0;

    for (int row = 0; row < n; row++) {
      for (int col = 0; col < n; col++) {

        if (row == blankRow && col == blankCol) {
          continue;
        }

        if (manhattan(row, col) != 0) {
          result++;
        }

      }
    }
    return result;
  }

  // sum of manhattan distances between blocks and goal
  public int manhattan() {
    int result = 0;

    for (int row = 0; row < n; row++) {
      for (int col = 0; col < n; col++) {
        if (row == blankRow && col == blankCol) {
          continue;
        }
        result += manhattan(row, col);
      }
    }
    return result;

  }

  // is this boad the goal board
  public boolean isGoal() {
    return hamming() == 0;
  }

  // a board that is obtained by exchanging any pair of blocks
  public Board twin() {

    int[][] clone = copyOf(blocks);

    if (blankRow != 0) {
      exch(clone, 0, 0, 0, 1);
    } else {
      exch(clone, 1, 0, 1, 1);
    }
    return new Board(clone);
  }

  public boolean equals(Object y) {
    if (y == this)
      return true;
    if (y == null)
      return false;

    if (this.getClass() != y.getClass())
      return false;

    Board that = ((Board) y);

    if (this.blankRow != that.blankRow)
      return false;

    if (this.blankCol != that.blankCol)
      return false;

    if (this.n != that.n)
      return false;

    for (int row = 0; row < n; row++) {
      for (int col = 0; col < n; col++)
        if (this.blocks[row][col] != that.blocks[row][col])
          return false;
    }

    return true;
  }


  public Iterable<Board> neighbors() {

    List<Board> neighbors = new LinkedList<>();

    if (blankRow > 0) {
      int[][] north = copyOf(blocks);
      exch(north, blankRow, blankCol, blankRow - 1, blankCol);
      neighbors.add(new Board(north));
    }
    if (blankRow < n - 1) {
      int[][] south = copyOf(blocks);
      exch(south, blankRow, blankCol, blankRow + 1, blankCol);
      neighbors.add(new Board(south));
    }
    if (blankCol > 0) {
      int[][] west = copyOf(blocks);
      exch(west, blankRow, blankCol, blankRow, blankCol - 1);
      neighbors.add(new Board(west));
    }
    if (blankCol < n - 1) {
      int[][] east = copyOf(blocks);
      exch(east, blankRow, blankCol, blankRow, blankCol + 1);
      neighbors.add(new Board(east));
    }
    return neighbors;
  }


  public String toString() {

    StringBuilder builder = new StringBuilder();
    builder.append(n).append("\n");
    for (int row = 0; row < n; row++) {
      for (int col = 0; col < n; col++) {
        builder.append(String.format("%2d ", blocks[row][col]));
      }
      builder.append("\n");
    }
    return builder.toString();
  }

  public static void main(String[] args) {

    int[][] blocks = new int[3][3];
    for (int i = 0; i < 3; i++)
      for (int j = 0; j < 3; j++)
        blocks[i][j] = i * j;

    final Board board = new Board(blocks);

    System.out.println(board);
  }

}
