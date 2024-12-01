package com.vimacodes.aoc.day1;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.vimacodes.aoc.utils.Inputs;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;

public record Similarities(List<Long> left, Multiset<Long> right) {

  public static Similarities parse(@NonNull final String text) {
    List<String> lines = text.lines().toList();

    var left = new ArrayList<Long>();
    var right = HashMultiset.<Long>create();

    for (String line : lines) {
      var longs = Inputs.parseToLongTuple(line);
      left.add(longs.getValue0());
      right.add(longs.getValue1());
    }

    return new Similarities(left, right);
  }

  public Long score() {
    return left.stream().mapToLong(v -> v * right.count(v)).sum();
  }
}
