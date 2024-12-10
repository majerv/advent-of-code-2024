package com.vimacodes.aoc.day10;

import com.vimacodes.aoc.day6.Pos;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public record TopographicMap(int rows, int cols, int[][] map, List<Pos> trailHeads) {

  public static TopographicMap parse(String text) {
    List<String> lines = text.lines().toList();
    int rows = lines.size();
    int cols = lines.get(0).length();

    int[][] map = new int[rows][cols];
    List<Pos> trailHeads = new ArrayList<>();

    System.out.printf("rows: %s, cols: %s\n", rows, cols);

    String line;
    for (int i = 0; i < rows; i++) {
      line = lines.get(i);
      for (int j = 0; j < cols; j++) {
        int num = Character.getNumericValue(line.charAt(j));
        //        System.out.print(num);
        if (num == 0) {
          trailHeads.add(new Pos(i, j));
        }
        map[i][j] = num;
      }
      //      System.out.println();
    }
    //    System.out.println(trailHeads);

    return new TopographicMap(rows, cols, map, trailHeads);
  }

  public TrailStats stats(Pos start) {
    int allNines = 0;
    var allNinesPos = new HashSet<Pos>();
    var queue = new LinkedList<Pos>();

    queue.add(start);

    while (!queue.isEmpty()) {
      Pos pos = queue.poll();

      int num = getNum(pos);
      int next = num + 1;

      if (num == 9) {
        allNines++;
        allNinesPos.add(pos);
      } else {
        if (getNum(pos.up()) == next) queue.add(pos.up());
        if (getNum(pos.right()) == next) queue.add(pos.right());
        if (getNum(pos.down()) == next) queue.add(pos.down());
        if (getNum(pos.left()) == next) queue.add(pos.left());
      }
    }

    TrailStats trailStats = new TrailStats(start, allNinesPos.size(), allNines);
    //    System.out.println("Trailhead stats: " + trailStats);
    return trailStats;
  }

  private int getNum(Pos pos) {
    return validPos(pos) ? map[pos.i()][pos.j()] : -1;
  }

  private boolean validPos(Pos pos) {
    return validPos(pos.i(), pos.j());
  }

  private boolean validPos(int i, int j) {
    return 0 <= i && i < rows && 0 <= j && j < cols;
  }
}
