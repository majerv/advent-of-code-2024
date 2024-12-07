package com.vimacodes.aoc.day7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Value;

@Value
public class Equation {

  long result;
  List<Integer> operands;

  public static Equation parse(String line) {
    String[] split = line.split(":");

    long result = Long.parseLong(split[0].trim());
    var operands =
        Arrays.stream(split[1].trim().split("\\s+"))
            .map(String::trim)
            .map(Integer::parseInt)
            .toList();

    return new Equation(result, operands);
  }

  public boolean solvable() {
    return solvable(false);
  }

  public boolean solvableWithConcat() {
    return solvable(true);
  }

  public boolean solvable(boolean withConcatenation) {
    final Set<Long> results = new HashSet<>();
    results.add((long) operands.get(0));

    final List<Long> newResults = new ArrayList<>();
    for (int i = 1; i < operands.size(); i++) {
      int op = operands.get(i);

      results.forEach(
          r -> {
            newResults.add(r + op);
            newResults.add(r * op);
            if (withConcatenation) newResults.add(concat(r, op));
          });

      results.clear();
      results.addAll(newResults);
      newResults.clear();
    }

    return results.contains(result);
  }

  private Long concat(Long v1, int v2) {
    return Long.valueOf(String.valueOf(v1) + v2);
  }

  public long result() {
    return result;
  }
}
