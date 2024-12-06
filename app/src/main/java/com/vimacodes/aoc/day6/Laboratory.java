package com.vimacodes.aoc.day6;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Value;

@Value
public class Laboratory {

  int rows;
  int cols;
  char[][] map;
  Pos start;

  public static Laboratory parse(String text) {
    List<String> lines = text.lines().toList();
    int rows = lines.size();
    int cols = lines.get(0).length();

    char[][] map = new char[rows][cols];

    System.out.printf("rows: %s, cols: %s\n", rows, cols);

    Pos start = new Pos(0, 0);

    String line;
    for (int i = 0; i < rows; i++) {
      line = lines.get(i);
      for (int j = 0; j < cols; j++) {
        char c = line.charAt(j);
        System.out.print(c);
        if (c == '^') {
          map[i][j] = '.';
          start = new Pos(i, j);
        } else {
          map[i][j] = c;
        }
      }
      System.out.println();
    }

    System.out.println("Start pos: " + start);
    return new Laboratory(rows, cols, map, start);
  }

  public long walk() {
    var pos = start;
    var dir = Direction.UP;

    Set<Integer> visitedFields = new HashSet<>();
    Pos next = pos.move(dir);

    while (validPos(next)) {
      visitedFields.add(fieldNumber(pos));
      if (free(next)) {
        pos = next;
      } else {
        dir = dir.turnRight();
      }

      next = pos.move(dir);
    }

    visitedFields.add(fieldNumber(pos));
    return visitedFields.size();
  }

  private boolean free(Pos pos) {
    return map[pos.i()][pos.j()] != '#';
  }

  private boolean validPos(Pos pos) {
    return validPos(pos.i(), pos.j());
  }

  private boolean validPos(int i, int j) {
    return 0 <= i && i < rows && 0 <= j && j < cols;
  }

  private Integer fieldNumber(Pos pos) {
    return fieldNumber(pos.i(), pos.j());
  }

  private Integer fieldNumber(int i, int j) {
    return i * cols + j;
  }

  public long possibleLoops() {
    int loops = 0;

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (map[i][j] == '.' && isLoopWithBlockAt(new Pos(i, j))) {
          loops++;
        }
      }
    }

    return loops;
  }

  private boolean isLoopWithBlockAt(Pos blockAt) {
    var pos = start;
    var dir = Direction.UP;
    int dups = 0;

    int all = rows * cols;

    Set<Integer> visitedFields = new HashSet<>();
    Pos next = pos.move(dir);

    while (validPos(next)) {
      if (!visitedFields.add(fieldNumber(pos))) {
        dups++;
      }
      if (free(next) && !next.equals(blockAt)) {
        pos = next;
      } else {
        dir = dir.turnRight();
      }

      next = pos.move(dir);

      if (dups > all || visitedFields.size() >= all) return true;
    }

    return false;
  }
}
