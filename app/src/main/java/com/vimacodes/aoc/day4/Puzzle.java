package com.vimacodes.aoc.day4;

import java.util.List;
import lombok.NonNull;

public record Puzzle(char[][] letters, int rows, int cols) {

  public static Puzzle parse(@NonNull String text) {
    List<String> lines = text.lines().toList();
    int rows = lines.size();
    int cols = lines.get(0).length();

    char[][] letters = new char[rows][cols];

    System.out.printf("rows: %s, cols: %s\n", rows, cols);

    String line;
    for (int i = 0; i < rows; i++) {
      line = lines.get(i);
      for (int j = 0; j < cols; j++) {
        char c = line.charAt(j);
        System.out.print(c);
        letters[i][j] = c;
      }
      System.out.println();
    }

    return new Puzzle(letters, rows, cols);
  }

  public long count(String word) {
    long count = 0;

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        char c = letters[i][j];
        if (c == word.charAt(0)) {
          count += count(word, i, j);
        }
      }
    }

    return count;
  }

  private long count(String word, int i, int j) {
    return countHorizontal(word, i, j) + countVertical(word, i, j) + countDiagonal(word, i, j);
  }

  private long countHorizontal(String word, int i, int j) {
    return countInDirection(word, i, j, 0, 1) + countInDirection(word, i, j, 0, -1);
  }

  private long countVertical(String word, int i, int j) {
    return countInDirection(word, i, j, 1, 0) + countInDirection(word, i, j, -1, 0);
  }

  private long countDiagonal(String word, int i, int j) {
    return countInDirection(word, i, j, 1, 1)
        + countInDirection(word, i, j, -1, -1)
        + countInDirection(word, i, j, 1, -1)
        + countInDirection(word, i, j, -1, 1);
  }

  private long countInDirection(String word, int i, int j, int verticalMove, int horizontalMove) {
    int k = 0;
    int posI = i;
    int posJ = j;

    while (k < word.length() && validRow(posI) && validCol(posJ)) {
      if (word.charAt(k) != letters[posI][posJ]) return 0;

      k++;
      posI += verticalMove;
      posJ += horizontalMove;
    }

    return k == word.length() ? 1 : 0;
  }

  private boolean validCol(int pos) {
    return 0 <= pos && pos < cols;
  }

  private boolean validRow(int pos) {
    return 0 <= pos && pos < rows;
  }

  public long countCrossXmas() {
    long count = 0;

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        char c = letters[i][j];
        if (c == 'A') {
          count += countCross(i, j);
        }
      }
    }

    return count;
  }

  private long countCross(int i, int j) {
    String diagonalL = getDiagonalL(i, j);
    String diagonalR = getDiagonalR(i, j);
    return (diagonalL.equals("MAS") || diagonalL.equals("SAM"))
            && (diagonalR.equals("MAS") || diagonalR.equals("SAM"))
        ? 1
        : 0;
  }

  private String getDiagonalL(int i, int j) {
    return String.format(
        "%c%c%c",
        getCharOrStarAt(i - 1, j - 1), getCharOrStarAt(i, j), getCharOrStarAt(i + 1, j + 1));
  }

  private String getDiagonalR(int i, int j) {
    return String.format(
        "%c%c%c",
        getCharOrStarAt(i - 1, j + 1), getCharOrStarAt(i, j), getCharOrStarAt(i + 1, j - 1));
  }

  private char getCharOrStarAt(int i, int j) {
    return 0 <= i && i < rows && 0 <= j && j < cols ? letters[i][j] : '*';
  }
}
