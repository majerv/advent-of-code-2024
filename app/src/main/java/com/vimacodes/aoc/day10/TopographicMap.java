package com.vimacodes.aoc.day10;

import com.vimacodes.aoc.day6.Pos;
import com.vimacodes.aoc.utils.Matrix;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public record TopographicMap(Matrix matrix, List<Pos> trailHeads) {

  public static TopographicMap parse(String text) {
    Matrix matrix = Matrix.parse(text);
    List<Pos> trailHeads = new ArrayList<>();

    for (int i = 0; i < matrix.rows(); i++) {
      for (int j = 0; j < matrix.cols(); j++) {
        int num = matrix.getNum(i, j);
        if (num == 0) {
          trailHeads.add(new Pos(i, j));
        }
      }
    }

    return new TopographicMap(matrix, trailHeads);
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
