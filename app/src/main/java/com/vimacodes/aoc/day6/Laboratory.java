package com.vimacodes.aoc.day6;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

public record Laboratory(int rows, int cols, char[][] map, Pos start) {

  public static Laboratory parse(String text) {
    List<String> lines = text.lines().toList();
    int rows = lines.size();
    int cols = lines.get(0).length();

    char[][] map = new char[rows][cols];

    //    System.out.printf("rows: %s, cols: %s\n", rows, cols);

    Pos start = new Pos(0, 0);

    String line;
    for (int i = 0; i < rows; i++) {
      line = lines.get(i);
      for (int j = 0; j < cols; j++) {
        char c = line.charAt(j);
        //        System.out.print(c);
        if (c == '^') {
          map[i][j] = '.';
          start = new Pos(i, j);
        } else {
          map[i][j] = c;
        }
      }
      //      System.out.println();
    }

    //    System.out.println("Start pos: " + start);
    return new Laboratory(rows, cols, map, start);
  }

  public long walk() {
    Set<Pos> visitedFields = new HashSet<>();
    Walker walker = new Walker(start, Direction.UP, null);

    while (walker.isInLab()) {
      visitedFields.add(walker.pos);
      walker.move();
    }

    return visitedFields.size();
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
    Walker slow = new Walker(start, Direction.UP, blockAt);
    Walker fast = new Walker(start, Direction.UP, blockAt);

    while (fast.isInLab()) {
      fast.move();
      if (fast.equals(slow)) return true;
      fast.move();
      slow.move();
    }

    return false;
  }

  private boolean free(Pos pos) {
    return !validPos(pos) || map[pos.i()][pos.j()] != '#';
  }

  private boolean validPos(Pos pos) {
    return validPos(pos.i(), pos.j());
  }

  private boolean validPos(int i, int j) {
    return 0 <= i && i < rows && 0 <= j && j < cols;
  }

  @AllArgsConstructor
  @EqualsAndHashCode
  private class Walker {

    private Pos pos;
    private Direction dir;

    @Nullable private Pos blockAt;

    public boolean isInLab() {
      return validPos(pos);
    }

    public void move() {
      Pos next = pos.move(dir);

      if (free(next) && !next.equals(blockAt)) {
        pos = next;
      } else {
        dir = dir.turnRight();
      }
    }
  }
}
