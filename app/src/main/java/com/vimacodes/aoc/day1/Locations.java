package com.vimacodes.aoc.day1;

import com.vimacodes.aoc.utils.Inputs;
import java.util.List;
import java.util.PriorityQueue;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public record Locations(PriorityQueue<Long> left, PriorityQueue<Long> right) {

  public static Locations parse(@NonNull final String text) {
    List<String> lines = text.lines().toList();

    PriorityQueue<Long> left = new PriorityQueue<>();
    PriorityQueue<Long> right = new PriorityQueue<>();

    for (String line : lines) {
      var longs = Inputs.parseToLongTuple(line);
      left.add(longs.getValue0());
      right.add(longs.getValue1());
    }

    return new Locations(left, right);
  }

  public Long totalDifferences() {
    long diffs = 0;

    while (!left.isEmpty() && !right.isEmpty()) {
      long lv = poll(left);
      long rv = poll(right);
      long diff = Math.abs(lv - rv);
      diffs += (diff);
      log.debug("left: {} right: {} diff: {}", lv, rv, diff);
    }

    return diffs;
  }

  private long poll(PriorityQueue<Long> queue) {
    return queue.isEmpty() ? 0 : queue.poll();
  }
}
