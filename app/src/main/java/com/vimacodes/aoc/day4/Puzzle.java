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
    return countHorizontalF(word, i, j) + countHorizontalB(word, i, j);
  }

  private long countVertical(String word, int i, int j) {
    return countVerticalF(word, i, j) + countVerticalB(word, i, j);
  }

  private long countDiagonal(String word, int i, int j) {
    return countDiagonalFR(word, i, j)
        + countDiagonalBL(word, i, j)
        + countDiagonalFL(word, i, j)
        + countDiagonalBR(word, i, j);
  }

  private long countVerticalF(String word, int i, int j) {
    int k = 0;

    for (; k < word.length() && i + k < rows; k++) {
      if (word.charAt(k) != letters[i + k][j]) return 0;
    }

    if (k == word.length()) {
      System.out.printf("VF: Found %s vertical at (%s,%s)\n", word, i, j);
      return 1;
    } else {
      return 0;
    }
  }

  private long countVerticalB(String word, int i, int j) {
    int k = 0;

    for (; k < word.length() && i - k >= 0; k++) {
      if (word.charAt(k) != letters[i - k][j]) return 0;
    }

    if (k == word.length()) {
      System.out.printf("VB: Found backwards %s vertical at (%s,%s)\n", word, i, j);
      return 1;
    } else {
      return 0;
    }
  }

  private long countHorizontalF(String word, int i, int j) {
    int k = 0;

    for (; k < word.length() && j + k < cols; k++) {
      if (word.charAt(k) != letters[i][j + k]) return 0;
    }

    if (k == word.length()) {
      System.out.printf("HF: Found %s at (%s,%s)\n", word, i, j);
      return 1;
    } else {
      return 0;
    }
  }

  private long countHorizontalB(String word, int i, int j) {
    int k = 0;

    for (; k < word.length() && j - k >= 0; k++) {
      if (word.charAt(k) != letters[i][j - k]) return 0;
    }

    if (k == word.length()) {
      System.out.printf("HB: Found backwards %s at (%s,%s)\n", word, i, j);
      return 1;
    } else {
      return 0;
    }
  }

  private long countDiagonalFR(String word, int i, int j) {
    int k = 0;

    for (; k < word.length() && j + k < cols & i + k < rows; k++) {
      if (word.charAt(k) != letters[i + k][j + k]) return 0;
    }

    if (k == word.length()) {
      System.out.printf("DFR: Found %s diagonal at (%s,%s)\n", word, i, j);
      return 1;
    } else {
      return 0;
    }
  }

  private long countDiagonalBL(String word, int i, int j) {
    int k = 0;

    for (; k < word.length() && j - k >= 0 && i - k >= 0; k++) {
      if (word.charAt(k) != letters[i - k][j - k]) return 0;
    }

    if (k == word.length()) {
      System.out.printf("DBL: Found backwards %s diagonal left at (%s,%s)\n", word, i, j);
      return 1;
    } else {
      return 0;
    }
  }

  private long countDiagonalFL(String word, int i, int j) {
    int k = 0;

    for (; k < word.length() && j - k >= 0 & i + k < rows; k++) {
      if (word.charAt(k) != letters[i + k][j - k]) return 0;
    }

    if (k == word.length()) {
      System.out.printf("DFL: Found %s diagonal left at (%s,%s)\n", word, i, j);
      return 1;
    } else {
      return 0;
    }
  }

  private long countDiagonalBR(String word, int i, int j) {
    int k = 0;

    for (; k < word.length() && j + k < cols && i - k >= 0; k++) {
      if (word.charAt(k) != letters[i - k][j + k]) return 0;
    }

    if (k == word.length()) {
      System.out.printf("DBR: Found backwards %s diagonal right at (%s,%s)\n", word, i, j);
      return 1;
    } else {
      return 0;
    }
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
