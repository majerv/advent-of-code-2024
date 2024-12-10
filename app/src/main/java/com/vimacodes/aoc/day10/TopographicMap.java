package com.vimacodes.aoc.day10;

import com.vimacodes.aoc.day6.Pos;
import com.vimacodes.aoc.utils.Matrix;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.stream.Stream;

public record TopographicMap(Matrix matrix) {

  public static TopographicMap parse(String text) {
    return new TopographicMap(Matrix.parse(text));
  }

  public Stream<Pos> trailHeads() {
    return matrix.collectAllEqualTo(0).stream();
  }

  public TrailStats stats(Pos start) {
    int allNines = 0;
    var allNinesPos = new HashSet<Pos>();

    var queue = new LinkedList<Pos>();
    queue.add(start);

    while (!queue.isEmpty()) {
      Pos pos = queue.poll();

      int num = matrix.getNum(pos);
      int next = num + 1;

      if (num == 9) {
        allNines++;
        allNinesPos.add(pos);
      } else {
        if (matrix.isEqualTo(pos.up(), next)) queue.add(pos.up());
        if (matrix.isEqualTo(pos.right(), next)) queue.add(pos.right());
        if (matrix.isEqualTo(pos.down(), next)) queue.add(pos.down());
        if (matrix.isEqualTo(pos.left(), next)) queue.add(pos.left());
      }
    }

    return new TrailStats(start, allNinesPos.size(), allNines);
  }
}
