package com.vimacodes.aoc.day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Update {

  List<Integer> pages;
  Comparator<Integer> ordering;

  public static Update of(String line, Comparator<Integer> ordering) {
    List<Integer> pages =
        Arrays.stream(line.split(",")).map(String::trim).map(Integer::parseInt).toList();

    return new Update(new ArrayList<>(pages), ordering);
  }

  public boolean isInRightOrder() {
    var sorted = new ArrayList<>(pages);
    sorted.sort(ordering);

    return pages.equals(sorted);
  }

  public boolean sort() {
    var original = new ArrayList<>(pages);
    pages.sort(ordering);

    return pages.equals(original);
  }

  public long middle() {
    return pages.get(pages.size() / 2);
  }
}
