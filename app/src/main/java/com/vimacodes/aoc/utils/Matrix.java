package com.vimacodes.aoc.utils;

import com.vimacodes.aoc.day6.Pos;
import java.util.ArrayList;
import java.util.List;

public record Matrix(int rows, int cols, char[][] map) {

  public static Matrix parse(String text) {
    List<String> lines = text.lines().toList();
    int rows = lines.size();
    int cols = lines.get(0).length();

    char[][] map = new char[rows][cols];

    String line;
    for (int i = 0; i < rows; i++) {
      line = lines.get(i);
      for (int j = 0; j < cols; j++) {
        char c = line.charAt(j);
        map[i][j] = c;
      }
    }

    return new Matrix(rows, cols, map);
  }

  public int getNum(int i, int j) {
    return Character.getNumericValue(map[i][j]);
  }

  public int getNum(Pos pos) {
    return getNum(pos.i(), pos.j());
  }

  public boolean isEqualTo(Pos pos, int comparedTo) {
    return validPos(pos) && getNum(pos) == comparedTo;
  }

  public boolean validPos(Pos pos) {
    return validPos(pos.i(), pos.j());
  }

  public boolean validPos(int i, int j) {
    return 0 <= i && i < rows && 0 <= j && j < cols;
  }

  public List<Pos> collectAllEqualTo(int num) {
    List<Pos> all = new ArrayList<>();

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (getNum(i, j) == num) {
          all.add(new Pos(i, j));
        }
      }
    }

    return all;
  }
}
