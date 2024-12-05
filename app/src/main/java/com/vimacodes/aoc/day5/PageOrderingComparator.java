package com.vimacodes.aoc.day5;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import lombok.Value;

@Value
public class PageOrderingComparator implements Comparator<Integer> {

  Map<Integer, Set<Integer>> rules;

  @Override
  public int compare(Integer o1, Integer o2) {
    if (inOrder(o1, o2)) {
      return -1;
    } else if (inOrder(o2, o1)) {
      return 1;
    } else {
      return 0;
    }
  }

  private boolean inOrder(int first, int second) {
    return rules.containsKey(first) && rules.get(first).contains(second);
  }
}
